package wild.protection.dto.request;

public class ChangePassword {

	private String oldpassword;
	private String newPassword;
	private String confrimPassword;
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfrimPassword() {
		return confrimPassword;
	}
	public void setConfrimPassword(String confrimPassword) throws Exception {
		if(this.confrimPassword!=this.newPassword) {
			throw new Exception("confirm Password Not matched with newPassword");
		}
		this.confrimPassword = confrimPassword;
	}
	
	
	
	
}
