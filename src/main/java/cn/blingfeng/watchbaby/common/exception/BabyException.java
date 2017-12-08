package cn.blingfeng.watchbaby.common.exception;

import cn.blingfeng.watchbaby.common.enums.ResultEnum;
import lombok.Data;
import lombok.Getter;

/**
 * @author : blingfeng
 * @date : 2017/12/03
 * @description
 **/
@Getter
public class BabyException extends RuntimeException{

    private Integer code;

    public BabyException(ResultEnum resultEnum){
        super();
        this.code = resultEnum.getCode();
    }
}
