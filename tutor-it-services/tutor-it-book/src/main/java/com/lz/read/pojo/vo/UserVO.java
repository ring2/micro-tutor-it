package com.lz.read.pojo.vo;

import com.lz.read.pojo.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :     ring2
 * @date :       2020/5/21 16:53
 * description:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends User {
     private Integer credit;
}
