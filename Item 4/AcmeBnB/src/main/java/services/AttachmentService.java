
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AttachmentRepository;
import domain.Attachment;
import domain.Audit;

@Service
@Transactional
public class AttachmentService {

	// Managed repository -----------------------------------

	@Autowired
	private AttachmentRepository	attachmentRepository;

	// Supporting services ----------------------------------

	@Autowired
	private AuditService			auditService;

	// Validator --------------------------------------------

	@Autowired
	private Validator				validator;


	// Constructors -----------------------------------------

	public AttachmentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Attachment create() {
		Attachment result;

		result = new Attachment();

		return result;
	}

	public Attachment findOne(int attachmentId) {
		Attachment result;

		result = attachmentRepository.findOne(attachmentId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Attachment> findAll() {
		Collection<Attachment> result;

		result = attachmentRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Attachment save(Attachment attachment) {
		Assert.notNull(attachment);

		Attachment result;

		result = attachmentRepository.save(attachment);

		return result;
	}

	public void delete(Attachment attachment) {
		Assert.notNull(attachment);
		Assert.isTrue(attachment.getId() != 0);

		Assert.isTrue(attachmentRepository.exists(attachment.getId()));

		attachmentRepository.delete(attachment);
	}

	// Other business methods -------------------------------

	public void flush() {
		attachmentRepository.flush();
	}

	public Attachment findOneToEdit(int attachmentId, int auditId) {
		Attachment result;
		Audit audit;

		result = findOne(attachmentId);
		audit = auditService.findOne(auditId);

		Assert.notNull(result);
		Assert.notNull(audit);

		Assert.isTrue(result.getAudit().equals(audit));

		return result;
	}

	public Attachment reconstruct(Attachment attachment, int auditId, BindingResult binding) {
		Attachment result;

		if (attachment.getId() == 0) {
			Audit audit;
			result = attachment;
			audit = auditService.findOne(auditId);
			result.setAudit(audit);
		} else {
			Attachment aux;
			aux = findOne(attachment.getId());
			result = attachment;
			result.setAudit(aux.getAudit());
		}

		validator.validate(result, binding);

		return result;
	}

}
