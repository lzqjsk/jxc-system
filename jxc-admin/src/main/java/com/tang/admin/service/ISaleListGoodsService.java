package com.tang.admin.service;

import com.tang.admin.pojo.SaleListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.query.saleListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 销售单商品表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-09
 */
public interface ISaleListGoodsService extends IService<SaleListGoods> {

    Integer getSaleTotalByGoodIs(Integer id);

    Map<String, Object> saleListGoodsList(saleListGoodsQuery saleListGoodsQuery);
}
