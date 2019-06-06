package com.my.project.Controller;


import com.my.project.Common.CommonConst;
import com.my.project.Mapper.BsxxDao;
import com.my.project.Mapper.LsxxDao;
import com.my.project.Service.MyService;
import com.my.project.entity.LsxxBean;
import com.my.project.utill.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@CrossOrigin(origins = {"http://47.94.229.129:8080","http://localhost:8080"})
@RestController
@RequestMapping("my")
public class MyController {
    @Autowired
    private MyService myService;

    @RequestMapping("jsodds")
    public String method(String dataStr) {
        int res =myService.getJspOdds(dataStr);
        if(res==0){
            return  "";
        }
        return  "ok";
    }
    @RequestMapping("selectBsxx")
    public List<Map<String,Object>> selectBsxx(String dataStr){
    return myService.selectJcBs(dataStr);
    }
    @RequestMapping("fxOdds")
    public Map<String,Object> getFxResult(String fjcid){
        this.myService.getFxResult(fjcid);
        return null;
    }
    @RequestMapping("oddsCpFx")
    public List<Map<String,Object>> OddsCpFx(String fjcid,String checkedWin,String checkedDraw,String checkedLose,String checkedTime){
        if(null==fjcid){
           return null;
        }
        return myService.oddsCpFx(fjcid,checkedWin,checkedDraw,checkedLose,checkedTime);
    }
    @RequestMapping("oddsJpFx")
    public List<Map<String,Object>> OddsJpFx(String fjcid,String checkedWin,String checkedDraw,String checkedLose,String checkedTime){
        if(null==fjcid){
            return null;
        }
        return myService.oddsJpFx(fjcid,checkedWin,checkedDraw,checkedLose,checkedTime);
    }
    @RequestMapping("roddsJpFx")
    public List<Map<String,Object>> ROddsJpFx(String fjcid,String checkedWin,String checkedDraw,String checkedLose,String checkedTime){
        if(null==fjcid){
            return null;
        }
        return myService.roddsJpFx(fjcid,checkedWin,checkedDraw,checkedLose,checkedTime);
    }
}
