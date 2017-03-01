
package forms;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import domain.CreditCard;

public class CreditCardForm {

	private int		id;
	private int		version;
	private String	holderName;
	private String	brandName;
	private String	number;
	private int		cvv;
	private String	date;


	public CreditCardForm() {
		super();
	}

	public CreditCardForm(CreditCard creditCard) {
		DateTimeFormatter dateFormat;
		dateFormat = DateTimeFormat.forPattern("dd/MM/yyyy");

		this.id = creditCard.getId();
		this.version = creditCard.getVersion();
		this.holderName = creditCard.getHolderName();
		this.brandName = creditCard.getBrandName();
		this.number = creditCard.getNumber();
		this.cvv = creditCard.getCvv();
		this.date = dateFormat.print(creditCard.getExpiryDate().getTime());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@NotBlank
	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@CreditCardNumber
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Range(min = 100, max = 999)
	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@NotEmpty
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
