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
public class BabyPhoto {
    @Id
    private String photoId;
    /** 照片url */
    private String photoUrl;
    /** 相册id */
    private String albumId;
    /** 照片描述 */
    private String photoDescription;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
