package com.chengzi.database.service;

import com.chengzi.entity.auth.MenuAction;

import java.util.List;

public interface MenuService {
    String queryAll();
    String queryAllForZTree();
    boolean modify(List<MenuAction> list);

}
