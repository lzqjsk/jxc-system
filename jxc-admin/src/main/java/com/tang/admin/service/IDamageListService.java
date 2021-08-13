package com.tang.admin.service;

import com.tang.admin.pojo.DamageList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.pojo.DamageListGoods;
import com.tang.admin.query.DamageListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报损单表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-11
 */
public interface IDamageListService extends IService<DamageList> {

    String  getNextDamageNumber();

    void saveDamageList(DamageList damageList, List<DamageListGoods> plgList);

    Map<String, Object> damageList(DamageListQuery damageListQuery);

    void deleteDamageList(Integer id);
}
