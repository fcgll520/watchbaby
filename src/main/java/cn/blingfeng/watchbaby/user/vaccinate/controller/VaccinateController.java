package cn.blingfeng.watchbaby.user.vaccinate.controller;

import cn.blingfeng.watchbaby.common.enums.ResultEnum;
import cn.blingfeng.watchbaby.common.exception.BabyException;
import cn.blingfeng.watchbaby.common.utils.dto.VaccinatedDTO;
import cn.blingfeng.watchbaby.common.vo.ResultVo;
import cn.blingfeng.watchbaby.dataobject.User;
import cn.blingfeng.watchbaby.user.login.service.UserService;
import cn.blingfeng.watchbaby.user.vaccinate.service.VaccinateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author : blingfeng
 * @date : 2017/12/07
 * @description
 **/
@RestController
@RequestMapping("/baby/vaccinate")
@Slf4j
public class VaccinateController {
    @Autowired
    private VaccinateService vaccinateService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResultVo createVaccinateReserve(@RequestParam String token,
                                           @RequestParam("vaccineId") Integer vaccineId) {
        if (vaccineId == null) {
            log.error("【疫苗预约】 表单参数错误 vaccineId={}", vaccineId);
            throw new BabyException(ResultEnum.FORM_PARAM_ERROR);
        }
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        vaccinateService.vaccineReserve(user.getUserId(), vaccineId);
        return ResultVo.ok();
    }

    @GetMapping()
    public ResultVo<Page<VaccinatedDTO>> vaccinateList(@RequestParam String token,
                                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        Pageable pageable = new PageRequest(page, size);
        Page<VaccinatedDTO> vaccinatedDTOPage = vaccinateService.userVaccinatedList(user.getUserId(), pageable);
        return ResultVo.ok(vaccinatedDTOPage);
    }

    @DeleteMapping()
    public ResultVo deleteVaccinateReserve(@RequestParam("token") String token,
                                           @RequestParam("vaccinateId") String vaccinateId) {
        if(StringUtils.isEmpty(vaccinateId)){
            log.error("【删除疫苗预约】 表单参数错误 vaccinateId={}",vaccinateId);
            throw new BabyException(ResultEnum.FORM_PARAM_ERROR);
        }
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        vaccinateService.deleteReserve(user.getUserId(), vaccinateId);
        return ResultVo.ok();
    }


}
