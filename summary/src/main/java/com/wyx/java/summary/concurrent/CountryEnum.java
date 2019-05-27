package com.wyx.java.summary.concurrent;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: yuxiang
 * @date: Create in 2019/5/27
 */
public enum CountryEnum {
    ONE(1,"齐"),
    TWO(2,"燕"),
    THREE(3,"韩"),
    FOUR(4,"赵"),
    FIVE(5,"魏"),
    SIX(6,"楚");

    @Getter private Integer code;
    @Getter private String message;

    CountryEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public static CountryEnum foreach_countryEnum(Integer code){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if(code.equals(value.code)){
                return value;
            }
        }
        return null;
    }

}
