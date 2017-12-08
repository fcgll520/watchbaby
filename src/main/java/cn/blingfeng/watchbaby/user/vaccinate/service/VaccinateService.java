package cn.blingfeng.watchbaby.user.vaccinate.service;

import cn.blingfeng.watchbaby.common.utils.dto.VaccinatedDTO;
import cn.blingfeng.watchbaby.dataobject.VaccinateUser;
import cn.blingfeng.watchbaby.dataobject.VaccineReserve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : blingfeng
 * @date : 2017/12/06
 * @description
 **/
public interface VaccinateService {
    /**
     * 查询用户已接种的疫苗
     * @param userId
     * @param pageable
     * @return
     */
    Page<VaccinatedDTO> userVaccinatedList(String userId, Pageable pageable);

    /**
     * 疫苗预约
     * @param userId
     * @param vaccineId
     * @return
     */
    VaccineReserve vaccineReserve(String userId,Integer vaccineId);

    /**
     * 疫苗预约取消
     * @param userId
     * @param vaccineReserveId
     * @return
     */
    VaccineReserve vaccineReserveCancle(String userId,String vaccineReserveId);

    /**
     * 删除预约记录
     * @param userId
     * @param vaccineReserveId
     */
    void deleteReserve(String userId,String vaccineReserveId);
//
//    /**
//     * 添加疫苗接种
//     * @param userId
//     * @param vaccineId
//     * @return
//     */
//    VaccinateUser addVaccinate(String userId,Integer vaccineId);
//
//    /**
//     * 完成预约(医生端)
//     * @param userId
//     * @param reserveId
//     */
//     void finishReserve(String userId,String reserveId);

}
