package com.tang.admin.service;

import com.tang.admin.pojo.OverflowList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.pojo.OverflowListGoods;
import com.tang.admin.query.OverFlowListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报溢单表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-11
 */
public interface IOverflowListService extends IService<OverflowList> {

    String  getOverflowNumber();

    void saveOverflowList(OverflowList overflowList, List<OverflowListGoods> plgList);

    Map<String, Object> overFlowList(OverFlowListQuery overFlowListQuery);

    void deleteOverflowList(Integer id);
}
