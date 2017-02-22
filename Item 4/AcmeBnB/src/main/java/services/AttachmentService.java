
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

		Audit audit;

		audit = attachment.getAudit();
		audit.getAttachments().remove(attachment);
		auditService.save(audit);

		attachmentRepository.delete(attachment);
	}
	// Other business methods -------------------------------

	public void flush() {
		attachmentRepository.flush();
	}

}
