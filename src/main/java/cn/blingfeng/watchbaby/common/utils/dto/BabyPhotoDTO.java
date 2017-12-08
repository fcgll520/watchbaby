package cn.blingfeng.watchbaby.common.utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@Data
public class BabyPhotoDTO {
    @JsonProperty("id")
    private String photoId;
    /** 照片url */
    @JsonProperty("url")
    private String photoUrl;
    /** 照片描述 */
    @JsonProperty("description")
    private String photoDescription;

    public BabyPhotoDTO(String photoId, String photoUrl, String photoDescription) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.photoDescription = photoDescription;
    }
}
