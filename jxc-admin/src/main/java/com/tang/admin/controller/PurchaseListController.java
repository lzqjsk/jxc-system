package com.tang.admin.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.tang.admin.model.RespBean;
import com.tang.admin.pojo.PurchaseList;
import com.tang.admin.pojo.PurchaseListGoods;
import com.tang.admin.query.PurchaseListQuery;
import com.tang.admin.service.IPurchaseListService;
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
 * 进货单 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-08-07
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseListController {

    @Resource
    private IPurchaseListService purchaseListService;

    @Resource
    private IUserService userService;

    @RequestMapping("index")
    public String index(Model model){
        //获取进货单号
        String purchaseNumber = purchaseListService.getNextPurchaseNumber();
        model.addAttribute("purchaseNumber",purchaseNumber);
        return "purchase/purchase";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(PurchaseList purchaseList, String goodsJson, Principal principal){

        String userName = principal.getName();
        purchaseList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<PurchaseListGoods> plgList =gson.fromJson(goodsJson,new TypeToken<List<PurchaseListGoods>>(){}.getType());
        purchaseListService.savePurchaseList(purchaseList,plgList);
        return RespBean.success("商品进货入库成功!");
    }

    /**
     * 进货单查旋页
     * @return
     */
    @RequestMapping("searchPage")
    public String searchPage(){
        return  "purchase/purchase_search";
    }

    /**
     *多条件查询
     * @param purchaseListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object > purchaseList(PurchaseListQuery purchaseListQuery){
        return purchaseListService.purchaseList(purchaseListQuery);
    }

    /**
     * 删除进货单记录
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        purchaseListService.deletePurchaseList(id);
        return RespBean.success("删除成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public RespBean update(Integer id){
        purchaseListService.updatePurchaseList(id);
        return RespBean.success("结算成功");
    }

    @RequestMapping("countPurchase")
    @ResponseBody
    public Map<String,Object > countPurchase(PurchaseListQuery purchaseListQuery){
        return purchaseListService.countPurchase(purchaseListQuery);
    }

}
