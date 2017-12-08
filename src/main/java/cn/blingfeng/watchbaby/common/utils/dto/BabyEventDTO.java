package cn.blingfeng.watchbaby.common.utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@Data
public class BabyEventDTO {
    @JsonProperty("id")
    private String eventId;

    @JsonProperty("title")
    private String eventTitle;
    /** 宝宝事件 */
    @JsonProperty("content")
    private String eventDescription;
    /** 创建时间 */
    @JsonProperty("time")
    private Date createTime;

    public BabyEventDTO(String eventId,String eventTitle, String eventDescription, Date createTime) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.createTime = createTime;
    }
}
