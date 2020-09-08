package com.lz.read.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.read.common.RestResult;
import com.lz.read.dao.ExpertMapper;
import com.lz.read.dao.ExperttypeMapper;
import com.lz.read.pojo.Administrator;
import com.lz.read.pojo.Expert;
import com.lz.read.pojo.Experttype;
import com.lz.read.pojo.vo.ExpertVo;
import com.lz.read.service.ExpertService;
import com.lz.read.service.ExperttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class ExpertServiceImpl implements ExpertService {

    @Resource
    private ExpertMapper expertMapper;

    @Resource
    private ExperttypeMapper experttypeMapper;

    //根据账号和密码查询专家
    @Override
    public Expert selectExpertByNameAndPwd(String username, String password) {
        Example example = new Example(Expert.class);
        example.createCriteria().andEqualTo("exUsername", username).
                andEqualTo("exPwd", password);
        List<Expert> experts = expertMapper.selectByExample(example);
        if (experts.size() > 0) {
            return experts.get(0);
        }
        return null;
    }

    //根据id获取专家列表
    @Override
    public RestResult getExpertById(Integer id) {
        return RestResult.success(expertMapper.selectByPrimaryKey(id));
    }

    @Override
    public RestResult delExpert(Integer id) {
        if (ObjectUtil.isNotEmpty(id)){
            int i = expertMapper.deleteByPrimaryKey(id);
            if (i > 0) {
                return RestResult.success();
            } else {
                return RestResult.failure("删除专家失败");
            }
        }
        return RestResult.failureOfParam();
    }

    //修改密码
    @Override
    public RestResult updateAdminPwd(String adminName, String originPwd, String newPwd) {
        if (StrUtil.isNotEmpty(adminName) && StrUtil.isNotEmpty(originPwd)) {
            Expert expert = selectExpertByNameAndPwd(adminName, originPwd);
            if (expert != null) {
                expert.setExPwd(newPwd);
                int i = expertMapper.updateByPrimaryKeySelective(expert);
                if (i > 0) {
                    return RestResult.success();
                } else {
                    return RestResult.failure("密码修改失败");
                }
            }
        }
        return RestResult.failureOfParam();
    }

    @Override
    public RestResult updateExpert(Expert expert) {
        if (ObjectUtil.isNotEmpty(expert)){
            int i = expertMapper.updateByPrimaryKeySelective(expert);
            if (i > 0) {
                return RestResult.success();
            } else {
                return RestResult.failure("专家信息更新失败");
            }
        }
        return RestResult.failureOfParam();
    }

    @Override
    public RestResult listExpert(Integer type,int pageNum, int pageSize) {
        Example example = new Example(Expert.class);
        if (type == 1) {
            example.createCriteria().andNotEqualTo("isReview",3);
        }else{
            example.createCriteria().andEqualTo("isReview",type);
        }

        List<Experttype> experttypes = experttypeMapper.selectAll();
        PageHelper.startPage(pageNum,pageSize);
        List<Expert> experts = expertMapper.selectByExample(example);
        List<ExpertVo> expertVos = new ArrayList<>(experts.size());
        // 我怀疑我在无中生有
        for (int i = 0; i < experts.size(); i++) {
            ExpertVo expertVo = new ExpertVo();
            BeanUtil.copyProperties(experts.get(i),expertVo);
            if (expertVo.getExTypeId() != null) {
                experttypes.forEach(experttype -> {
                    if (experttype.getId().equals(expertVo.getExTypeId())){
                        expertVo.setExpertType(experttype.getEtName());
                    }
                });
            }else {
                expertVo.setExpertType("暂未设置");
            }
            expertVos.add(expertVo);
        }
        PageInfo pageInfo = new PageInfo(expertVos);
        return RestResult.success(pageInfo);
    }

    //专家注册
    @Override
    public RestResult registerExpert(Expert expert) {
        Example example = new Example(Expert.class);
        example.createCriteria().andEqualTo("exUsername",expert.getExUsername());
        List<Expert> experts = expertMapper.selectByExample(example);
        if (experts.size() > 0){
            return RestResult.failureOfRepeatName();
        }
        int i = expertMapper.insertSelective(expert);
        if (i > 0){
            return RestResult.success();
        }
        return RestResult.failure("注册失败");
    }
}
