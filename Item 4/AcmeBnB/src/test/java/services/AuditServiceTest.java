
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
import domain.Audit;
import domain.Auditor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditServiceTest extends AbstractTest {

	// Service under test --------------------------------

	@Autowired
	private AuditService	auditService;

	@Autowired
	private AuditorService	auditorService;


	// Tests ---------------------------------------------

	@Test
	public void testCreateAudit() {
		authenticate("auditor1");

		Audit result;
		Auditor auditor;

		auditor = auditorService.findByPrincipal();
		result = auditService.create();
		Assert.isTrue(result.getAuditor().equals(auditor));
		Assert.isTrue(result.getText() == null);

		System.out.println("------- TEST CREATE -------");
		System.out.println("Audit created \n");

		unauthenticate();
	}

	@Test
	public void testFindOneAudit() {
		//TODO CAMBIAR ID AUDIT
		authenticate("auditor1");

		Audit result;
		int id = 49;

		result = auditService.findOne(id);

		System.out.println("------- TEST FIND ONE -------");
		System.out.println("Audit whose id is " + id + " found: " + result + "\n");

		unauthenticate();
	}

	@Test
	public void testFindAllAudit() {
		authenticate("auditor1");

		Collection<Audit> result;

		result = auditService.findAll();

		System.out.println("------- TEST FIND ALL -------");
		System.out.println(result.size() + " audits found \n");

		unauthenticate();
	}

	@Test
	public void testSaveAudit() {
		//TODO AÑADIR PROPERTY
		authenticate("auditor1");

		Audit audit;
		Audit result;
		Auditor auditor;
		Collection<Audit> audits;

		audit = auditService.create();
		auditor = auditorService.findByPrincipal();

		audit.setText("Text test");
		audit.setAuditor(auditor);

		result = auditService.save(audit);
		auditService.flush();

		audits = auditService.findAll();
		Assert.isTrue(audits.contains(result), "Error saving audit");
		Assert.isTrue(result.getDraft() == false, "Error: Audit published can't be a draft");

		System.out.println("------- TEST SAVE -------");
		System.out.println("Audit saved \n");

		unauthenticate();
	}

	@Test
	public void testSaveAsDraftAudit() {
		authenticate("auditor1");
		//TODO COPIAR EL SAVE
		unauthenticate();
	}

	@Test
	public void testDeleteAudit() {
		authenticate("auditor2");

		Audit audit;
		//TODO PONER ID DE UNA AUDIT DEL AUDITOR2
		audit = auditService.findOne(49);

		auditService.delete(audit);
		Assert.isTrue(!auditService.findAll().contains(audit), "Error deleting audit");

		System.out.println("------- TEST DELETE -------");
		System.out.println("Audit deleted correctly \n");

		unauthenticate();
	}
}
