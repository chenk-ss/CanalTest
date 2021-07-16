package com.chenk.kafkcanaltest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenk.kafkcanaltest.dto.CanalMessageDTO;
import com.chenk.kafkcanaltest.dto.entity.Order;
import com.chenk.kafkcanaltest.dto.entity.User;
import com.chenk.kafkcanaltest.service.MysqlCommandService;
import com.chenk.kafkcanaltest.service.OrderService;
import com.chenk.kafkcanaltest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: chenke
 * @since: 2021/7/15
 */
@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    // 消费监听
    @KafkaListener(topics = {"canalTopic"})
    public void KafkaListener(ConsumerRecord<?, ?> record) {
        String value = (String) record.value();
        CanalMessageDTO canalMessageDTO = JSONObject.parseObject(value, CanalMessageDTO.class);
        if (canalMessageDTO.getIsDdl()) {
            log.info("DDL:{}", canalMessageDTO.getSql());
            return;
        }
        Class clazz;
        MysqlCommandService service;
        switch (canalMessageDTO.getTable()) {
            case "order":
                clazz = Order.class;
                service = orderService;
                break;
            case "user": {
                clazz = User.class;
                service = userService;
                break;
            }
            default:
                return;
        }
        sync(canalMessageDTO.getType(), clazz, service, canalMessageDTO.getData().toString());
    }

    /**
     * 同步数据
     *
     * @param type
     * @param clazz
     * @param service
     * @param data
     */
    void sync(String type, Class clazz, MysqlCommandService service, String data) {
        switch (type) {
            case "INSERT":
                JSON.parseArray(data, clazz).stream().forEach(object->service.insert(object));
                break;
            case "UPDATE":
                JSON.parseArray(data, clazz).stream().forEach(object->service.update(object));
                break;
            case "DELETE":
                JSON.parseArray(data, clazz).stream().forEach(object->service.delete(object));
                break;
            default:
                break;
        }
    }

}