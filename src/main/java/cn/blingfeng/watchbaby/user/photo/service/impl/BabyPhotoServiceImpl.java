package cn.blingfeng.watchbaby.user.photo.service.impl;

import cn.blingfeng.watchbaby.common.enums.ResultEnum;
import cn.blingfeng.watchbaby.common.exception.BabyException;
import cn.blingfeng.watchbaby.common.utils.KeyUtil;
import cn.blingfeng.watchbaby.common.utils.dto.BabyAlbumDTO;
import cn.blingfeng.watchbaby.common.utils.dto.BabyPhotoDTO;
import cn.blingfeng.watchbaby.dataobject.BabyAlbum;
import cn.blingfeng.watchbaby.dataobject.BabyPhoto;
import cn.blingfeng.watchbaby.user.photo.repository.BabyAlbumRepository;
import cn.blingfeng.watchbaby.user.photo.repository.BabyPhotoRepository;
import cn.blingfeng.watchbaby.user.photo.service.BabyPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@Service
@Slf4j
public class BabyPhotoServiceImpl implements BabyPhotoService {
    @Autowired
    private BabyAlbumRepository albumRepository;
    @Autowired
    private BabyPhotoRepository photoRepository;

    @Override
    public Page<BabyAlbumDTO> babyAlbumList(String userId, Pageable pageable) {
        Page<BabyAlbum> babyAlbumPage = albumRepository.findByUserId(userId, pageable);
        if (babyAlbumPage == null) {
            return null;
        }
        List<BabyAlbumDTO> babyAlbumDTOList = babyAlbumPage.getContent().stream().map(e -> new BabyAlbumDTO(e.getAlbumId(), e.getAlbumDescription())).collect(Collectors.toList());
        Page<BabyAlbumDTO> babyAlbumDTOPage = new PageImpl<>(babyAlbumDTOList, pageable, babyAlbumPage.getTotalElements());
        return babyAlbumDTOPage;
    }

    @Override
    public BabyAlbum addBabyAlbum(BabyAlbum babyAlbum) {
        String key = KeyUtil.getKey();
        babyAlbum.setAlbumId(key);
        BabyAlbum result = albumRepository.save(babyAlbum);
        return result;
    }

    @Override
    public BabyAlbum updateBabyAlbum(BabyAlbum babyAlbum) {
        BabyAlbum result = albumRepository.save(babyAlbum);
        return result;
    }

    @Override
    public void deleteBabyAlbum(String userId, String albumId) {
        BabyAlbum babyAlbum = albumRepository.findOne(albumId);
        if (babyAlbum == null) {
            log.error("【删除宝宝相册】不存在该相册 albumId={}", albumId);
            throw new BabyException(ResultEnum.ALBUM_NOT_EXIST);
        }
        if (!babyAlbum.getUserId().equalsIgnoreCase(userId)) {
            log.error("【删除宝宝相册】相册不属于此用户 userId={},albumId={}", userId, albumId);
            throw new BabyException(ResultEnum.ALBUM_NOT_OWNER);
        }
        albumRepository.delete(babyAlbum);
    }

    @Override
    public BabyAlbumDTO findAlbum(String userId, String albumId) {

        BabyAlbum babyAlbum = albumRepository.findOne(albumId);
        BabyAlbumDTO babyAlbumDTO = new BabyAlbumDTO();
        BeanUtils.copyProperties(babyAlbum,babyAlbumDTO);
        List<BabyPhoto> babyPhotoList = photoRepository.findByAlbumId(albumId);
        if(babyPhotoList==null){
            return null;
        }

        List<BabyPhotoDTO> babyPhotoDTOList = babyPhotoList.stream().map(e -> new BabyPhotoDTO(e.getPhotoId(), e.getPhotoUrl(), e.getPhotoDescription())).collect(Collectors.toList());
        babyAlbumDTO.setBabyPhotoList(babyPhotoDTOList);
        return babyAlbumDTO;
    }
}
