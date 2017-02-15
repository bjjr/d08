
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity {

	// Attributes
	private double	value;


	@Digits(integer = 9, fraction = 2)
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
