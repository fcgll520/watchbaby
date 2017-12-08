package cn.blingfeng.watchbaby.user.photo.service;

import cn.blingfeng.watchbaby.common.utils.dto.BabyAlbumDTO;
import cn.blingfeng.watchbaby.dataobject.BabyAlbum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/

public interface BabyPhotoService {
    /**
     * 查找宝宝相册列表
     * @param userId
     * @param pageable
     * @return
     */
    Page<BabyAlbumDTO> babyAlbumList(String userId,Pageable pageable);

    /**
     * 新增宝宝相册
     * @param babyAlbum
     * @return
     */
    BabyAlbum addBabyAlbum(BabyAlbum babyAlbum);

    /**
     * 更新宝宝相册
     * @param babyAlbum
     * @return
     */
    BabyAlbum updateBabyAlbum(BabyAlbum babyAlbum);

    /**
     * 删除宝宝相册
     * @param userId
     * @param albumId
     */
    void deleteBabyAlbum(String userId,String albumId);

    /**
     * 查找相冊
     * @param userId
     * @param album
     * @return
     */
    BabyAlbumDTO findAlbum(String userId,String album);

}
