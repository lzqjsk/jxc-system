package com.tang.admin.service;

import com.tang.admin.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.query.GoodsQuery;

import java.util.Map;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-06
 */
public interface IGoodsService extends IService<Goods> {

    String genGoodsCode();

    Map<String, Object> goodsList(GoodsQuery goodsQuery);

    void saveGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoods(Integer id);

    void updateStock(Goods goods);

    void deleteStock(Integer id);

    Goods getGoodsInfoById(Integer gid);

    Map<String, Object> stockList(GoodsQuery goodsQuery);
}
