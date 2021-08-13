package com.tang.admin.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.tang.admin.model.RespBean;
import com.tang.admin.model.SaleCount;
import com.tang.admin.pojo.PurchaseListGoods;
import com.tang.admin.pojo.SaleList;
import com.tang.admin.pojo.SaleListGoods;
import com.tang.admin.query.SaleListQuery;
import com.tang.admin.service.ISaleListService;
import com.tang.admin.service.IUserService;
import com.tang.admin.utils.DateUtil;
import com.tang.admin.utils.MathUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-08-09
 */
@Controller
@RequestMapping("/sale")
public class SaleListController {

    @Resource
    private ISaleListService saleListService;

    @Resource
    private IUserService userService;

    /**
     * 销售库主页
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){

        String saleNumber = saleListService.getNextSaleNumber();
        model.addAttribute("saleNumber",saleNumber);
        return "sale/sale";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(SaleList saleList, String goodsJson, Principal principal){

        String userName = principal.getName();
        saleList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<SaleListGoods> slgList =gson.fromJson(goodsJson,new TypeToken<List<SaleListGoods>>(){}.getType());
        saleListService.saveSaleList(saleList,slgList);
        return RespBean.success("商品销售出库成功!");
    }

    /**
     * 销售单查询页
     */
    @RequestMapping("searchPage")
    public String searchPage(){
        return "sale/sale_search";
    }

    /**
     * 多条件查询
     * @param saleListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> saleList(SaleListQuery saleListQuery){
        return saleListService.saleList(saleListQuery);
    }

    /**
     * 删除销售单记录
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        saleListService.deleteSaleList(id);
        return RespBean.success("删除成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public RespBean save(int id){
        saleListService.updateSaleListState(id);
        return RespBean.success("销售单结算成功");
    }

    @RequestMapping("countSale")
    @ResponseBody
    public Map<String,Object> countSale(SaleListQuery saleListQuery){
        return saleListService.countSale(saleListQuery);
    }

    @RequestMapping("countSaleByDay")
    @ResponseBody
    public  Map<String,Object> countDaySale(String begin,String end){
        Map<String,Object> result =new HashMap<String,Object>();
        List<SaleCount> saleCounts =new ArrayList<SaleCount>();
        /**
         * 2021-03-15  -  2021-03-30
         */
        List<Map<String,Object>> list = saleListService.countDaySale(begin,end);
        /**
         * 根据传入的时间段 生成日期列表
         */
        List<String> datas = DateUtil.getRangeDates(begin,end);
        for (String data : datas) {
            SaleCount saleCount =new SaleCount();
            saleCount.setDate(data);
            boolean flag =true;
            for(Map<String,Object> map:list){
                String dd = map.get("saleDate").toString().substring(0,10);
                if(data.equals(dd)){
                    saleCount.setAmountCost(MathUtil.format2Bit(Float.parseFloat(map.get("amountCost").toString())));
                    saleCount.setAmountSale(MathUtil.format2Bit(Float.parseFloat(map.get("amountSale").toString())));
                    saleCount.setAmountProfit(MathUtil.format2Bit(saleCount.getAmountSale()-saleCount.getAmountCost()));
                    flag =false;
                }
            }
            if(flag){
                saleCount.setAmountProfit(0F);
                saleCount.setAmountSale(0F);
                saleCount.setAmountCost(0F);
            }
            saleCounts.add(saleCount);
        }

        result.put("count",saleCounts.size());
        result.put("data",saleCounts);
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    @RequestMapping("countSaleByMonth")
    @ResponseBody
    public Map<String,Object> countSaleByMonth(String begin,String end){
        Map<String,Object> result =new HashMap<String,Object>();
        List<SaleCount> saleCounts =new ArrayList<SaleCount>();
        /**
         * 2021-03  -  2021-03
         */
        List<Map<String,Object>> list = saleListService.countMonthSale(begin,end);
        /**
         * 根据传入的时间段 生成日期列表
         */
        List<String> datas = DateUtil.getRangeMonth(begin,end);
        for (String data : datas) {
            SaleCount saleCount =new SaleCount();
            saleCount.setDate(data);
            boolean flag =true;
            for(Map<String,Object> map:list){
                String dd = map.get("saleDate").toString().substring(0,7);
                if(data.equals(dd)){
                    saleCount.setAmountCost(MathUtil.format2Bit(Float.parseFloat(map.get("amountCost").toString())));
                    saleCount.setAmountSale(MathUtil.format2Bit(Float.parseFloat(map.get("amountSale").toString())));
                    saleCount.setAmountProfit(MathUtil.format2Bit(saleCount.getAmountSale()-saleCount.getAmountCost()));
                    flag =false;
                }
            }
            if(flag){
                saleCount.setAmountProfit(0F);
                saleCount.setAmountSale(0F);
                saleCount.setAmountCost(0F);
            }
            saleCounts.add(saleCount);
        }

        result.put("count",saleCounts.size());
        result.put("data",saleCounts);
        result.put("code",0);
        result.put("msg","");
        return result;
    }

}
