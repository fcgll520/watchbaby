package cn.blingfeng.watchbaby.user.vaccinate.service.impl;

import cn.blingfeng.watchbaby.common.enums.ReserveStatusEnum;
import cn.blingfeng.watchbaby.common.enums.ResultEnum;
import cn.blingfeng.watchbaby.common.exception.BabyException;
import cn.blingfeng.watchbaby.common.utils.KeyUtil;
import cn.blingfeng.watchbaby.common.utils.dto.VaccinatedDTO;
import cn.blingfeng.watchbaby.dataobject.VaccinateUser;
import cn.blingfeng.watchbaby.dataobject.VaccineReserve;
import cn.blingfeng.watchbaby.user.vaccinate.repository.VaccinateUserRepository;
import cn.blingfeng.watchbaby.user.vaccinate.repository.VaccineReserveRepository;
import cn.blingfeng.watchbaby.user.vaccinate.service.VaccinateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : blingfeng
 * @date : 2017/12/06
 * @description
 **/
@Service
@Slf4j
public class VaccinateServiceImpl implements VaccinateService {
    @Autowired
    private VaccinateUserRepository userRepository;

    @Autowired
    private VaccineReserveRepository reserveRepository;

    @Override
    public Page<VaccinatedDTO> userVaccinatedList(String userId, Pageable pageable) {
        Page<VaccinateUser> vaccinateUserPage = userRepository.findByUserId(userId, pageable);
        if(vaccinateUserPage==null){
            return null;
        }
        List<VaccinatedDTO> vaccinatedDTOList = vaccinateUserPage.getContent().stream().map(e -> new VaccinatedDTO(e.getVaccineInfo().getVaccineName(), e.getVaccinateTime())).collect(Collectors.toList());
        Page<VaccinatedDTO> vaccinatedDTOPage = new PageImpl<VaccinatedDTO>(vaccinatedDTOList, pageable, vaccinateUserPage.getTotalElements());
        return vaccinatedDTOPage;
    }

    @Override
    public VaccineReserve vaccineReserve(String userId, Integer vaccineId) {
        VaccineReserve vaccineReserve = new VaccineReserve();
        String key = KeyUtil.getKey();
        vaccineReserve.setReserveId(key);
        vaccineReserve.setVaccineId(vaccineId);
        vaccineReserve.setReserveTime(new Date());
        vaccineReserve.setReserveStatus(ReserveStatusEnum.RESEERVING.getCode());

        //TODO 此处待修改逻辑
        vaccineReserve.setVaccineOutpatientService("xxxx");

        vaccineReserve.setUserId(userId);
        VaccineReserve result = reserveRepository.save(vaccineReserve);
        return result;
    }

    @Override
    public VaccineReserve vaccineReserveCancle(String userId, String vaccineReserveId) {
        VaccineReserve vaccineReserve = reserveRepository.findOne(vaccineReserveId);
        if (vaccineReserve.getReserveStatus().equals(ReserveStatusEnum.CANCLE.getCode())) {
            log.error("【取消疫苗预约】 疫苗预约状态异常 reserveStatus={}", vaccineReserve.getReserveStatus());
            throw new BabyException(ResultEnum.RESERVE_STATUS_ERROR);
        }
        if (vaccineReserve == null) {
            log.error("【取消疫苗预约】 此预约记录不存在 vaccineReserveId={}", vaccineReserveId);
            throw new BabyException(ResultEnum.RESERVE_NOT_EXIST);
        }
        if (!vaccineReserve.getUserId().equalsIgnoreCase(userId)) {
            log.error("【取消疫苗预约】 此预约记录不属于该用户 vaccineReserveId={}，userId={}", vaccineReserveId, userId);
            throw new BabyException(ResultEnum.RESERVE_NOT_OWNER);
        }
        vaccineReserve.setReserveStatus(ReserveStatusEnum.CANCLE.getCode());
        VaccineReserve result = reserveRepository.save(vaccineReserve);
        return result;
    }

    @Override
    @Transactional(rollbackFor = BabyException.class)
    public void deleteReserve(String userId, String vaccineReserveId) {
        VaccineReserve vaccineReserve = reserveRepository.findOne(vaccineReserveId);
        if (!vaccineReserve.getReserveStatus().equals(ReserveStatusEnum.CANCLE.getCode())) {
            log.error("【删除预约】 预约状态异常 reserveStatus = {}", vaccineReserve.getReserveStatus());
            throw new BabyException(ResultEnum.RESERVE_STATUS_ERROR);
        }
        if (!vaccineReserve.getUserId().equalsIgnoreCase(userId)) {
            log.error("【删除预约】 此预约记录不属于该用户 vaccineReserveId={}，userId={}", vaccineReserveId, userId);
            throw new BabyException(ResultEnum.RESERVE_NOT_OWNER);
        }
        reserveRepository.delete(vaccineReserveId);
    }

//    @Override
//    public VaccinateUser addVaccinate(String userId,Integer vaccinateId) {
//        String key = KeyUtil.getUserKey();
//        VaccinateUser vaccinateUser = new VaccinateUser();
//        vaccinateUser.setUserId(userId);
//        vaccinateUser.setVaccineId(vaccinateId);
//        vaccinateUser.setVaccinateId(key);
//        vaccinateUser.setVaccinateTime(new Date());
//        VaccinateUser result = userRepository.save(vaccinateUser);
//        return result;
//    }
}
