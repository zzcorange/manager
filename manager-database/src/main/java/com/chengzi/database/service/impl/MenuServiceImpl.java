package com.chengzi.database.service.impl;

import com.chengzi.database.dao.MenuMapper;
import com.chengzi.database.service.MenuService;
import com.chengzi.entity.auth.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public String queryAll() {
        List<Menu> list = menuMapper.queryAll();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        list.forEach(menu -> {
            sb.append(menu.toString());
            sb.append(",");
        });
        if(sb.charAt(sb.length()-1)==','){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("]");
        return sb.toString();
    }
}
