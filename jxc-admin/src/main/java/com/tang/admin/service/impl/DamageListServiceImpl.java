package com.tang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.admin.pojo.*;
import com.tang.admin.mapper.DamageListMapper;
import com.tang.admin.query.DamageListQuery;
import com.tang.admin.service.IDamageListGoodsService;
import com.tang.admin.service.IDamageListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.admin.service.IGoodsService;
import com.tang.admin.utils.AssertUtil;
import com.tang.admin.utils.DateUtil;
import com.tang.admin.utils.PageResultUtil;
import com.tang.admin.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报损单表 服务实现类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-11
 */
@Service
public class DamageListServiceImpl extends ServiceImpl<DamageListMapper, DamageList> implements IDamageListService {


    @Resource
    private IGoodsService goodsService;

    @Resource
    private IDamageListGoodsService damageListGoodsService;

    @Override
    public String getNextDamageNumber() {
        try {
            StringBuffer  stringBuffer = new StringBuffer();
            stringBuffer.append("BS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = this.baseMapper.getNextDamageNumber();
            if(null!=saleNumber){
                stringBuffer.append(StringUtil.formatCode(saleNumber));
            }else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void saveDamageList(DamageList damageList, List<DamageListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(damageList)),"记录添加失败!");
        DamageList temp = this.getOne(new QueryWrapper<DamageList>().eq("damage_number",damageList.getDamageNumber()));
        plgList.forEach(plg->{
            plg.setDamageListId(temp.getId());
            //修改库存
            Goods goods = goodsService.getById(plg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()-plg.getNum());
            goods.setLastPurchasingPrice(plg.getPrice());
            goods.setState(2);
            AssertUtil.isTrue(!goodsService.updateById(goods),"记录添加失败");
            AssertUtil.isTrue(!(damageListGoodsService.save(plg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> damageList(DamageListQuery damageListQuery) {
        IPage<DamageList> page = new Page<DamageList>(damageListQuery.getPage(),damageListQuery.getLimit());
        page =  this.baseMapper.damageList(page,damageListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    public void deleteDamageList(Integer id) {
        /**
         * 1.报损单商品记录删除
         * 2.报损单记录删除
         */

        AssertUtil.isTrue(!(damageListGoodsService.remove(new QueryWrapper<DamageListGoods>().eq("damage_list_id",id))),"记录删除失败");

        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败");
    }
}
