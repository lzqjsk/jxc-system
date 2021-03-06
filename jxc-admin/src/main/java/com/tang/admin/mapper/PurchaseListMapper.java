package com.tang.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tang.admin.model.CountResultModel;
import com.tang.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.admin.query.PurchaseListQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 进货单 Mapper 接口
 * </p>
 *
 * @author 小开心
 * @since 2021-08-07
 */
public interface PurchaseListMapper extends BaseMapper<PurchaseList> {


    String getNextPurchaseNumber();

    IPage<PurchaseList> purchaseList(IPage<PurchaseList> page,@Param("purchaseListQuery") PurchaseListQuery purchaseListQuery);

    Long countPurchaseTotal(@Param("purchaseListQuery") PurchaseListQuery purchaseListQuery);

    List<CountResultModel> countPurchaseList(@Param("purchaseListQuery") PurchaseListQuery purchaseListQuery);
}
