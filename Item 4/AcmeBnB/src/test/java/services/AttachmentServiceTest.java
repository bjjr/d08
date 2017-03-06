
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Attachment;
import domain.Audit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AttachmentServiceTest extends AbstractTest {

	// Service under test --------------------------------

	@Autowired
	private AttachmentService	attachmentService;

	@Autowired
	private AuditService		auditService;


	// Tests ---------------------------------------------

	@Test
	public void testCreateAttachment() {
		authenticate("auditor1");

		Attachment result;

		result = attachmentService.create();

		Assert.isTrue(result.getPath() == null, "Error creating attachment");

		System.out.println("------- TEST CREATE -------");
		System.out.println("Attachment created \n");

		unauthenticate();
	}

	@Test
	public void testFindOneAttachment() {
		//TODO CAMBIAR ID DEL ATTACHMENT
		authenticate("auditor1");

		Attachment result;
		int id = 49;

		result = attachmentService.findOne(id);

		System.out.println("------- TEST FIND ONE -------");
		System.out.println("Attachment whose id is " + id + " found: " + result + "\n");

		unauthenticate();
	}

	@Test
	public void testFindAllAttachment() {
		authenticate("auditor1");

		Collection<Attachment> attachments;

		attachments = attachmentService.findAll();

		System.out.println("------- TEST FIND ALL -------");
		System.out.println(attachments.size() + " attachments found \n");

		unauthenticate();
	}

	@Test
	public void testSaveAttachment() {
		// TODO COMPLETAR TEST SAVE ATTACHMENT
		authenticate("auditor1");

		Attachment attachment;
		Attachment result;
		Audit audit;

		attachment = attachmentService.create();
		audit = auditService.create(41);

		audit.setDraft(false);
		audit.setText("Text prueba");

		attachment.setPath("http://www.pathTest.com");
		attachment.setAudit(audit);

		result = attachmentService.save(attachment);
		attachmentService.flush();

		System.out.println("------- TEST SAVE -------");
		System.out.println("Attachment saved: " + result + "\n");

		unauthenticate();
	}

	@Test
	public void testDeleteAttachment() {
		//TODO CAMBIAR ID DEL ATTACHMENT
		authenticate("auditor1");

		Attachment attachment;

		attachment = attachmentService.findOne(49);

		attachmentService.delete(attachment);

		Assert.isTrue(!attachmentService.findAll().contains(attachment));

		System.out.println("------- TEST DELETE -------");
		System.out.println("Attachment deleted correctly \n");

		unauthenticate();
	}

}
