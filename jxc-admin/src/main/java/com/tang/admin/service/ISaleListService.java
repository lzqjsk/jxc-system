package com.tang.admin.service;

import com.tang.admin.pojo.SaleList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.pojo.SaleListGoods;
import com.tang.admin.query.SaleListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-09
 */
public interface ISaleListService extends IService<SaleList> {

    String getNextSaleNumber();

    void saveSaleList(SaleList saleList, List<SaleListGoods> slgList);

    Map<String, Object> saleList(SaleListQuery saleListQuery);

    void deleteSaleList(Integer id);

    void updateSaleListState(int id);

    Map<String, Object> countSale(SaleListQuery saleListQuery);

    List<Map<String, Object>> countDaySale(String begin, String end);

    List<Map<String, Object>> countMonthSale(String begin, String end);
}
