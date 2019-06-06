package com.my.project.Mapper;



import com.my.project.entity.BsxxBean;
import com.my.project.entity.CsOdds;

import java.util.List;
import java.util.Map;

/**
 * 操作赔率方法--初始赔率  最终赔率  竞彩截止前特定时间赔率
 * @author Administrator
 *
 */
public interface OddsDao {
	List<Map<String,Object>>selectJcBs();
	/**
	 * 保存初始赔率
	 * @param csOdds
	 */
	public void saveCp(CsOdds csOdds);

	/**
	 * 保存最终赔率
	 * @param csOdds
	 */
	public void saveZp(CsOdds csOdds);

	/**
	 * 更新最终赔率
	 * @param csOdds
	 */
	public void deleteZp(CsOdds csOdds);
	public void createTable(Map<String,String> map);

	/**
	 * 让球终赔
	 * @param csOdds
	 */
	public void saveRqJcsp(CsOdds csOdds);
	public void deleteRqJcsp(CsOdds csOdds);

}
