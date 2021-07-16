package com.chenk.kafkcanaltest.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: chenke
 * @since: 2021/7/15
 */
@Data
public class CanalMessageDTO<T> {
    private List<T> data;
    private String databases;
    private Boolean isDdl;
    private String old;
    private String table;
    private String type;
    private String sql;
}
