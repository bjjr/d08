
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AuditRepository;
import domain.Attachment;
import domain.Audit;
import domain.Auditor;
import domain.Property;

@Service
@Transactional
public class AuditService {

	// Managed repository -----------------------------------

	@Autowired
	private AuditRepository		auditRepository;

	// Supporting services ----------------------------------

	@Autowired
	private AuditorService		auditorService;

	@Autowired
	private AttachmentService	attachmentService;

	@Autowired
	private PropertyService		propertyService;

	// Validator --------------------------------------------

	@Autowired
	private Validator			validator;


	// Constructors -----------------------------------------

	public AuditService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Audit create(int propertyId) {
		Audit result;
		Date momentWritten;
		Property property;
		Auditor auditor;
		Boolean auditExisting;

		property = propertyService.findOne(propertyId);
		auditor = auditorService.findByPrincipal();
		auditExisting = false;
		for (Audit a : auditor.getAudits()) {
			if (a.getProperty().equals(property)) {
				auditExisting = true;
				break;
			}
		}

		Assert.isTrue(!auditExisting, "Cannot create an audit of this property");
		result = new Audit();
		momentWritten = new Date(System.currentTimeMillis() - 1000);
		result.setMomentWritten(momentWritten);
		result.setProperty(property);
		result.setAuditor(auditor);

		return result;
	}

	public Audit findOne(int auditId) {
		Audit result;

		result = auditRepository.findOne(auditId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Audit> findAll() {
		Collection<Audit> result;

		result = auditRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Audit save(Audit audit) {
		Assert.notNull(audit);

		Audit result;
		Auditor auditor;
		Date momentWritten;
		Property property;

		if (audit.getId() == 0) {
			auditor = auditorService.findByPrincipal();
			auditor.getAudits().add(audit);
			auditorService.save(auditor);
			property = audit.getProperty();
			property.getAudits().add(audit);
			propertyService.save(property);
		}

		momentWritten = new Date(System.currentTimeMillis() - 1000);
		audit.setMomentWritten(momentWritten);
		audit.setDraft(false);
		result = auditRepository.save(audit);

		return result;
	}

	public void delete(Audit audit) {
		Assert.notNull(audit);
		Assert.isTrue(audit.getId() != 0);

		Assert.isTrue(auditRepository.exists(audit.getId()));
		Assert.isTrue(audit.getDraft(), "Can't delete an audit published");

		Auditor auditor;
		Property property;
		Collection<Attachment> attachments;

		auditor = auditorService.findByPrincipal();
		for (Audit aud : auditor.getAudits()) {
			if (aud.equals(audit)) {
				property = aud.getProperty();
				property.getAudits().remove(audit);
				propertyService.save(property);
				break;
			}
		}
		attachments = attachmentService.findAll();

		auditor.getAudits().remove(audit);
		auditorService.save(auditor);

		for (Attachment a : attachments) {
			if (a.getAudit().equals(audit)) {
				attachmentService.delete(a);
			}
		}

		auditRepository.delete(audit);
	}

	// Other business methods -------------------------------

	public Audit saveAsDraft(Audit audit) {
		Assert.notNull(audit);

		Audit result;
		Auditor auditor;
		Date momentWritten;
		Property property;

		if (audit.getId() == 0) {
			auditor = auditorService.findByPrincipal();
			auditor.getAudits().add(audit);
			auditorService.save(auditor);
			property = audit.getProperty();
			property.getAudits().add(audit);
			propertyService.save(property);
		}

		momentWritten = new Date(System.currentTimeMillis() - 1000);
		audit.setMomentWritten(momentWritten);
		audit.setDraft(true);
		result = auditRepository.save(audit);

		return result;
	}

	public Collection<Audit> findAuditsPublishedByProperty(int propertyId) {
		Collection<Audit> result;

		result = auditRepository.findAuditsPublishedByProperty(propertyId);
		Assert.notNull(result);

		return result;
	}

	public Double findMinAuditsOfProperties() {
		Double result;

		result = auditRepository.findMinAuditsOfProperties();
		Assert.notNull(result);

		return result;
	}

	public Double findAvgAuditsOfProperties() {
		Double result;

		result = auditRepository.findAvgAuditsOfProperties();
		Assert.notNull(result);

		return result;
	}

	public Double findMaxAuditsOfProperties() {
		Double result;

		result = auditRepository.findMaxAuditsOfProperties();
		Assert.notNull(result);

		return result;
	}

	public Collection<Attachment> findAttachmentsByAudit(int auditId) {
		Collection<Attachment> result;

		result = auditRepository.findAttachmentsByAudit(auditId);

		return result;
	}

	public Audit findOneToEdit(int auditId, int propertyId) {
		Audit result;
		Property property;

		result = findOne(auditId);
		property = propertyService.findOne(propertyId);

		Assert.notNull(result);
		Assert.notNull(property);

		Assert.isTrue(result.getProperty().equals(property));

		return result;
	}

	public void flush() {
		auditRepository.flush();
	}

	public Audit reconstruct(Audit audit, int propertyId, BindingResult binding) {
		Audit result;
		Auditor auditor;

		if (audit.getId() == 0) {
			Property property;
			auditor = auditorService.findByPrincipal();
			result = audit;
			property = propertyService.findOne(propertyId);
			result.setAuditor(auditor);
			result.setProperty(property);
		} else {
			Audit aux;
			aux = findOne(audit.getId());
			result = audit;
			result.setAuditor(aux.getAuditor());
			result.setProperty(aux.getProperty());
		}

		validator.validate(result, binding);

		return result;
	}

}
