
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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


	// Constructors -----------------------------------------

	public AuditService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Audit create() {
		Audit result;

		result = new Audit();

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

		if (audit.getId() == 0) {
			auditor = auditorService.findByPrincipal();
			auditor.getAudits().add(audit);
			auditorService.save(auditor);
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

		auditor = audit.getAuditor();
		property = audit.getProperty();
		attachments = attachmentService.findAll();

		auditor.getAudits().remove(audit);
		property.getAudits().remove(audit);

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

		if (audit.getId() == 0) {
			auditor = auditorService.findByPrincipal();
			auditor.getAudits().add(audit);
			auditorService.save(auditor);
		}

		momentWritten = new Date(System.currentTimeMillis() - 1000);
		audit.setMomentWritten(momentWritten);
		audit.setDraft(true);
		result = auditRepository.save(audit);

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

	public void flush() {
		auditRepository.flush();
	}

}
