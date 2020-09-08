package tutor.it.generator.service.impl;

import tutor.it.generator.entity.ShopCar;
import tutor.it.generator.mapper.ShopCarMapper;
import tutor.it.generator.service.IShopCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.StrUtil;
import tutor.it.common.RestResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutor.it.common.QueryParam;
import cn.hutool.core.bean.BeanUtil;
import tutor.it.generator.entity.dto.ShopCarDTO;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ring2
 * @since 2020-05-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ShopCarServiceImpl extends ServiceImpl<ShopCarMapper, ShopCar> implements IShopCarService {


 @Override
 public RestResult create(ShopCarDTO object) {
    ShopCar target = new ShopCar();
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
 public RestResult update(ShopCarDTO object) {
    ShopCar target = new ShopCar();
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
    Page<ShopCar> page = new Page<>();
    page.setCurrent(queryParam.getPageNum());
    page.setSize(queryParam.getPageSize());
    QueryWrapper<ShopCar> queryWrapper = new QueryWrapper<>();
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
