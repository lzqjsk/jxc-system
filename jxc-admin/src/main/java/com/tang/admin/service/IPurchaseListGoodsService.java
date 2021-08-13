package com.tang.admin.service;

import com.tang.admin.pojo.PurchaseListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.query.PurchaseListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 进货单商品表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-07
 */
public interface IPurchaseListGoodsService extends IService<PurchaseListGoods> {

    Map<String, Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery);
}
