package com.tang.admin.service;

import com.tang.admin.pojo.ReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.pojo.ReturnListGoods;
import com.tang.admin.query.ReturnListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退货单表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-08
 */
public interface IReturnListService extends IService<ReturnList> {


    String  getNextReturnNumber();

    void saveReturnList(ReturnList returnList, List<ReturnListGoods> rlgList);

    Map<String, Object> returnList(ReturnListQuery returnListQuery);

    void deleteReturnList(Integer id);
}
