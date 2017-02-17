
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TenantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Book;
import domain.CreditCard;
import domain.Finder;
import domain.Invoice;
import domain.SocialIdentity;
import domain.Tenant;

@Service
@Transactional
public class TenantService {

	@Autowired
	private TenantRepository	tenantRepository;


	public Tenant create() {
		Tenant tenant;
		Authority authority;
		UserAccount userAccount;

		userAccount = new UserAccount();
		userAccount.setUsername("");
		userAccount.setPassword("");

		tenant = new Tenant();
		tenant.setName("");
		tenant.setSurname("");
		tenant.setEmail("");
		tenant.setPhone("");
		tenant.setPicture("");
		tenant.setSocialIdentities(new ArrayList<SocialIdentity>());
		tenant.setUserAccount(userAccount);

		tenant.setCreditCard(new CreditCard());

		tenant.setInvoices(new ArrayList<Invoice>());
		tenant.setFinder(new Finder());
		tenant.setBooks(new ArrayList<Book>());

		authority = new Authority();
		authority.setAuthority("TENANT");
		tenant.getUserAccount().addAuthority(authority);

		return tenant;
	}

	public Tenant save(Tenant tenant) {
		Tenant result, authenticatedTenant;
		Assert.notNull(tenant);

		if (tenant.getId() == 0) {
			authenticatedTenant = findByPrincipal();
			Assert.isTrue(tenant.equals(authenticatedTenant));
		} else {
			Assert.isTrue(tenant.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("TENANT"));
			tenant.getUserAccount().setPassword(hashCodePassword(tenant.getUserAccount().getPassword()));
		}
		result = tenantRepository.save(tenant);
		Assert.notNull(result);

		return result;
	}

	public Tenant findOne(int id) {
		return tenantRepository.findOne(id);
	}

	public Collection<Tenant> findAll() {
		return tenantRepository.findAll();
	}

	public void flush() {
		tenantRepository.flush();
	}

	public Tenant findByPrincipal() {
		return tenantRepository.findByUserAccount(LoginService.getPrincipal().getId());
	}

	public String hashCodePassword(String password) {
		String result;
		Md5PasswordEncoder encoder;

		encoder = new Md5PasswordEncoder();
		result = encoder.encodePassword(password, null);

		return result;
	}

}
