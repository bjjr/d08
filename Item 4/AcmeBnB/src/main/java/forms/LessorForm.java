
package forms;

import java.util.Collection;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;
import domain.Comment;
import domain.CreditCard;
import domain.Lessor;
import domain.Property;
import domain.SocialIdentity;

public class LessorForm {

	private Integer						id;
	private Integer						version;
	private String						name;
	private String						surname;
	private String						email;
	private String						phone;
	private String						picture;
	private CreditCard					creditCard;
	private Collection<Property>		properties;
	private Collection<SocialIdentity>	socialIdentities;
	private UserAccount					userAccount;
	private Collection<Comment>			comments;
	private double						accumulatedCharges;

	// Form
	private String						confirmPassword;
	private boolean						eula;


	public LessorForm() {
		super();
		this.confirmPassword = "";
		this.eula = false;
	}

	/**
	 * Crear objeto LessorForm a partir de un Lessor. Es necesario inicializar todas sus propiedades.
	 * 
	 * @param lessor
	 *            Objeto LessorForm para vista
	 */

	public LessorForm(Lessor lessor) {
		this();
		this.id = lessor.getId();
		this.version = lessor.getVersion();
		this.name = lessor.getName();
		this.surname = lessor.getSurname();
		this.email = lessor.getEmail();
		this.phone = lessor.getPhone();
		this.picture = lessor.getPicture();
		this.creditCard = lessor.getCreditCard();
		this.properties = lessor.getProperties();
		this.socialIdentities = lessor.getSocialIdentities();
		this.userAccount = lessor.getUserAccount();
		this.comments = lessor.getComments();
		this.accumulatedCharges = lessor.getAccumulatedCharges();
	}
	/**
	 * Reconstruir Lessor a partir de LessorForm
	 * 
	 * @return Lessor Objeto Lessor original
	 */
	public Lessor getLessor() {
		Lessor lessor = new Lessor();
		lessor.setId(id);
		lessor.setVersion(version);
		lessor.setName(name);
		lessor.setSurname(surname);
		lessor.setEmail(email);
		lessor.setPhone(phone);
		lessor.setPicture(picture);
		lessor.setCreditCard(creditCard);
		lessor.setProperties(properties);
		lessor.setSocialIdentities(socialIdentities);
		lessor.setUserAccount(userAccount);
		lessor.setComments(comments);
		lessor.setAccumulatedCharges(accumulatedCharges);

		return lessor;
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

	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

	public double getAccumulatedCharges() {
		return accumulatedCharges;
	}

	public void setAccumulatedCharges(double accumulatedCharges) {
		this.accumulatedCharges = accumulatedCharges;
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
