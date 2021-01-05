package domain;

public class DbVO {
	private int number;
	private String addressname;
	private String ispublic;
	private String username;
	private String adddate;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getAddressname() {
		return addressname;
	}
	public void setAddressname(String addressname) {
		this.addressname = addressname;
	}
	public String getIspublic() {
		return ispublic;
	}
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAdddate() {
		return adddate;
	}
	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}
	
	@Override
	public String toString() {
		return "DbVO [number=" + number + ", addressname=" + addressname + ", ispublic=" + ispublic + ", username="
				+ username + ", adddate=" + adddate + "]";
	}
}
