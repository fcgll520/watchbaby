package cn.blingfeng.watchbaby.common.enums;

import lombok.Getter;

/**
 * @author : blingfeng
 * @date : 2017/12/07
 * @description
 **/
@Getter
public enum ReserveStatusEnum {
    /** 预约中 */
    RESEERVING(0, "预约中"),
    /** 取消预约 */
    CANCLE(1, "取消预约"),;

    private Integer code;
    private String msg;

    ReserveStatusEnum(Integer code, String msg) {
        this.code = code;

        this.msg = msg;
    }
}
