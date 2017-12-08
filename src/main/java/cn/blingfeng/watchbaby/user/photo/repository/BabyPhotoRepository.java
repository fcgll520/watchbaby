package cn.blingfeng.watchbaby.user.photo.repository;

import cn.blingfeng.watchbaby.dataobject.BabyAlbum;
import cn.blingfeng.watchbaby.dataobject.BabyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
public interface BabyPhotoRepository extends JpaRepository<BabyPhoto,String>{
    /**
     * 查找相册下图片
     * @param albumId
     * @return
     */
    List<BabyPhoto> findByAlbumId(String albumId);
}
