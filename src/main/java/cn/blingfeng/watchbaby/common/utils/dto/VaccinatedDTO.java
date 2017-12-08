package cn.blingfeng.watchbaby.common.utils.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/07
 * @description
 **/
@Data
public class VaccinatedDTO {

    private String vaccineName;

    private Date vaccinateTime;

    public VaccinatedDTO(String vaccineName, Date vaccinateTime) {
        this.vaccineName = vaccineName;
        this.vaccinateTime = vaccinateTime;
    }
}
