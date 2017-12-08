package cn.blingfeng.watchbaby.user.event.service.impl;

import cn.blingfeng.watchbaby.common.enums.ResultEnum;
import cn.blingfeng.watchbaby.common.exception.BabyException;
import cn.blingfeng.watchbaby.common.utils.KeyUtil;
import cn.blingfeng.watchbaby.common.utils.dto.BabyEventDTO;
import cn.blingfeng.watchbaby.dataobject.BabyEvent;
import cn.blingfeng.watchbaby.user.event.repository.BabyEventRepository;
import cn.blingfeng.watchbaby.user.event.service.BabyEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@Service
@Slf4j
public class BabyEventServiceImpl implements BabyEventService {
    @Autowired
    private BabyEventRepository eventRepository;

    @Override
    public Page<BabyEventDTO> babyEventList(String userId, Pageable pageable) {
        Page<BabyEvent> babyEventPage = eventRepository.findByUserId(userId, pageable);
        if (babyEventPage.getContent() == null) {
            return null;
        }
        List<BabyEventDTO> babyEventDTOList = babyEventPage.getContent().stream().map(e -> new BabyEventDTO(e.getEventId(),e.getEventTitle(), e.getEventDescription(), e.getCreateTime())).collect(Collectors.toList());
        Page<BabyEventDTO> babyEventDTOPage = new PageImpl<>(babyEventDTOList,pageable,babyEventPage.getTotalElements());
        return babyEventDTOPage;
    }

    @Override
    public BabyEvent addBabyEvent(BabyEvent babyEvent) {
        String key = KeyUtil.getKey();
        babyEvent.setEventId(key);
        BabyEvent result = eventRepository.save(babyEvent);
        return result;
    }

    @Override
    public void deleteBabyEvent(String userId, String eventId) {
        BabyEvent babyEvent = eventRepository.findOne(eventId);
        if(!babyEvent.getUserId().equalsIgnoreCase(userId)){
           log.error("【删除宝宝事件】 事件不属于此用户 userId = {},eventId = {}",userId,eventId);
           throw new BabyException(ResultEnum.EVENT_NOT_OWNER);
        }
        eventRepository.delete(babyEvent);
    }

    @Override
    public BabyEvent updateBabyEvent(BabyEvent babyEvent) {
        BabyEvent result = eventRepository.save(babyEvent);
        return result;
    }
}
