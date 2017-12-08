package cn.blingfeng.watchbaby.user.event.controller;

import cn.blingfeng.watchbaby.common.converter.BabyEventForm2BabyEventConverter;
import cn.blingfeng.watchbaby.common.enums.ResultEnum;
import cn.blingfeng.watchbaby.common.exception.BabyException;
import cn.blingfeng.watchbaby.common.utils.dto.BabyEventDTO;
import cn.blingfeng.watchbaby.common.utils.form.BabyEventForm;
import cn.blingfeng.watchbaby.common.vo.ResultVo;
import cn.blingfeng.watchbaby.dataobject.BabyEvent;
import cn.blingfeng.watchbaby.dataobject.User;
import cn.blingfeng.watchbaby.user.event.service.BabyEventService;
import cn.blingfeng.watchbaby.user.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@RestController
@RequestMapping("/baby/event")
@Slf4j
public class BabyEventController {
    @Autowired
    private UserService userService;
    @Autowired
    private BabyEventService babyEventService;

    @GetMapping()
    public ResultVo<Page<BabyEventDTO>> babyEventList(@RequestParam("token") String token,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        Pageable pageable = new PageRequest(page, size);
        Page<BabyEventDTO> babyEventDTOPage = babyEventService.babyEventList(user.getUserId(), pageable);
        return ResultVo.ok(babyEventDTOPage);
    }

    @PostMapping()
    public ResultVo addBabyEvent(@RequestParam("token") String token,
                                 @RequestParam("title") String title,
                                 @RequestParam("content") String content) {
        if (StringUtils.isEmpty(title)) {
            log.error("【新增宝宝事件】 标题不能为空");
            throw new BabyException(ResultEnum.FORM_PARAM_ERROR);
        }
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        BabyEvent babyEvent = new BabyEvent();
        babyEvent.setEventTitle(title);
        babyEvent.setEventDescription(content);
        babyEvent.setUserId(user.getUserId());
        babyEventService.addBabyEvent(babyEvent);
        return ResultVo.ok();
    }

    @PutMapping()
    public ResultVo updateBabyEvent(@RequestParam("token") String token,
                                    @Valid BabyEventForm babyEventForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【更新宝宝事件】 表单参数错误 babyEventForm={}", babyEventForm);
            throw new BabyException(ResultEnum.FORM_PARAM_ERROR);
        }
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        BabyEvent babyEvent = BabyEventForm2BabyEventConverter.converter(babyEventForm);
        babyEvent.setUserId(user.getUserId());
        babyEventService.updateBabyEvent(babyEvent);
        return ResultVo.ok();
    }

    @DeleteMapping("/{id}")
    public ResultVo deleteBabyEvent(@PathVariable("id") String id,
                                    @RequestParam("token") String token) {
        if(StringUtils.isEmpty(id)){
            log.error("【删除宝宝事件】 表单参数错误 id");
            throw new BabyException(ResultEnum.FORM_PARAM_ERROR);
        }
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        babyEventService.deleteBabyEvent(user.getUserId(),id);
        return ResultVo.ok();
    }
}
