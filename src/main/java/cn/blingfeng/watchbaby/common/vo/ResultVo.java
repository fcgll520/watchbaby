package cn.blingfeng.watchbaby.common.vo;

import lombok.Data;

/**
 * @author : blingfeng
 * @date : 2017/12/03
 * @description
 **/
@Data
public class ResultVo<T> {
    /** 状态码 */
    private Integer code;

    private String msg;

    private T data;

    public ResultVo(Integer code,String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static  ResultVo ok(){
        return new ResultVo(0,"成功",null);
    }

    public static <T> ResultVo ok(T data){
        return new ResultVo(0,"成功",data);
    }

    public static ResultVo error(Integer errorCode,String msg){
        return new ResultVo(errorCode,msg,null);
    }

}
