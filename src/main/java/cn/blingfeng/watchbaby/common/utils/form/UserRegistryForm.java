package cn.blingfeng.watchbaby.common.utils.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/04
 * @description
 **/
@Data
public class UserRegistryForm {

    /** 用户姓名 */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 1,max = 10,message = "用户名最大为10位")
    private String userName;
    /** 用户性别 */
    @NotNull(message = "用户性别不能为空")
    private Integer userSex;
    /** 用户手机号 */
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",message = "手机号格式不正确")
    private String userPhone;
    /** 用户密码 */
    @Size(min =6 ,max=12,message = "密码长度不小于6位不大于10位")
    private String userPassword;
    /** 用户出生日期 */
    private Date userBirth = new Date();
    /** 图片验证码 */
    @NotBlank(message = "图片验证码不能为空")
    private String picCheckCode;
    /** 手机验证码 */
    @NotBlank(message = "手机验证码不能为空")
    private String phoneCheckCode;

}
