package com.tang.admin.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.tang.admin.model.RespBean;
import com.tang.admin.pojo.CustomerReturnList;
import com.tang.admin.pojo.CustomerReturnListGoods;
import com.tang.admin.pojo.PurchaseList;
import com.tang.admin.pojo.PurchaseListGoods;
import com.tang.admin.query.CustomerReturnListQuery;
import com.tang.admin.service.ICustomerReturnListService;
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
 * 客户退货单表 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-08-10
 */
@Controller
@RequestMapping("/customerReturn")
public class CustomerReturnListController {

    @Resource
    private ICustomerReturnListService customerReturnListService;

    @Resource
    IUserService userService;

    /**
     * 客户退货主页
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        String nextCustomerReturnNumber = customerReturnListService.getNextCustomerReturnNumber();
        model.addAttribute("customerReturnNumber", nextCustomerReturnNumber);
        return "customerReturn/customer_return";
    }

    /**
     * 退货
     * @param customerReturnList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(CustomerReturnList customerReturnList, String goodsJson, Principal principal){
        String userName = principal.getName();
        customerReturnList.setUserId(userService.findUserByUserName(userName).getId());
        Gson gson = new Gson();
        List<CustomerReturnListGoods> slgList = gson.fromJson(goodsJson,new TypeToken<List<CustomerReturnListGoods>>(){}.getType());
        customerReturnListService.saveCustomerReturnList(customerReturnList,slgList);
        return RespBean.success("商品退货入库成功!");
    }

    /**
     * 退货单查询页
     * @return
     */
    @RequestMapping("searchPage")
    public String searchPage(){
        return "customerReturn/customer_return_search";
    }

    /**
     * 退货单列表
     * @param customerReturnListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery){
        return customerReturnListService.customerReturnList(customerReturnListQuery);
    }

    /**
     * 删除客户退货单记录
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        customerReturnListService.deleteReturnList(id);
        return RespBean.success("删除成功");
    }


}
