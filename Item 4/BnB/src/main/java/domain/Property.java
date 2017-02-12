
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Property extends DomainEntity {

	// Attributes
	private String	name;
	private Double	rate;
	private String	description;
	private String	address;


	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Digits(integer = 9, fraction = 2)
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	// Relationships
	private Lessor					lessor;
	private Collection<Book>		books;
	private Collection<Attribute>	attributes;
	private Collection<Audit>		audits;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Lessor getLessor() {
		return lessor;
	}

	public void setLessor(Lessor lessor) {
		this.lessor = lessor;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "property")
	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	@NotNull
	@Valid
	@ManyToMany(mappedBy = "properties")
	public Collection<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Collection<Attribute> attributes) {
		this.attributes = attributes;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "property")
	public Collection<Audit> getAudits() {
		return audits;
	}

	public void setAudits(Collection<Audit> audits) {
		this.audits = audits;
	}

}
