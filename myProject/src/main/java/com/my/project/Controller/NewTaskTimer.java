package com.my.project.Controller;

import com.my.project.Common.CommonConst;
import com.my.project.Mapper.BsxxDao;
import com.my.project.Mapper.LsxxDao;
import com.my.project.Mapper.OddsDao;
import com.my.project.entity.BsxxBean;
import com.my.project.entity.CsOdds;
import com.my.project.entity.LsxxBean;
import com.my.project.utill.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NewTaskTimer {
    @Autowired
    private LsxxDao lsxxDao;
    @Autowired
    private OddsDao oddsDao;
    @Autowired
    private BsxxDao bsxxDao;
    /**
     * 获取下一日的比赛信息以及初始赔率
     */
    @Scheduled(cron="0 57 23 * * ?")
    public void getNextBsxx(){
        if(CommonConst.lsxxMap.isEmpty()){
            List<LsxxBean> beanList = lsxxDao.getLsxx();
            CommonConst.putLsxxMap(beanList);
        }
        String strdate = CommonConst.getDate("yyyyMMdd", 1);
        String  strUrl = CommonConst.URL+"/"+strdate+".js";
        String line1 = HttpRequest.sendGet(strUrl);
        try{
            getResultMap(line1,strdate);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * 获取前一日的比赛结果
     */
    @Scheduled(cron="0 30 14 * * ?")
    public void getPreRes(){

    }

    /**
     * 获取赔率变化
     */
    @Scheduled(cron="0 0/5 * * * ?")
    public void getJOdds(){
        String strdate = CommonConst.getNow("yyyyMMdd");
        Map<String,String> map = new HashMap<>();
        map.put("dateStr",strdate);
        List<Map<String,Object>> bsList= bsxxDao.selectJcfid(map);
        for (Map<String,Object> hashMap:bsList) {
            String url = "https://odds.139cai.com/json/match/oddseurope?mid="+hashMap.get("fid")+"&lotyid=6";
            String line1 = HttpRequest.sendGet(url);
            readOp(line1,hashMap.get("fjcid").toString());
        }
    }
    /**
     * 保存比赛信息的方法
     * @param line
     * @param dateStr
     * @throws Exception
     */
    public void getResultMap(String line, String dateStr) throws Exception{
        String [] strs=null;
        if(line.startsWith("var Flagnum")){
            strs = line.split(";");
            List<BsxxBean> list = new ArrayList<BsxxBean>();
            for(int i=2;i<strs.length;i++){
                HashMap<String,Object> map = new HashMap<String,Object>();
                strs[i] = strs[i].replace("zcdz", "").replace("'", "").replace("[", "").replace("]", "").replace("=", ",");
                String[] strss= strs[i].split(",");

                if(!CommonConst.lsxxMap.containsKey(strss[2])) {
                    Integer fid=0;
                    try {
                        fid = lsxxDao.getMaxFid();
                    } catch (Exception e) {
                        fid=0;
                    }
                    fid++;
                    LsxxBean bean = new LsxxBean();
                    bean.setLsid(fid);
                    bean.setLsmc(strss[2]);
                    if(strss[2].indexOf("杯")>-1){
                        bean.setSfbs(1);
                    }else{
                        bean.setSfbs(0);
                    }
                    lsxxDao.saveLsxx(bean);
                    CommonConst.lsxxMap.put(strss[2], bean);
                }
                BsxxBean bsxx = new BsxxBean();
                bsxx.setFjcid(strss[0]);//比赛id
                Integer flsid = CommonConst.lsxxMap.get(strss[2]).getLsid();//联赛id
                if(null==flsid){
                    flsid=0;
                }
                bsxx.setFlsid(flsid);
                bsxx.setFlsmc(strss[2]);
                bsxx.setFzdmc(strss[10]);//主队名称
                bsxx.setFkdmc(strss[11]);//客队名称
                bsxx.setFjcjzsj(dateStr);//竞彩截止时间
                bsxx.setFbssj(strss[4]+strss[5]+strss[6]+strss[7]+strss[8]);//比赛时间
                String rqstr ="";
                bsxx.setFrq(strss[strss.length-4]);//让球
                rqstr = strss[strss.length-4];
                if(strss.length<=38){
                    bsxx.setFrq(strss[35]);//让球
                    rqstr = strss[35];
                }
                if("".equals(rqstr)){
                    bsxx.setFrq("0");//让球
                    rqstr = "0";
                }
                bsxx.setFxq(CommonConst.getXq(dateStr,"yyyyMMdd"));
                int zdjq = Integer.parseInt("".equals(strss[12])?"0":strss[12]);
                bsxx.setFzdjq(zdjq);//主队进球
                int kdjq = Integer.parseInt("".equals(strss[13])?"0":strss[13]);
                bsxx.setFkdjq(kdjq);//客队进球
                if(zdjq>kdjq){
                    bsxx.setFres("3");
                }else if(zdjq==kdjq){
                    bsxx.setFres("1");
                }else{
                    bsxx.setFres("0");
                }
                if(rqstr.indexOf("-")>-1){
                    int rq = Integer.parseInt(rqstr.replace("-",""));
                    if(zdjq-kdjq>rq){
                        bsxx.setFrqres("3");
                    }else if(zdjq-kdjq==rq){
                        bsxx.setFrqres("1");
                    }else{
                        bsxx.setFrqres("0");
                    }
                }else{
                    int rq = Integer.parseInt(rqstr);
                    if(kdjq-zdjq>rq){
                        bsxx.setFrqres("0");
                    }else if(kdjq-zdjq==rq){
                        bsxx.setFrqres("1");
                    }else{
                        bsxx.setFrqres("3");
                    }
                }

                bsxx.setFkdid(0);//客队id
                bsxx.setFzdid(0);//主队id
                bsxx.setFid(strss[1]);
                list.add(bsxx);
            }
                try {
                    bsxxDao.saveBsxxList(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 解析欧赔
     */
    public void readOp(String line ,String fjcid){
        String [] strs = line.split("\\[")[1].split("]")[0].split("}}");
        for ( int i =0;i<strs.length;i++){
            String [] ops= strs[i].split("\\{");
            String [] cp =ops[2].split(",");
            String [] zp =ops[3].split(",");
            String zpwin = zp[0].split(":")[1].replace("\"","");
            String zpdraw = zp[1].split(":")[1].replace("\"","");
            String zplose = zp[2].split(":")[1].replace("\"","");

            String []ids = ops[4].split(",");
            String id = ids[ids.length-1].split(":")[1].replace("\"","");//欧赔公司id

            CsOdds hisodds = new CsOdds();
            hisodds.setFjcid(fjcid);
            hisodds.setWin(Double.parseDouble("".equals(zpwin)?"0":zpwin));
            hisodds.setDraw(Double.parseDouble("".equals(zpdraw)?"0":zpdraw));
            hisodds.setLose(Double.parseDouble("".equals(zplose)?"0":zplose));
            hisodds.setFid(id);
            try{
                if("10000".equals(id)||"30".equals(id)||"449".equals(id)||"451".equals(id)||"211".equals(id)){
                    String key = fjcid+","+zpwin+","+zpdraw+","+zplose;
                    if(!CommonConst.hisOddsMap.containsKey(key)){
                        CommonConst.hisOddsMap.put(key,"");
                        /**
                         * 保存至竞彩历史赔率表
                         */
                    }
//                    oddsDao.saveZp(zpodds);
                }
            }catch (Exception e){

            }
        }

    }
}
