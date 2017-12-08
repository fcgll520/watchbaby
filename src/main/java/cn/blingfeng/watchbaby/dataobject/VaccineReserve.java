package cn.blingfeng.watchbaby.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/06
 * @description
 **/
@Data
@Entity
public class VaccineReserve {
    @Id
    private String reserveId;
    /** 用户id */
    private String userId;
    /** 疫苗id */
    private Integer vaccineId;
    /** 预约时间 */
    private Date reserveTime;
    /** 接种门诊 */
    private String vaccineOutpatientService;
    /** 预约状态 0为预约 1为取消预约 默认为0 */
    private Integer reserveStatus;

}
