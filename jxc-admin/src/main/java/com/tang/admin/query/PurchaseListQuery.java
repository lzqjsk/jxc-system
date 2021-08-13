package com.tang.admin.query;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author xiaokaixin
 * @Date 2021/8/8 08:24
 * @Version 1.0
 */
@Data
public class PurchaseListQuery extends BaseQuery{

    private String purchaseNumber;
    private Integer supplierId;
    private Integer state;

    private String  startDate;
    private String endDate;
    private String goodsName;
    private Integer typeId;
    private List<Integer> typeIds;

    public Integer index;
}
