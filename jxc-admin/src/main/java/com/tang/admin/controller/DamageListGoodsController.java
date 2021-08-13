package com.tang.admin.controller;


import com.tang.admin.query.DamageListGoodsQuery;
import com.tang.admin.query.PurchaseListGoodsQuery;
import com.tang.admin.service.IDamageListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 报损单商品表 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-08-11
 */
@Controller
@RequestMapping("/damageListGoods")
public class DamageListGoodsController {

    @Resource
    private IDamageListGoodsService damageListGoodsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object > damageListGoodsList(DamageListGoodsQuery damageListGoodsQuery){
        return damageListGoodsService.damageListGoodsList(damageListGoodsQuery);
    }
}
