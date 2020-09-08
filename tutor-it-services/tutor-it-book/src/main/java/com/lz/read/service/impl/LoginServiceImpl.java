package com.lz.read.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lz.read.common.RestResult;
import com.lz.read.config.UserType;
import com.lz.read.dao.AdministratorMapper;
import com.lz.read.pojo.Administrator;
import com.lz.read.pojo.Expert;
import com.lz.read.service.AdministratorService;
import com.lz.read.service.ExpertService;
import com.lz.read.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :     lz
 * @date :       2020/4/2 10:16
 * description:
 **/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {


    @Autowired
    ExpertService expertService;

    @Resource
    AdministratorService administratorService;

    @Override
    public RestResult<Map> validateUser(String username, String password, String userType) {
        Map<String,Object> result = new HashMap<>();
        if (StrUtil.isNotEmpty(userType) && userType.equals(UserType.ADMIN.getUserType())){
            Administrator administrator = administratorService.selectAdminByNameAndPwd(username, password);
            if (administrator != null) {
                result.put("user",administrator);
                result.put("userType",UserType.ADMIN.getUserType());
                return RestResult.success(result);
            }
        }else if (StrUtil.isNotEmpty(userType) && userType.equals(UserType.EXPERT.getUserType())){
            Expert expert = expertService.selectExpertByNameAndPwd(username, password);
            if (expert!=null) {
                Byte isReview = expert.getIsReview();
                if (isReview==0){
                    return RestResult.failureExpert();
                }
                if (isReview==3){
                    return RestResult.failureExpert1();
                }
            }
            if (expert != null) {
                result.put("user",expert);
                result.put("userType",UserType.EXPERT.getUserType());
                return RestResult.success(result);
            }
        }
        return RestResult.failure("用户名或密码错误");
    }
}
