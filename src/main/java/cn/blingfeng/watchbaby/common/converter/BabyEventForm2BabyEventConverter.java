package cn.blingfeng.watchbaby.common.converter;

import cn.blingfeng.watchbaby.common.utils.form.BabyEventForm;
import cn.blingfeng.watchbaby.dataobject.BabyEvent;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
public class BabyEventForm2BabyEventConverter {
    public static BabyEvent converter(BabyEventForm babyEventForm){

        BabyEvent babyEvent = new BabyEvent();
        babyEvent.setEventDescription(babyEventForm.getContent());
        babyEvent.setEventTitle(babyEventForm.getTitle());
        babyEvent.setEventId(babyEventForm.getId());
        return babyEvent;
    }
}
