package com.tang.admin.service;

import com.tang.admin.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.admin.query.CustomerQuery;

import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author 小开心
 * @since 2021-08-05
 */
public interface ICustomerService extends IService<Customer> {

    Map<String, Object> customerList(CustomerQuery customerQuery);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Integer[] ids);

    Customer findCustomerrByName(String name);
}
