package kr.pe.hoon.domain;

import java.util.Date;

public class UserVO {
	private String uid;
	private String pw;
	private String name;
	private String gender;
	private String email;
	private String thumbnail;
	private Date joindate;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
	@Override
	public String toString() {
		return "UserVO [uid=" + uid + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", email=" + email
				+ ", thumbnail=" + thumbnail + ", joindate=" + joindate + "]";
	}
	
}
