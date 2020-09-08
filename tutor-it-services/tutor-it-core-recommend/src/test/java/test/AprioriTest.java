package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tutor.it.Application;
import tutor.it.common.RestResult;
import tutor.it.core.RecommendCore;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :     ring2
 * @date :       2020/5/26 07:09
 * description:
 **/
@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class AprioriTest {

    @Resource
    RecommendCore recommendCore;

    @Test
    public void testApriori() {
        RestResult<List<String>> listRestResult = recommendCore.aprioriAnalysis();
        System.out.println("hhh");

    }

}
