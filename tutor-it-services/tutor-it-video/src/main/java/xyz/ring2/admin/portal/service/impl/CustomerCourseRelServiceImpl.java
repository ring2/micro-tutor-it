package xyz.ring2.admin.portal.service.impl;

import xyz.ring2.admin.portal.entity.CustomerCourseRel;
import xyz.ring2.admin.portal.mapper.CustomerCourseRelMapper;
import xyz.ring2.admin.portal.service.ICustomerCourseRelService;
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
import xyz.ring2.admin.portal.entity.dto.CustomerCourseRelDTO;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ring2
 * @since 2020-05-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CustomerCourseRelServiceImpl extends ServiceImpl<CustomerCourseRelMapper, CustomerCourseRel> implements ICustomerCourseRelService {


 @Override
 public RestResult create(CustomerCourseRelDTO object) {
    CustomerCourseRel target = new CustomerCourseRel();
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
 public RestResult update(CustomerCourseRelDTO object) {
    CustomerCourseRel target = new CustomerCourseRel();
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
    Page<CustomerCourseRel> page = new Page<>();
    page.setCurrent(queryParam.getPageNum());
    page.setSize(queryParam.getPageSize());
    QueryWrapper<CustomerCourseRel> queryWrapper = new QueryWrapper<>();
    String key = queryParam.getKeyword();
    if (StrUtil.isNotEmpty(key)){
        queryWrapper.like("employee_name",key);
    }
    page = this.baseMapper.selectPage(page, queryWrapper);
    data.put("total", page.getTotal());
    data.put("list", page.getRecords());
    return RestResult.success(data);
 }
}
