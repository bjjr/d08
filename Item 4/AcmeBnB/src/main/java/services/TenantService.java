
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import domain.Comment;
import domain.CreditCard;
import domain.Finder;
import domain.Invoice;
import domain.SocialIdentity;
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
		Authority auth;
		auth = new Authority();
		auth.setAuthority("TENANT");

		result = tenantForm.getTenant();

		result.getUserAccount().addAuthority(auth);
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		result.setComments(new ArrayList<Comment>());
		result.setInvoices(new ArrayList<Invoice>());
		result.setBooks(new ArrayList<Book>());

		validator.validate(result, binding);

		return result;
	}

	public Tenant reconstruct(Tenant tenant, BindingResult binding) {
		Tenant result;

		Tenant aux = findByPrincipal();
		result = tenant;

		result.setInvoices(aux.getInvoices());
		result.setFinder(aux.getFinder());
		result.setBooks(aux.getBooks());
		result.setCreditCard(aux.getCreditCard());
		result.setSocialIdentities(aux.getSocialIdentities());
		result.setUserAccount(aux.getUserAccount());
		result.setComments(aux.getComments());

		validator.validate(result, binding);

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

	public Tenant tenantMaxRatio() {
		List<Tenant> tenants;
		Tenant tenant;

		tenants = (List<Tenant>) tenantRepository.tenantMaxRatio();
		Assert.notNull(tenants);

		tenant = tenants.get(tenants.size() - 1);
		Assert.notNull(tenant);

		return tenant;
	}

	public Tenant tenantMinRatio() {
		List<Tenant> tenants;
		Tenant tenant;

		tenants = (List<Tenant>) tenantRepository.tenantMinRatio();
		Assert.notNull(tenants);

		tenant = tenants.get(tenants.size() - 1);
		Assert.notNull(tenant);

		return tenant;
	}

}
