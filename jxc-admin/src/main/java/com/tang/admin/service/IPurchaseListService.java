package com.tang.admin.service;

import com.tang.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.pojo.PurchaseListGoods;
import com.tang.admin.query.PurchaseListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-07
 */
public interface IPurchaseListService extends IService<PurchaseList> {

    String getNextPurchaseNumber();

    void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList);

    Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery);

    void deletePurchaseList(Integer id);

    void updatePurchaseList(Integer pid);

    Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery);
}
