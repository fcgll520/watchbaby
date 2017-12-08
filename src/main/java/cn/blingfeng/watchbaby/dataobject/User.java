package cn.blingfeng.watchbaby.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/02
 * @description
 **/
@Entity
@Data
@DynamicUpdate
public class User {
    @Id
    private String userId;
    /** 用户姓名 */
    private String userName;
    /** 用户性别 */
    private Integer userSex;
    /** 用户手机号 */
    private String userPhone;
    /** 用户密码 */
    private String userPassword;
    /** 用户地址 */
    private String userHometown;
    /** 用户出生日期 */
    private Date userBirth;
    /** 用户头像 */
    private String userPhoto;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
