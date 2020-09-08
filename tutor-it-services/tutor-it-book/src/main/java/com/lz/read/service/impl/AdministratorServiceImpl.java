package com.lz.read.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.Administrator;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.lz.read.dao.AdministratorMapper;
import com.lz.read.service.AdministratorService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class AdministratorServiceImpl implements AdministratorService {

    @Resource
    private AdministratorMapper administratorMapper;

    public Administrator selectAdminByNameAndPwd(String username, String password) {
        Example example = new Example(Administrator.class);
        example.createCriteria().andEqualTo("adminName", username).
                andEqualTo("adminPwd", password);
        List<Administrator> administrators = administratorMapper.selectByExample(example);
        if (administrators.size() > 0) {
            return administrators.get(0);
        }
        return null;
    }

    /**
     * 修改密码
     */
    @Override
    public RestResult updateAdminPwd(String adminName, String originPwd, String newPwd) {
        if (StrUtil.isNotEmpty(adminName) && StrUtil.isNotEmpty(originPwd)) {
            Administrator administrator = selectAdminByNameAndPwd(adminName, originPwd);
            if (administrator != null) {
                administrator.setAdminPwd(newPwd);
                int i = administratorMapper.updateByPrimaryKeySelective(administrator);
                if (i > 0) {
                    return RestResult.success();
                } else
                    return RestResult.failure("密码修改失败");
            }

        }

        return RestResult.failureOfParam();
    }

    // 更新管理员资料
    @Override
    public RestResult updateAdmin(Administrator administrator) {
        if (ObjectUtil.isNotEmpty(administrator)) {
            if (administratorMapper.updateByPrimaryKeySelective(administrator) > 0) {
                return RestResult.success();
            } else {
                return RestResult.failure("管理员信息修改失败");
            }
        }
        return RestResult.failureOfParam();
    }
}
