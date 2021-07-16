package com.chenk.kafkcanaltest.dto.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author: chenke
 * @since: 2021/7/16
 */
@Data
@TableName("`order`")
public class Order {
    private Integer id;
    private String order_name;
    private Date create_time;
}
