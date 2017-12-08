package cn.blingfeng.watchbaby.user.photo.service.impl;

import cn.blingfeng.watchbaby.common.utils.KeyUtil;
import cn.blingfeng.watchbaby.common.utils.dto.BabyAlbumDTO;
import cn.blingfeng.watchbaby.dataobject.BabyAlbum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BabyPhotoServiceImplTest {
    @Autowired
    private BabyPhotoServiceImpl babyPhotoService;

    private final String userId = "15124507855192172";
    @Test
    public void babyAlbumList() {

        Pageable pageable = new PageRequest(0,2);
        Page<BabyAlbumDTO> babyAlbumDTOS = babyPhotoService.babyAlbumList(userId, pageable);
        Assert.assertNotNull(babyAlbumDTOS.getContent());
    }

    @Test
    public void addBabyAlbum() {
        BabyAlbum babyAlbum = new BabyAlbum();
        babyAlbum.setAlbumId(KeyUtil.getKey());
        babyAlbum.setAlbumDescription("宝宝第100天");
        babyAlbum.setAlbumName("小宝宝");
        babyAlbum.setUserId(userId);
        BabyAlbum babyAlbum1 = babyPhotoService.addBabyAlbum(babyAlbum);
        Assert.assertNotNull(babyAlbum1);
    }

    @Test
    public void updateBabyAlbum() {
        BabyAlbum babyAlbum = new BabyAlbum();
        babyAlbum.setAlbumId("15127156390796782");
        babyAlbum.setAlbumDescription("宝宝第200天");
        babyAlbum.setAlbumName("大宝宝");
        babyAlbum.setUserId(userId);
        BabyAlbum babyAlbum1 = babyPhotoService.updateBabyAlbum(babyAlbum);
        Assert.assertNotNull(babyAlbum1);
    }

    @Test
    public void deleteBabyAlbum() {
        String albumId = "15127156390796782";
        babyPhotoService.deleteBabyAlbum(userId,albumId);
    }
}