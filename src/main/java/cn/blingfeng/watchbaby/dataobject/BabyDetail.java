package cn.blingfeng.watchbaby.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@Data
@Entity
@DynamicUpdate
public class BabyDetail {
    @Id
    private String detailId;
    /** userId */
    private String userId;
    /** 宝宝身高 */
    private Integer babyHeight;
    /** 宝宝体重 */
    private Integer babyWeight;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
