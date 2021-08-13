package com.tang.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author xiaokaixin
 * @Date 2021/8/12 07:50
 * @Version 1.0
 */

/**
 * 统计报表
 */
@Controller
@RequestMapping("report")
public class ReportController {

    @RequestMapping("countSupplier")
    public String countSupplier(){

        return "count/supplier";
    }

    @RequestMapping("countCustomer")
    public String countCustomerPage(){

        return "count/customer";
    }

    @RequestMapping("countPurchase")
    public String countPurchase(){
        return "count/purchase";
    }

    @RequestMapping("countSale")
    public String countSale(){
        return "count/sale";
    }

    @RequestMapping("countDaySale")
    public String countDaySal(){
        return "count/day_sale";
    }

    /**
     * 月销售统计
     * @return
     */
    @RequestMapping("countMonthSale")
    public String countMonthSale(){
        return "count/month_sale";
    }


}
