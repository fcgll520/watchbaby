package cn.blingfeng.watchbaby.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/06
 * @description
 **/
@Data
@DynamicUpdate
@Entity
public class VaccineInfo {
    @Id
    @GeneratedValue

    private Integer vaccineId;
    /** 疫苗名称  */
    private String vaccineName;
    /** 疫苗价格  */
    private BigDecimal vaccinePrice;
    /** 注意事项 是否空腹等等  */
    private String vaccineAnnouncements;
    /** 疫苗最早注射时间  */
    private Date vaccinationStartTime;
    /** 疫苗最晚注射时间  */
    private Date vaccinationEndTime;
    /** 疫苗描述  */
    private String vaccineDescription;

    private Date createTime;

    private Date updateTime;
}
