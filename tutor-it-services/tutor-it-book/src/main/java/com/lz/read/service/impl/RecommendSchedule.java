package com.lz.read.service.impl;

import com.lz.read.dao.ExpertMapper;
import com.lz.read.dao.RecommendMapper;
import com.lz.read.pojo.Recommend;
import com.lz.read.pojo.po.RecommendBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :     lz
 * @date :       2020/5/3 13:41
 * description:
 **/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class RecommendSchedule {

    @Resource
    RecommendMapper recommendMapper;

    @Resource
    ExpertMapper expertMapper;

    /**
     *  1. 专家注册时挑选擅长的书籍分类
     *  因此专家表中有专家擅长的书籍分类ids 如 【1，2，4，6】
     *
     *  2. 从需要审核的书籍中可以获得书籍分类id
     *
     *  3. 从专家擅长的书籍分类id中判断是否有该推荐书籍的分类id，有则获取
     *  该专家的id 放入集合中最终获取到专家id集合，
     *
     *  4. 通过专家id集合在recommend 表中进行group by分类获得count数
     *  并order by count asc
     *
     *  5. 这样就获取到最‘得闲'专家，将推荐的书籍分配给他
     *
     * sql: 在获取到的专家集合中进行分组并获取到专家已审核书籍的数量并升序排列
     *
     *  select re_expertid,count(1) review_num from recommend GROUP BY re_expertid HAVING re_expertid in (1,2) ORDER BY review_num
     */
    public void executeTask(){
        List<RecommendBook> bookTypeFromRecommend = getBookTypeFromRecommend();

        if (bookTypeFromRecommend.size() > 0) {
            log.info("有任务正在执行。。任务个数【{}】个",bookTypeFromRecommend.size());
            bookTypeFromRecommend.forEach(recommendBook -> {
                List<Integer> expertIds = expertMapper.getExpertIds(recommendBook.getBookTypeId());
                Integer expertId = recommendMapper.getFinallyResult(expertIds);
                Recommend recommend = new Recommend();
                recommend.setId(recommendBook.getId());
                recommend.setReExpertId(expertId);
                recommendMapper.updateByPrimaryKeySelective(recommend);
            });
        }
    }

    /**
     *
     * @return 获取书籍
     */
    public List<RecommendBook> getBookTypeFromRecommend() {
        return recommendMapper.getBookTypeIds();
    }


}
