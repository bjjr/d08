
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity {

	// Attributes
	private String	nick;
	private String	nameSN;
	private String	urlSN;


	@NotBlank
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@NotBlank
	public String getNameSN() {
		return nameSN;
	}

	public void setNameSN(String nameSN) {
		this.nameSN = nameSN;
	}

	@URL
	public String getUrlSN() {
		return urlSN;
	}

	public void setUrlSN(String urlSN) {
		this.urlSN = urlSN;
	}


	// Relationships
	private Actor	actor;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
