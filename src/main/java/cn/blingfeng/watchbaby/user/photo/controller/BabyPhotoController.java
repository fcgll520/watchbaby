package cn.blingfeng.watchbaby.user.photo.controller;

import cn.blingfeng.watchbaby.common.enums.ResultEnum;
import cn.blingfeng.watchbaby.common.exception.BabyException;
import cn.blingfeng.watchbaby.common.utils.dto.BabyAlbumDTO;
import cn.blingfeng.watchbaby.common.utils.form.BabyAlbumForm;
import cn.blingfeng.watchbaby.common.vo.ResultVo;
import cn.blingfeng.watchbaby.dataobject.BabyAlbum;
import cn.blingfeng.watchbaby.dataobject.User;
import cn.blingfeng.watchbaby.user.login.service.UserService;
import cn.blingfeng.watchbaby.user.photo.service.BabyPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/baby/album")
@Slf4j
public class BabyPhotoController {
    @Autowired
    private BabyPhotoService babyPhotoService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResultVo<Page<BabyAlbumDTO>> albumList(@RequestParam("token") String token,
                                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        Pageable pageable = new PageRequest(page, size);
        Page<BabyAlbumDTO> babyAlbumDTOPage = babyPhotoService.babyAlbumList(user.getUserId(), pageable);
        return ResultVo.ok(babyAlbumDTOPage);
    }

    @PostMapping()
    public ResultVo createAlbum(@Valid BabyAlbumForm babyAlbumForm,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【新建宝宝相册】表单参数错误 babyAlbumForm={}", babyAlbumForm);
            throw new BabyException(ResultEnum.FORM_PARAM_ERROR);
        }
        BabyAlbum babyAlbum = new BabyAlbum();
        BeanUtils.copyProperties(babyAlbumForm, babyAlbum);
        babyPhotoService.addBabyAlbum(babyAlbum);
        return ResultVo.ok();
    }

    @PutMapping()
    public ResultVo updateAlbum(@RequestParam("token") String token,
                                @RequestParam("id") String id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description) {
        if (StringUtils.isEmpty(name)) {
            log.error("【更新宝宝相册】表单参数错误 name");
            throw new BabyException(ResultEnum.FORM_PARAM_ERROR);
        }
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        BabyAlbum babyAlbum = new BabyAlbum();
        babyAlbum.setUserId(user.getUserId());
        babyAlbum.setAlbumName(name);
        babyAlbum.setAlbumId(id);
        babyAlbum.setAlbumDescription(description);
        babyPhotoService.updateBabyAlbum(babyAlbum);
        return ResultVo.ok();
    }

    @DeleteMapping()
    public ResultVo deleteAlbum(@RequestParam("token") String token,
                                @RequestParam("id") String id){
        ResultVo<User> userInfo = userService.getUserInfo(token);
        User user = userInfo.getData();
        babyPhotoService.deleteBabyAlbum(user.getUserId(),id);
        return ResultVo.ok();
    }
}
