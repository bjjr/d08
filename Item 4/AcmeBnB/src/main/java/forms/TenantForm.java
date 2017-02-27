
package forms;

import domain.Tenant;

public class TenantForm {

	private Tenant	tenant;
	private String	confirmPassword;
	private boolean	eula;


	public TenantForm() {
		this.confirmPassword = "";
		this.eula = false;
	}

	public TenantForm(Tenant tenant) {
		this();
		this.tenant = tenant;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
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
