package com.my.project.entity;
/**
 * 联赛信息实体bean
 * @author Administrator
 *
 */
public class LsxxBean extends Basebean {
	private Integer  lsid;
	private String lsmc;
	private Integer sfbs;//是否是杯赛

	public Integer getLsid() {
		return lsid;
	}

	public void setLsid(Integer lsid) {
		this.lsid = lsid;
	}

	public String getLsmc() {
		return lsmc;
	}
	public void setLsmc(String lsmc) {
		this.lsmc = lsmc;
	}
	public Integer getSfbs() {
		return sfbs;
	}
	public void setSfbs(Integer sfbs) {
		this.sfbs = sfbs;
	}
}
