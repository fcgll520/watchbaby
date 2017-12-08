package cn.blingfeng.watchbaby.user.vaccinate.service.impl;

import cn.blingfeng.watchbaby.common.utils.dto.VaccinatedDTO;
import cn.blingfeng.watchbaby.user.vaccinate.service.VaccinateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class VaccinateServiceImplTest {
    @Autowired
    private VaccinateServiceImpl vaccinateService;
    @Test
    public void userVaccinatedList() {
        String userId = "15124507855192172";
        Pageable pageable = new PageRequest(0,2);
        Page<VaccinatedDTO> vaccinatedDTOPage = vaccinateService.userVaccinatedList(userId, pageable);
        Assert.assertNotNull(vaccinatedDTOPage.getContent());
    }

    @Test
    public void vaccineReserve() {
    }

    @Test
    public void vaccineReserveCancle() {
    }

    @Test
    public void deleteReserve() {
    }
}