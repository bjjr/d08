
package forms;

import domain.CreditCard;

public class CreditCardForm {

	private CreditCard	creditCard;
	private String		date;


	public CreditCardForm() {
		this.date = "";
	}

	public CreditCardForm(CreditCard creditCard) {
		this();
		this.creditCard = creditCard;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
