
package forms;

import java.util.Collection;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;
import domain.Book;
import domain.Comment;
import domain.CreditCard;
import domain.Finder;
import domain.Invoice;
import domain.SocialIdentity;
import domain.Tenant;

public class TenantForm {

	private Integer						id;
	private Integer						version;
	private String						name;
	private String						surname;
	private String						email;
	private String						phone;
	private String						picture;
	private CreditCard					creditCard;
	private Collection<Invoice>			invoices;
	private Finder						finder;
	private Collection<Book>			books;
	private Collection<SocialIdentity>	socialIdentities;
	private UserAccount					userAccount;
	private Collection<Comment>			comments;

	// Form
	private String						confirmPassword;
	private boolean						eula;


	public TenantForm() {
		super();
		this.confirmPassword = "";
		this.eula = false;
	}

	/**
	 * Crear objeto TenantForm a partir de un Tenant. Es necesario inicializar todas sus propiedades.
	 * 
	 * @param tenant
	 *            Objeto TenantForm para vista
	 */

	public TenantForm(Tenant tenant) {
		this();
		this.id = tenant.getId();
		this.version = tenant.getVersion();
		this.name = tenant.getName();
		this.surname = tenant.getSurname();
		this.email = tenant.getEmail();
		this.phone = tenant.getPhone();
		this.picture = tenant.getPicture();
		this.creditCard = tenant.getCreditCard();
		this.invoices = tenant.getInvoices();
		this.finder = tenant.getFinder();
		this.books = tenant.getBooks();
		this.socialIdentities = tenant.getSocialIdentities();
		this.userAccount = tenant.getUserAccount();
		this.comments = tenant.getComments();
	}

	/**
	 * Reconstruir Tenant a partir de TenantForm
	 * 
	 * @return Tenant Objeto Tenant original
	 */
	public Tenant getTenant() {
		Tenant tenant = new Tenant();
		tenant.setId(id);
		tenant.setVersion(version);
		tenant.setName(name);
		tenant.setSurname(surname);
		tenant.setEmail(email);
		tenant.setPhone(phone);
		tenant.setPicture(picture);
		tenant.setCreditCard(creditCard);
		tenant.setInvoices(invoices);
		tenant.setFinder(finder);
		tenant.setBooks(books);
		tenant.setSocialIdentities(socialIdentities);
		tenant.setUserAccount(userAccount);
		tenant.setComments(comments);

		return tenant;
	}

	public Collection<SocialIdentity> getSocialIdentities() {
		return socialIdentities;
	}

	public void setSocialIdentities(Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Collection<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Collection<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Finder getFinder() {
		return finder;
	}

	public void setFinder(Finder finder) {
		this.finder = finder;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Pattern(regexp = "^\\+\\d{1,4}[\\s\\S]+$")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isEula() {
		return eula;
	}

	public void setEula(boolean eula) {
		this.eula = eula;
	}

}
