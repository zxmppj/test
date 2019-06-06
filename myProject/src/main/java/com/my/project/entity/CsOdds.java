package com.my.project.entity;

public class CsOdds extends Basebean {
	private String fjcid;
	private Double win;
	private Double lose;
	private Double draw;
	private String fid;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFjcid() {
		return fjcid;
	}

	public void setFjcid(String fjcid) {
		this.fjcid = fjcid;
	}

	public Double getWin() {
		return win;
	}

	public void setWin(Double win) {
		this.win = win;
	}

	public Double getLose() {
		return lose;
	}

	public void setLose(Double lose) {
		this.lose = lose;
	}

	public Double getDraw() {
		return draw;
	}

	public void setDraw(Double draw) {
		this.draw = draw;
	}
}
