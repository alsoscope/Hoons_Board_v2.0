package kr.pe.hoon.domain;

public class LikeVO {
	private int lno;
	private int bno;
	private String uid;
	private int like;
	
	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
	@Override
	public String toString() {
		return "LikeVO [lno=" + lno + ", bno=" + bno + ", uid=" + uid + ", like=" + like + "]";
	}
	
}
