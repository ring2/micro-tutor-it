package tutor.it.generator.service.impl;

import tutor.it.core.RecommendCore;
import tutor.it.generator.entity.Orders;
import tutor.it.generator.mapper.OrderMapper;
import tutor.it.generator.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
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
import tutor.it.generator.entity.dto.OrderDTO;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ring2
 * @since 2020-05-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements IOrderService {

    @Resource
    RecommendCore recommendCore;

    @Override
    public RestResult create(OrderDTO object) {
        Orders target = new Orders();
        BeanUtil.copyProperties(object, target);
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
    public RestResult update(OrderDTO object) {
        Orders target = new Orders();
        BeanUtil.copyProperties(object, target);
        int i = this.baseMapper.updateById(target);
        if (i > 0) {
            return RestResult.success();
        }
        return RestResult.failure();
    }

    @Override
    public RestResult<Map<String, Object>> listByPage(QueryParam queryParam) {
        Map<String, Object> data = new HashMap<>(4);
        Page<Orders> page = new Page<>();
        page.setCurrent(queryParam.getPageNum());
        page.setSize(queryParam.getPageSize());
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        String key = queryParam.getKeyword();
        if (StrUtil.isNotEmpty(key)) {
            queryWrapper.like("employee_name", key);
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        data.put("total", page.getTotal());
        data.put("list", page.getRecords());
        return RestResult.success(data);
    }

    @Override
    public RestResult getApriori_analysis() {
        RestResult<List<String>> analysisResult = recommendCore.aprioriAnalysis();
        return null;
    }

}
