package com.my.project.Service.impl;

import com.my.project.Common.CommonConst;
import com.my.project.Mapper.BsxxDao;
import com.my.project.Mapper.LsxxDao;
import com.my.project.Mapper.OddsDao;
import com.my.project.Service.MyService;
import com.my.project.entity.BsxxBean;
import com.my.project.entity.CsOdds;
import com.my.project.entity.LsxxBean;
import com.my.project.utill.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private LsxxDao lsxxDao;
    @Autowired
    private OddsDao oddsDao;
    @Autowired
    private BsxxDao bsxxDao;
    @Override
    public int getJspOdds(String dateStr) {
        try{
            if(CommonConst.lsxxMap.isEmpty()){
                List<LsxxBean> beanList = lsxxDao.getLsxx();
                CommonConst.putLsxxMap(beanList);
            }
            String  strUrl = CommonConst.URL+"/"+dateStr+".js";
            String line1 = HttpRequest.sendGet(strUrl);
               getResultMap(line1,dateStr);
            getRqOdds(dateStr);
        }catch(Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public List<Map<String,Object>> selectJcBs(String dateStr) {
        Map<String,String> map = new HashMap<>();
        map.put("dateStr",dateStr);
        return bsxxDao.selectJcBs(map);
    }

    @Override
    public String getRqOdds(String datastr) {
            String jcrqspzp = HttpRequest.sendGet("http://bf.139cai.com/mcache/jcrqsp/"+datastr+".js");
            String str = jcrqspzp.replace("var matchs_rqsp=new Array();", "").replace(",''", "").replace("matchs_rqsp", "").replace("'", "").replace("[", "").replace("]", "");
            String [] strs = str.split(";");
            List<CsOdds> list = new ArrayList<CsOdds>();
            for(int i=0;i<strs.length;i++){
                if(strs[i].contains("=")){
                    strs[i]= strs[i].replace("=", ",");
                    String[] strss= strs[i].split(",");
                    CsOdds csOdds = new CsOdds();
                    try {
                    csOdds.setFjcid(strss[0]);
                    csOdds.setWin(Double.valueOf(strss[1].replaceAll("--", "0")));
                    csOdds.setDraw(Double.valueOf(strss[2].replaceAll("--", "0")));
                    csOdds.setLose(Double.valueOf(strss[3].replaceAll("--", "0")));
                   oddsDao.deleteRqJcsp(csOdds);
                        oddsDao.saveRqJcsp(csOdds);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        return "ok";
    }

    @Override
    public Map<String, Object> getFxResult(String fjcid) {


        return null;
    }

    @Override
    public List<Map<String, Object>> oddsCpFx(String fjcid, String checkedWin, String checkedDraw, String checkedLose,String checkedTime) {
        Map<String,String> param = new HashMap<>();
        param.put("fjcid",fjcid);
        Map<String,Object> map = bsxxDao.getOddsCpById(param);
        map.put("checkedWin",checkedWin);
        map.put("checkedDraw",checkedDraw);
        map.put("checkedLose",checkedLose);
        map.put("checkedTime",checkedTime);//checkedTime
    return bsxxDao.getOddsCpRes(map);
    }
    @Override
    public List<Map<String, Object>> oddsJpFx(String fjcid, String checkedWin, String checkedDraw, String checkedLose,String checkedTime) {
        Map<String,String> param = new HashMap<>();
        param.put("fjcid",fjcid);
        Map<String,Object> map = bsxxDao.getOddsJpById(param);
        map.put("checkedWin",checkedWin);
        map.put("checkedDraw",checkedDraw);
        map.put("checkedLose",checkedLose);
        map.put("checkedTime",checkedTime);//checkedTime
        return bsxxDao.getOddsJpRes(map);
    }
    @Override
    public List<Map<String, Object>> roddsJpFx(String fjcid, String checkedWin, String checkedDraw, String checkedLose,String checkedTime) {
        Map<String,String> param = new HashMap<>();
        param.put("fjcid",fjcid);
        Map<String,Object> map = bsxxDao.getROddsJpById(param);
        map.put("checkedWin",checkedWin);
        map.put("checkedDraw",checkedDraw);
        map.put("checkedLose",checkedLose);//checkedTime
        map.put("checkedTime",checkedTime);//checkedTime
        return bsxxDao.getROddsJpRes(map);
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
                String url = "https://odds.139cai.com/json/match/oddseurope?mid="+strss[1]+"&lotyid=6";
                String line1 = HttpRequest.sendGet(url);
                readOp(line1,strss[0]);
                bsxxDao.deleteBsxx(bsxx);
                bsxxDao.saveBsxx(bsxx);
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
            String cpwin = cp[0].split(":")[1].replace("\"","");
            String cpdraw = cp[1].split(":")[1].replace("\"","");
            String cplose = cp[2].split(":")[1].replace("\"","");

            String [] zp =ops[3].split(",");
            String zpwin = zp[0].split(":")[1].replace("\"","");
            String zpdraw = zp[1].split(":")[1].replace("\"","");
            String zplose = zp[2].split(":")[1].replace("\"","");

            String []ids = ops[4].split(",");
            String id = ids[ids.length-1].split(":")[1].replace("\"","");//欧赔公司id
            CsOdds csOdds =new CsOdds();
            csOdds.setFjcid(fjcid);
            csOdds.setWin(Double.parseDouble("".equals(cpwin)?"0":cpwin));
            csOdds.setDraw(Double.parseDouble("".equals(cpdraw)?"0":cpdraw));
            csOdds.setLose(Double.parseDouble("".equals(cplose)?"0":cplose));
            csOdds.setFid(id);

            CsOdds zpodds = new CsOdds();
            zpodds.setFjcid(fjcid);
            zpodds.setWin(Double.parseDouble("".equals(zpwin)?"0":zpwin));
            zpodds.setDraw(Double.parseDouble("".equals(zpdraw)?"0":zpdraw));
            zpodds.setLose(Double.parseDouble("".equals(zplose)?"0":zplose));
            zpodds.setFid(id);

            try{
                if("10000".equals(id)||"30".equals(id)||"449".equals(id)||"451".equals(id)||"211".equals(id)){
                        oddsDao.deleteZp(zpodds);
                        oddsDao.saveZp(zpodds);
                }
            }catch (Exception e){

            }
        }

    }
}
