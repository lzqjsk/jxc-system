package com.tang.admin.controller;


import com.tang.admin.pojo.GoodsUnit;
import com.tang.admin.service.IGoodsUnitService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品单位表 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-08-06
 */
@Controller
@RequestMapping("/goodsUnit")
public class GoodsUnitController {
    @Resource
    private IGoodsUnitService goodsUnitService;

    @RequestMapping("allGoodsUnits")
    @ResponseBody
    public List<GoodsUnit> allGoodsUnits(){
        return goodsUnitService.list();
    }
}
