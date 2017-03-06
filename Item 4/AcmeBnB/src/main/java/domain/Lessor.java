
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Lessor extends ConsumerActor {

	private double	accumulatedCharges;


	@Digits(integer = 9, fraction = 2)
	@Min((long) 0.0)
	public double getAccumulatedCharges() {
		return accumulatedCharges;
	}

	public void setAccumulatedCharges(double accumulatedCharges) {
		this.accumulatedCharges = accumulatedCharges;
	}


	// Relationships
	private Collection<Property>	properties;


	@NotNull
	@OneToMany(mappedBy = "lessor")
	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

}
