package com.chengzi.database.dao;

import com.chengzi.entity.auth.Menu;
import com.chengzi.entity.auth.MenuAction;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    List<Menu> queryAll();
    int insertMenu(Menu menu);
    int createMenu(MenuAction menuAction);
    int updateMenu(MenuAction menuAction);
    int deleteMenu(MenuAction menuAction);
    int deleteMenuAuthority(String menuId);
    int insertMenuAuthority(Map map);
}
