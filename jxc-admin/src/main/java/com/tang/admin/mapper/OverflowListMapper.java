package com.tang.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tang.admin.pojo.OverflowList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.admin.query.OverFlowListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报溢单表 Mapper 接口
 * </p>
 *
 * @author 小开心
 * @since 2021-08-11
 */
public interface OverflowListMapper extends BaseMapper<OverflowList> {

    String getOverflowNumber();

    IPage<OverflowList> overFlowList(IPage<OverflowList> page,@Param("overFlowListQuery") OverFlowListQuery overFlowListQuery);
}
