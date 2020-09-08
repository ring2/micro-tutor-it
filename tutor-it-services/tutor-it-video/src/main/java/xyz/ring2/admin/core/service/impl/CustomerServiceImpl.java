package xyz.ring2.admin.core.service.impl;

import cn.hutool.core.util.ObjectUtil;
import xyz.ring2.admin.core.entity.Customer;
import xyz.ring2.admin.core.mapper.CustomerMapper;
import xyz.ring2.admin.core.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.StrUtil;
import xyz.ring2.admin.common.RestResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ring2.admin.common.QueryParam;
import cn.hutool.core.bean.BeanUtil;
import xyz.ring2.admin.core.entity.dto.CustomerDTO;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ring2
 * @since 2020-05-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {


 @Override
 public RestResult create(CustomerDTO object) {
    Customer target = new Customer();
    BeanUtil.copyProperties(object,target);
    int i = this.baseMapper.insert(target);
    if (i > 0) {
        return RestResult.success();
    }
    return RestResult.failure();
 }

 @Override
 public RestResult delete(Integer id) {
    int i = 0;
    i = this.baseMapper.deleteById(id);
    if (i > 0) {
        return RestResult.success();
    }
    return RestResult.failure();
 }

 @Override
 public RestResult update(CustomerDTO object) {
    Customer target = new Customer();
    BeanUtil.copyProperties(object,target);
    int i = this.baseMapper.updateById(target);
    if (i > 0) {
    return RestResult.success();
    }
    return RestResult.failure();
 }

 @Override
 public RestResult<Map<String, Object>> listByPage(QueryParam queryParam) {
    Map<String, Object> data = new HashMap<>(4);
    Page<Customer> page = new Page<>();
    page.setCurrent(queryParam.getPageNum());
    page.setSize(queryParam.getPageSize());
    QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
    String key = queryParam.getKeyword();
    if (StrUtil.isNotEmpty(key)){
        queryWrapper.like("username",key);
    }
    page = this.baseMapper.selectPage(page, queryWrapper);
    data.put("total", page.getTotal());
    data.put("list", page.getRecords());
    return RestResult.success(data);
 }

    @Override
    public RestResult register(String username, String password) {
     QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
     queryWrapper.lambda().eq(Customer::getUsername,username);
        Customer customer = this.baseMapper.selectOne(queryWrapper);
         if (ObjectUtil.isNotEmpty(customer)){
             return RestResult.failureOfRepeatName();
         }
         customer.setPassword(password).setUsername(username);
        int insert = this.baseMapper.insert(customer);
        if (insert > 0) {
            return RestResult.success();
        }
        return RestResult.failure();
    }

    @Override
    public RestResult validUser(String username, String password) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Customer::getUsername,username)
                .eq(Customer::getPassword,password);
        Customer customer = this.baseMapper.selectOne(queryWrapper);
        return ObjectUtil.isNotEmpty(customer)?RestResult.success(customer)
                :RestResult.failureOfUsername();
    }
}
