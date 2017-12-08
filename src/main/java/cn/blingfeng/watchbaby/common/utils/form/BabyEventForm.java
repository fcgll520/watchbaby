package cn.blingfeng.watchbaby.common.utils.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@Data
public class BabyEventForm {
    @NotBlank(message = "eventId不能为空")
    private String id;

    private String content;

    @NotBlank(message = "标题不能为空")
    private String title;

}
