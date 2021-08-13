package com.tang.admin.controller;


import com.tang.admin.query.PurchaseListGoodsQuery;
import com.tang.admin.query.ReturnListGoodsQuery;
import com.tang.admin.query.ReturnListQuery;
import com.tang.admin.service.IReturnListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 退货单商品表 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-08-08
 */
@Controller
@RequestMapping("/returnListGoods")
public class ReturnListGoodsController {

    @Resource
    private IReturnListGoodsService returnListGoodsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object > returnListGoodsList(ReturnListGoodsQuery returnListGoodsQuery){
        return returnListGoodsService.returnListGoodsList(returnListGoodsQuery);
    }
}
