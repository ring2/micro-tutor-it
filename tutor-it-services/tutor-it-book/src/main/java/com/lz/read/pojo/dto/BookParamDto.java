package com.lz.read.pojo.dto;

import lombok.Data;

/**
 * @author :     lz
 * @date :       2020/4/14 10:47
 * description:
 **/
@Data
public class BookParamDto {
    String isCharge;
    Integer bookTypeId;
    Integer pageNum;
    Integer pageSize;
}
