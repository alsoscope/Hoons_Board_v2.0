package kr.pe.hoon.dto;

public class LoginDTO {
	private String uid;
	private String upw;
	private boolean usecookie;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public boolean isUsecookie() {
		return usecookie;
	}
	public void setUsecookie(boolean usecookie) {
		this.usecookie = usecookie;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [uid=" + uid + ", upw=" + upw + ", usecookie=" + usecookie + "]";
	}
	
}
