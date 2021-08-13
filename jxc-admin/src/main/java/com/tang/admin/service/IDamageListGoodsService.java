package com.tang.admin.service;

import com.tang.admin.pojo.DamageListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.query.DamageListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 报损单商品表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-11
 */
public interface IDamageListGoodsService extends IService<DamageListGoods> {

    Map<String, Object> damageListGoodsList(DamageListGoodsQuery damageListGoodsQuery);
}
