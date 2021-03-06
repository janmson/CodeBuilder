package com.CodeBuilder.core.web;

import com.CodeBuilder.core.pojo.Setting;
import com.CodeBuilder.core.pojo.Table;
import com.CodeBuilder.core.service.TableService;
import com.CodeBuilder.core.utils.SettingUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SettingController {


    /*页面显示部分*/
    @RequestMapping(value="/defaultSetting", method=RequestMethod.GET)
    public ModelAndView defaultSetting() {
        ModelAndView mv = new ModelAndView("defaultSetting");
        mv.addObject("url", "defaultSetting");
        return mv;
    }

    @RequestMapping(value="/tableSetting/{tableName}", method=RequestMethod.GET)
    public ModelAndView tableSetting(@PathVariable("tableName") String tableName) {
        ModelAndView mv = new ModelAndView("tableSetting");
        mv.addObject("tableName", tableName);
        return mv;
    }

    /*restful 部分*/
    @GetMapping("/setting/{tableName}")
    public Setting getSetting(@PathVariable("tableName") String tableName) {

        SettingUtils su = new SettingUtils();
        Setting setting = new Setting();
        if("hyDefault".equals(tableName)){
            setting = su.getDefaultSetting();
        }else{
            setting = su.getTableSetting(tableName);
        }
        return setting;
    }

    @PutMapping("/setting")
    public String update(@RequestBody Setting setting) throws Exception {
        SettingUtils su = new SettingUtils();

        if("1".equals(setting.getIsDefault())){
            su.updateDefaultSetting(setting);
        }else{
            if(setting.getId()!=0){
                su.updateTableSetting(setting);
            }else{

                su.insertTableSetting(setting);
            }
        }
        return "success";
    }

}
