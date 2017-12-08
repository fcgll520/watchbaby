package cn.blingfeng.watchbaby.common.utils.dto;

import cn.blingfeng.watchbaby.dataobject.BabyPhoto;
import lombok.Data;

import java.util.List;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@Data
public class BabyAlbumDTO {
    /** 相册id */
    private String albumId;
    /** 相册描述 */
    private String albumDescription;
    /** 照片list */
    List<BabyPhotoDTO> babyPhotoList;

    public BabyAlbumDTO(String albumId, String albumDescription, List<BabyPhotoDTO> babyPhotoList) {
        this.albumId = albumId;
        this.albumDescription = albumDescription;
        this.babyPhotoList = babyPhotoList;
    }
    public BabyAlbumDTO(String albumId, String albumDescription) {
        this.albumId = albumId;
        this.albumDescription = albumDescription;

    }
    public BabyAlbumDTO(){

    }
}
