
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.TenantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Book;
import domain.CreditCard;
import domain.Finder;
import domain.Invoice;
import domain.Tenant;
import forms.TenantForm;

@Service
@Transactional
public class TenantService {

	@Autowired
	private TenantRepository		tenantRepository;

	@Autowired
	private ConsumerActorService	consumerActorService;

	@Autowired
	private Validator				validator;


	public Tenant create() {
		Tenant tenant;
		Authority authority;
		UserAccount userAccount;

		userAccount = new UserAccount();
		userAccount.setUsername("");
		userAccount.setPassword("");

		tenant = new Tenant();
		consumerActorService.setConsumerActorProperties(tenant);

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

		if (tenant.getId() != 0) {
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

	@Transactional(readOnly = true)
	public Tenant reconstruct(TenantForm tenantForm, BindingResult binding) {
		Tenant result;

		if (tenantForm.getTenant().getId() == 0) {
			result = tenantForm.getTenant();
		} else {
			result = tenantRepository.findOne(tenantForm.getTenant().getId());

			result.setName(tenantForm.getName());
			result.setSurname(tenantForm.getSurname());
			result.setEmail(tenantForm.getEmail());
			result.setPhone(tenantForm.getPhone());
			result.setPicture(tenantForm.getPicture());

			//result.getUserAccount().setUsername(tenantForm.getTenant().getUserAccount().getUsername());
			//result.getUserAccount().setPassword(tenantForm.getTenant().getUserAccount().getPassword());

			validator.validate(result, binding);
		}

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

	// Other business methods -------------------------------

	public Double avgAcceptedPerTenant() {
		return tenantRepository.avgAcceptedPerTenant();
	}

	public Double avgDeniedPerTenant() {
		return tenantRepository.avgDeniedPerTenant();
	}

	public Collection<Tenant> tenantsMoreRequestsApproved() {
		return tenantRepository.tenantsMoreRequestsApproved();
	}

	public Collection<Tenant> tenantsMoreRequestsDenied() {
		return tenantRepository.tenantsMoreRequestsDenied();
	}

	public Collection<Tenant> tenantsMoreRequestsPending() {
		return tenantRepository.tenantsMoreRequestsPending();
	}

	public String hashCodePassword(String password) {
		String result;
		Md5PasswordEncoder encoder;

		encoder = new Md5PasswordEncoder();
		result = encoder.encodePassword(password, null);

		return result;
	}

}
