package com.lz.read;

import com.lz.read.common.ReadBootApplication;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.Administrator;
import com.lz.read.pojo.vo.UserAnswerVo;
import com.lz.read.service.AdministratorService;
import com.lz.read.service.InvestigateService;
import com.lz.read.service.impl.InvestigateServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ReadApplication.class})
public class ReadApplicationTests {

    @Autowired
    AdministratorService administratorService;

    @Autowired
    InvestigateService investigateService;

    @Test
    public void contextLoads() {
        Administrator zs = administratorService.selectAdminByNameAndPwd("zs", "123456");
        System.out.println(zs);
    }

    @Test
    public void test() {
        RestResult<UserAnswerVo> situation = investigateService.getSituation();
        System.out.println(situation.getData().toString());

    }

}
