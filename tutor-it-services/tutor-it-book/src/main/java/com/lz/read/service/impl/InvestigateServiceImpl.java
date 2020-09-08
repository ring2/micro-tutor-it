package com.lz.read.service.impl;

import com.lz.read.common.RestResult;
import com.lz.read.dao.AnswerMapper;
import com.lz.read.dao.PaperMapper;
import com.lz.read.dao.QuestionMapper;
import com.lz.read.dao.UserMapper;
import com.lz.read.pojo.Answer;
import com.lz.read.pojo.Paper;
import com.lz.read.pojo.Question;
import com.lz.read.pojo.dto.InvestigateDto;
import com.lz.read.pojo.vo.AnswerSituationVo;
import com.lz.read.pojo.vo.AnswerVo;
import com.lz.read.pojo.vo.ContentVo;
import com.lz.read.pojo.vo.UserAnswerVo;
import com.lz.read.service.InvestigateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/16 19:02
 * description:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestigateServiceImpl implements InvestigateService {

    @Resource
    PaperMapper paperMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    QuestionMapper questionMapper;

    @Resource
    AnswerMapper answerMapper;

    @Override
    public RestResult postInvestigate(InvestigateDto investigateDto) {
        Paper paper = new Paper();
        paper.setPaAdddate(new Date());
        paper.setPaTitle(investigateDto.getTitle());
        int i = paperMapper.insertSelective(paper);
        Example example = new Example(Paper.class);
        example.createCriteria().andEqualTo("paTitle",paper.getPaTitle());
        Paper paper1 = paperMapper.selectOneByExample(example);
        Question question = new Question();
        question.setQueTitle(investigateDto.getTitle());
        question.setQueDirection(investigateDto.getQuestion());
        question.setQueType(investigateDto.getQuestionType().substring(0,3));
        question.setQuePaperid(paper1.getId());
        questionMapper.insertSelective(question);
        return RestResult.success();
    }

    /**
     *
     * @return 纯编码，不使用sql（不建议。。）
     */
    @Override
    public RestResult<UserAnswerVo> getSituation() {
        UserAnswerVo userAnswerVo = new UserAnswerVo();
        List<AnswerVo> list = new ArrayList<>();
        paperMapper.selectAll().forEach(paper -> {
            AnswerVo answerVo = new AnswerVo();
            List<ContentVo> answers = new ArrayList<>();
            Example example = new Example(Question.class);
            example.createCriteria().andEqualTo("quePaperid", paper.getId());
            Question question = questionMapper.selectByExample(example).get(0);
            answerVo.setTitle(question.getQueDirection());
            Example example1 = new Example(Answer.class);
            example1.createCriteria().andEqualTo("ansQuestionId", question.getId());
            answerMapper.selectByExample(example1).forEach(answer -> {
                ContentVo contentVo = new ContentVo();
                contentVo.setUsername(userMapper.selectByPrimaryKey(answer.getAnsReaderid()).getUsername());
                contentVo.setContent(answer.getAnContent());
                answers.add(contentVo);
                answerVo.setAnswer(answers);
            });
            list.add(answerVo);
        });
        userAnswerVo.setData(list);
        return RestResult.success(userAnswerVo);
    }

    /**
     *
     * @return 采用sql
     */
    @Override
    public RestResult<List<AnswerSituationVo>> getSituation1() {
        List<AnswerSituationVo> answerSituationVos = answerMapper.selAnswerSituation();
        return RestResult.success(answerSituationVos);
    }

    @Override
    public RestResult delete(Integer id) {
         questionMapper.deleteByPrimaryKey(id);
        return RestResult.success();
    }
}
