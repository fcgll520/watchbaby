package cn.blingfeng.watchbaby.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@Entity
@Data
@DynamicUpdate
public class BabyAlbum {
    @Id
    private String albumId;
    /** userId */
    private String userId;
    /** 相册名字 */
    private String albumName;
    /** 相册描述 */
    private String albumDescription;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
