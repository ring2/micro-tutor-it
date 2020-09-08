package com.lz.read.pojo.vo;

import lombok.Data;

/**
 * @author :     ring2
 * @date :       2020/4/23 21:18
 * description:
 **/
@Data
public class AnswerSituationVo {
    private Integer id;
    private String question;
    private Integer peopleNum;
    private Float avg;
    private Integer max;
    private Integer min;
    private String result;
}
