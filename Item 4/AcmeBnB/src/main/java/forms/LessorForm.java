
package forms;

import domain.Lessor;

public class LessorForm {

	private Lessor	lessor;
	private String	confirmPassword;
	private boolean	eula;


	public LessorForm() {
		this.confirmPassword = "";
		this.eula = false;
	}

	public LessorForm(Lessor lessor) {
		this();
		this.lessor = lessor;
	}

	public Lessor getLessor() {
		return lessor;
	}

	public void setLessor(Lessor lessor) {
		this.lessor = lessor;
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
