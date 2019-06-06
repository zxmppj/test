package com.my.project.Mapper;


import com.my.project.entity.BsxxBean;

import java.util.List;
import java.util.Map;

/**
 * 比赛信息相关操作
 * @author Administrator
 *
 */
public interface BsxxDao {
	/**
	 * 胜赔结果
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getWinRes(Map<String,String> map);
	/**
	 * 更新比赛信息
	 * @param bean
	 * @return
	 */
	 int deleteBsxx(BsxxBean bean);
	/**
	 * 批量保存比赛信息
	 * @param list
	 */
	 void saveBsxxList(List<BsxxBean> list);

	/**
	 * 保存比赛信息
	 */
	void saveBsxx(BsxxBean bean);

	/**
	 * 根据日期查询竞彩比赛信息
	 * @param map
	 * @return
	 */
	List<Map<String,Object>>selectJcBs(Map<String,String> map);
	/**
	 * 根据日期查询竞彩比赛信息
	 * @param map
	 * @return
	 */
	List<Map<String,Object>>selectJcfid(Map<String,String> map);

	/**
	 * 根据竞彩id竞彩初盘赔率
	 * @param map
	 * @return
	 */
	Map<String,Object>getOddsCpById(Map<String,String> map);

	/**
	 * 历史初陪相同的比赛结果
	 * @param map
	 * @return
	 */
	List<Map<String,Object>>getOddsCpRes(Map<String,Object> map);
	/**
	 * 根据竞彩id竞彩初盘赔率
	 * @param map
	 * @return
	 */
	Map<String,Object>getOddsJpById(Map<String,String> map);

	/**
	 * 历史初陪相同的比赛结果
	 * @param map
	 * @return
	 */
	List<Map<String,Object>>getOddsJpRes(Map<String,Object> map);
	/**
	 * 根据竞彩id竞彩初盘赔率
	 * @param map
	 * @return
	 */
	Map<String,Object>getROddsJpById(Map<String,String> map);

	/**
	 * 历史初陪相同的比赛结果
	 * @param map
	 * @return
	 */
	List<Map<String,Object>>getROddsJpRes(Map<String,Object> map);
}
