package com.tang.admin.service;

import com.tang.admin.pojo.CustomerReturnListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.query.CustomerReturnListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-09
 */
public interface ICustomerReturnListGoodsService extends IService<CustomerReturnListGoods> {


    Integer getReturnTotalByGoodId(Integer id);


    Map<String, Object> customerReturnListGoodsList(CustomerReturnListGoodsQuery customerReturnListGoodsQuery);
}
