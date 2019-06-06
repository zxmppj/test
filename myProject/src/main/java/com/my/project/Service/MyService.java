package com.my.project.Service;

import java.util.List;
import java.util.Map;

public interface MyService {
    /**
     * 同步即时盘信息
     * @return
     */
     int getJspOdds(String dataStr);

    /**
     * 查询竞彩比赛信息
     * @return
     */
 List<Map<String,Object>>selectJcBs(String flag);
 String getRqOdds(String datastr);

    /**
     * 获取分析结果
     * @param fjcid
     * @return
     */
 Map<String,Object> getFxResult(String fjcid);
 List<Map<String,Object>> oddsCpFx(String fjcid,String checkedWin,String checkedDraw,String checkedLose,String checkedTime);
 List<Map<String,Object>> oddsJpFx(String fjcid,String checkedWin,String checkedDraw,String checkedLose,String checkedTime);
    List<Map<String,Object>> roddsJpFx(String fjcid,String checkedWin,String checkedDraw,String checkedLose,String checkedTime);
}
