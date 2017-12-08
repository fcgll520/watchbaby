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
@Entity
@Data
@DynamicUpdate
public class BabyEvent {
    @Id
    private String eventId;
    /** 用户id */
    private String userId;
    /** 宝宝事件 */
    private String eventDescription;

    private String eventTitle;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
