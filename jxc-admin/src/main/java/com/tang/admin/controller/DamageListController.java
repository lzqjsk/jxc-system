package com.tang.admin.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.tang.admin.model.RespBean;
import com.tang.admin.pojo.DamageList;
import com.tang.admin.pojo.DamageListGoods;
import com.tang.admin.pojo.PurchaseList;
import com.tang.admin.pojo.PurchaseListGoods;
import com.tang.admin.query.DamageListQuery;
import com.tang.admin.service.IDamageListService;
import com.tang.admin.service.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报损单表 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-08-11
 */
@Controller
@RequestMapping("/damage")
public class DamageListController {

    @Resource
    private IDamageListService damageListService;


    @Resource
    IUserService userService;

    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("damageNumber", damageListService.getNextDamageNumber());
        return "damage/damage";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(DamageList damageList, String goodsJson, Principal principal){

        String userName = principal.getName();
        damageList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<DamageListGoods> plgList =gson.fromJson(goodsJson,new TypeToken<List<DamageListGoods>>(){}.getType());
        damageListService.saveDamageList(damageList,plgList);
        return RespBean.success("商品报损出库成功!");
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> damageList(DamageListQuery damageListQuery){
        return damageListService.damageList(damageListQuery);
    }

    /**
     * 删除报损单记录
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        damageListService.deleteDamageList(id);
        return RespBean.success("删除成功");
    }




}
