package com.divesh.entities;

public class Batch 
{
	private int bid;
	private int rno;
	private int tno;
	private int gid;
	

	public Batch() {}
	public Batch(int bid,int rno, int tno, int gid) {
		super();
		this.bid = bid;
		this.rno = rno;
		this.tno = tno;
		this.gid = gid;
	}

	public int getBid() {
		return bid;
	}


	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getGid() {
		return gid;
	}


	public void setGid(int gid) {
		this.gid = gid;
	}



	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	
	
}
