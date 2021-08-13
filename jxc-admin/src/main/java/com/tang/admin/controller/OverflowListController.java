package com.tang.admin.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.tang.admin.model.RespBean;
import com.tang.admin.pojo.DamageList;
import com.tang.admin.pojo.DamageListGoods;
import com.tang.admin.pojo.OverflowList;
import com.tang.admin.pojo.OverflowListGoods;
import com.tang.admin.query.OverFlowListQuery;
import com.tang.admin.service.IOverflowListService;
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
 * 报溢单表 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-08-11
 */
@Controller
@RequestMapping("/overflow")
public class OverflowListController {

    @Resource
    private IOverflowListService overflowListService;

    @Resource
    private IUserService userService;

    /**
     * 商品报一主页
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("overflowNumber",overflowListService.getOverflowNumber());
        return "overflow/overflow";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(OverflowList overflowList, String goodsJson, Principal principal){

        String userName = principal.getName();
        overflowList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<OverflowListGoods> plgList =gson.fromJson(goodsJson,new TypeToken<List<OverflowListGoods>>(){}.getType());
        overflowListService.saveOverflowList(overflowList,plgList);
        return RespBean.success("商品报溢入库成功!");
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> overFlowList(OverFlowListQuery overFlowListQuery){
        return overflowListService.overFlowList(overFlowListQuery);
    }


    /**
     * 删除报溢单记录
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        overflowListService.deleteOverflowList(id);
        return RespBean.success("删除成功");
    }

}
