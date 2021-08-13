package com.tang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.admin.dto.TreeDto;
import com.tang.admin.model.CountResultModel;
import com.tang.admin.pojo.Goods;
import com.tang.admin.pojo.PurchaseList;
import com.tang.admin.mapper.PurchaseListMapper;
import com.tang.admin.pojo.PurchaseListGoods;
import com.tang.admin.query.PurchaseListQuery;
import com.tang.admin.service.IGoodsService;
import com.tang.admin.service.IGoodsTypeService;
import com.tang.admin.service.IPurchaseListGoodsService;
import com.tang.admin.service.IPurchaseListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.admin.utils.AssertUtil;
import com.tang.admin.utils.DateUtil;
import com.tang.admin.utils.PageResultUtil;
import com.tang.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 服务实现类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-07
 */
@Service
public class PurchaseListServiceImpl extends ServiceImpl<PurchaseListMapper, PurchaseList> implements IPurchaseListService {

    @Resource
    private IPurchaseListGoodsService purchaseListGoodsService;

    @Resource
    private IGoodsService goodsService;

    @Resource
    private IGoodsTypeService goodsTypeService;

    @Override
    public String getNextPurchaseNumber() {
        //JH20218070001X
        try {
            StringBuffer  stringBuffer = new StringBuffer();
            stringBuffer.append("JH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String purchaseNumber = this.baseMapper.getNextPurchaseNumber();
            if(null!=purchaseNumber){
                stringBuffer.append(StringUtil.formatCode(purchaseNumber));
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
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(purchaseList)),"记录添加失败!");
        PurchaseList temp = this.getOne(new QueryWrapper<PurchaseList>().eq("purchase_number",purchaseList.getPurchaseNumber()));
        plgList.forEach(plg->{
            plg.setPurchaseListId(temp.getId());
            //修改库存
            Goods goods = goodsService.getById(plg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+plg.getNum());
            goods.setLastPurchasingPrice(plg.getPrice());
            goods.setState(2);
            goodsService.updateById(goods);

        });
        AssertUtil.isTrue(!(purchaseListGoodsService.saveBatch(plgList)),"记录添加失败!");
    }

    @Override
    public Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery) {
        IPage<PurchaseList> page = new Page<PurchaseList>(purchaseListQuery.getPage(),purchaseListQuery.getLimit());

        page = this.baseMapper.purchaseList(page,purchaseListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deletePurchaseList(Integer id) {
        /**
         * 1.进货单商品记录删除
         * 2.进货单记录删除
         */

        AssertUtil.isTrue(!(purchaseListGoodsService.remove(new QueryWrapper<PurchaseListGoods>().eq("purchase_list_id",id))),"记录删除失败");

        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败");

    }

    @Override
    public void updatePurchaseList(Integer pid) {
        PurchaseList purchaseList = this.getById(pid);
        AssertUtil.isTrue(null == purchaseList,"待结算的记录不存在");
        AssertUtil.isTrue(purchaseList.getState() == 1,"记录已支付");
        purchaseList.setState(1);
        AssertUtil.isTrue(!(this.updateById(purchaseList)),"记录结算失败");
    }

    @Override
    public Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery) {
        /**
         * 分页查询
         *      查总数
         *      查当前页列表
         */
        if(null!=purchaseListQuery.getTypeId()){
            List<Integer> typeIds = goodsTypeService.queryAllSubTypeIdsByTypeId(purchaseListQuery.getTypeId());
            purchaseListQuery.setTypeIds(typeIds);
        }
        /**
         * page
         *      1-->0
         *      2-->10
         *      3-->20
         *
         */
        purchaseListQuery.setIndex((purchaseListQuery.getPage()-1)*purchaseListQuery.getLimit());
        Long count = this.baseMapper.countPurchaseTotal(purchaseListQuery);
        List<CountResultModel> list = this.baseMapper.countPurchaseList(purchaseListQuery);
        return PageResultUtil.getResult(count,list);
    }
}
