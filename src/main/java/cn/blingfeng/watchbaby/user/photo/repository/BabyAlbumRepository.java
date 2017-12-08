package cn.blingfeng.watchbaby.user.photo.repository;

import cn.blingfeng.watchbaby.dataobject.BabyAlbum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
public interface BabyAlbumRepository extends JpaRepository<BabyAlbum,String>{
    /**
     * 查找用户相册
     * @param userId
     * @param pageable
     * @return
     */
    Page<BabyAlbum> findByUserId(String userId, Pageable pageable);
}
