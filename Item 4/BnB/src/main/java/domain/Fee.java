
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity {

	// Attributes
	private Double	value;


	@Digits(integer = 9, fraction = 2)
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
