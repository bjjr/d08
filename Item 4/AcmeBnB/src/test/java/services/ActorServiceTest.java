
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;
import domain.Actor;
import domain.Tenant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ActorServiceTest extends AbstractTest {

	// Service under test -----------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private TenantService		tenantService;


	// Tests ------------------------------------------

	/*
	 * Simulate the login of admin1 and check that the principal it's
	 * that administrator by comparing the name.
	 */

	@Test
	public void testFindByPrincipal() {
		authenticate("admin");
		Actor a;
		a = actorService.findByPrincipal();
		Assert.isTrue(a.getName().equals("admin1Name"));
		unauthenticate();
	}

	/*
	 * Given the ids of an actor an his user account check if
	 * findByUserAccountId returns the same actor
	 */

	@Test
	public void testFindByUserAccount() {
		UserAccount ua;
		ua = userAccountService.findByUsername("admin");
		Actor a;
		a = actorService.findOne(12);
		Assert.isTrue(actorService.findByUserAccount(ua).equals(a));
	}

	@Test
	public void testSave() {
		authenticate("tenant1");

		Tenant t;
		t = tenantService.findByPrincipal();

		t.setName("tenant1");

		Actor saved;
		saved = actorService.save(t);
		actorService.flush();

		Assert.isTrue(saved.getName().equals("tenant1"));

		unauthenticate();
	}
}
