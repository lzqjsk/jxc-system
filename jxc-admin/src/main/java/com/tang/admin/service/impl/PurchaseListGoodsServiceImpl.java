package com.tang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.admin.pojo.PurchaseListGoods;
import com.tang.admin.mapper.PurchaseListGoodsMapper;
import com.tang.admin.pojo.User;
import com.tang.admin.query.PurchaseListGoodsQuery;
import com.tang.admin.service.IPurchaseListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 进货单商品表 服务实现类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-07
 */
@Service
public class PurchaseListGoodsServiceImpl extends ServiceImpl<PurchaseListGoodsMapper, PurchaseListGoods> implements IPurchaseListGoodsService {

    @Override
    public Map<String, Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery) {
        IPage<PurchaseListGoods> page = new Page<PurchaseListGoods>(purchaseListGoodsQuery.getPage(),purchaseListGoodsQuery.getLimit());
        QueryWrapper<PurchaseListGoods> queryWrapper = new QueryWrapper<PurchaseListGoods>();
        if(null!=purchaseListGoodsQuery.getPurchaseListId()){
            queryWrapper.eq("purchase_list_id",purchaseListGoodsQuery.getPurchaseListId());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
