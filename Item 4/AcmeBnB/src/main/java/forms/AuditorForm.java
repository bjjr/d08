
package forms;

import java.util.Collection;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;
import domain.Audit;
import domain.Auditor;
import domain.Comment;
import domain.SocialIdentity;

public class AuditorForm {

	private Integer						id;
	private Integer						version;
	private String						name;
	private String						surname;
	private String						email;
	private String						phone;
	private String						picture;
	private String						companyName;
	private UserAccount					userAccount;
	private Collection<SocialIdentity>	socialIdentities;
	private Collection<Audit>			audits;
	private Collection<Comment>			comments;

	// Form ----------------------------------------

	private String						confirmPassword;


	public AuditorForm() {
		super();
		this.confirmPassword = "";
	}

	public AuditorForm(Auditor auditor) {
		this();
		this.id = auditor.getId();
		this.version = auditor.getVersion();
		this.name = auditor.getName();
		this.surname = auditor.getSurname();
		this.email = auditor.getEmail();
		this.phone = auditor.getPhone();
		this.picture = auditor.getPicture();
		this.companyName = auditor.getCompanyName();
		this.userAccount = auditor.getUserAccount();
		this.socialIdentities = auditor.getSocialIdentities();
		this.audits = auditor.getAudits();
		this.comments = auditor.getComments();
	}

	public Auditor getAuditor() {
		Auditor result;

		result = new Auditor();
		result.setId(id);
		result.setVersion(version);
		result.setName(name);
		result.setSurname(surname);
		result.setEmail(email);
		result.setPhone(phone);
		result.setPicture(picture);
		result.setCompanyName(companyName);
		result.setUserAccount(userAccount);
		result.setSocialIdentities(socialIdentities);
		result.setAudits(audits);
		result.setComments(comments);

		return result;
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

	@NotBlank
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Collection<SocialIdentity> getSocialIdentities() {
		return socialIdentities;
	}

	public void setSocialIdentities(Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	public Collection<Audit> getAudits() {
		return audits;
	}

	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
