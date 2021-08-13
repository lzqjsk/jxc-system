package com.tang.admin.query;

import lombok.Data;

/**
 * @Author xiaokaixin
 * @Date 2021/8/8 15:13
 * @Version 1.0
 */
@Data
public class ReturnListQuery extends BaseQuery{

    private String returnNumber;
    private Integer supplierId;
    private Integer state;
    private String  startDate;
    private String endDate;
}
