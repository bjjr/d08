
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
public class Attachment extends DomainEntity {

	// Attributes
	private String	path;


	@NotBlank
	@URL
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	// Relationships
	private Audit	audit;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

}
