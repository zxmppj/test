package com.my.project.Mapper;


import com.my.project.entity.LsxxBean;

import java.util.List;

/**
 * 操作联赛信息的DAO
 * @author Administrator
 *
 */
public interface LsxxDao {
	/**
	 * 保存联赛信息的方法
	 * @param bean
	 * @return
	 */
	public int saveLsxx(LsxxBean bean);
	/**
	 * 查询表中的最大fid
	 * @return
	 */
	public int getMaxFid();
	/**
	 * 
	 * @param name
	 * @return LsxxBean
	 */
	public LsxxBean getByName(String name);
	/**
	 * 获取所有的联赛信息
	 * @return
	 */
	public List<LsxxBean> getLsxx();
}
