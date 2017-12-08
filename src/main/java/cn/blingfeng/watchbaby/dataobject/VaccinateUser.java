package cn.blingfeng.watchbaby.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/06
 * @description
 **/
@Data
@Entity
public class VaccinateUser {
    @Id
    private String vaccinateId;

    private String userId;

    private Date vaccinateTime;

    private Integer vaccineId;
    @OneToOne
    @JoinColumn(name = "vaccineId",insertable = false,updatable=false)
    private VaccineInfo vaccineInfo;

}
