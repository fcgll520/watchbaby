package cn.blingfeng.watchbaby.common.utils.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@Data
public class BabyAlbumForm {

    /** 相册名字 */
    @NotBlank(message = "相册名不能为空")
    private String name;
    /** 相册描述 */
    @NotBlank(message = "相册描述不能为空")
    private String description;

}
