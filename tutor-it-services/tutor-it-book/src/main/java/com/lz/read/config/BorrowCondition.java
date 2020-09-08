package com.lz.read.config;

import lombok.Getter;

/**
 * @author :     lz
 * @date :       2020/4/2 16:36
 * description:
 **/
@Getter
public enum BorrowCondition {

    BORROWING("借阅中"),
    RETURN_ON_TIME("按期归还"),
    OVERDUE_RETURN("超期归还"),
    NO_RETURN("未归还");
    private String condition;

    BorrowCondition(String condition){
        this.condition = condition;
    }
    public static BorrowCondition getBorrowStatus(String condition){
        for (BorrowCondition value : BorrowCondition.values()) {
            if (value.getCondition().equals(condition)){
                return value;
            }
        }
        return null;
    }
}
