package com.chengzi.database.dao;

import com.chengzi.entity.auth.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> queryAll();
    int insertMenu(Menu menu);
}
