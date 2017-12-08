package cn.blingfeng.watchbaby.common.enums;


import lombok.Getter;

/**
 * @author : blingfeng
 * @date : 2017/12/03
 * @description
 **/
@Getter
public enum ResultEnum {
    /** 手机号或者密码错误 */
    PHONE_OR_PASSWORD_ERROR(10, "手机号或者密码错误"),
    /** 用户注册表单参数错误 */
    USER_REGISTRY_FORM_PARAM_ERROR(11,"用户注册表单参数错误"),
    /** 用户登录参数错误 */
    USER_LOGIN_PARAM_ERROR(12,"用户登录参数错误"),
    /** 预约记录不存在 */
    RESERVE_NOT_EXIST(13,"预约记录不存在"),
    /** 预约记录不属于此用户 */
    RESERVE_NOT_OWNER(14,"预约记录不属于此用户"),
    /** 预约状态异常 */
    RESERVE_STATUS_ERROR(15,"预约状态异常"),
    /** 表单参数错误 */
    FORM_PARAM_ERROR(16,"表单参数错误"),
    /** 事件不属于此用户 */
    EVENT_NOT_OWNER(17,"事件不属于此用户"),
    /** 相册不存在 */
    ALBUM_NOT_EXIST(18,"相册不存在"),
    /** 相册不属于该用户 */
    ALBUM_NOT_OWNER(19,"相册不属于该用户"),
    ;
    private String msg;

    private Integer code;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
