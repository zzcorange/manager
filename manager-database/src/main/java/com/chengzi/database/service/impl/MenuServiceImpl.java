package com.chengzi.database.service.impl;

import com.chengzi.database.dao.MenuMapper;
import com.chengzi.database.service.MenuService;
import com.chengzi.entity.auth.Menu;
import com.chengzi.entity.auth.MenuAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Override
    public String queryAllForZTree() {
        List<Menu> list = menuMapper.queryAll();
        System.out.println(">>>>>>>>>>>>>>>>>.."+list.size());
        System.out.println(">>>>>>>>>>>>>>>>>.getId."+list.get(0).getId());
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        list.forEach(menu -> {
            System.out.println(">>>>>>>>>>>>>>>>>.."+menu.getId());
            sb.append(menu.toZTreeString());
            sb.append(",");
        });
        if(sb.charAt(sb.length()-1)==','){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean modify(List<MenuAction> list) {
        list.forEach((menuAction)->{
          switch (menuAction.getAction()){
              case  "create":
                  menuMapper.createMenu(menuAction);
                  insertMenuAuthority(menuAction);
                  break;
              case  "add":
                  menuMapper.createMenu(menuAction);
                  insertMenuAuthority(menuAction);
                  break;
              case "modify":
                  System.out.println(">>>>>>>>>>>>>>>>>>"+menuAction.toString());
                  menuMapper.updateMenu(menuAction);
                  deleteMenuAuthority(menuAction);
                  insertMenuAuthority(menuAction);
                  break;
              case "delete":
                  menuMapper.deleteMenu(menuAction);
                  deleteMenuAuthority(menuAction);
                  break;
          }
        });
        return true;
    }
    @Transactional(propagation =Propagation.MANDATORY)
    public  void deleteMenuAuthority(MenuAction menuAction){
        menuMapper.deleteMenuAuthority(menuAction.getId());
    }
    @Transactional()
    public void insertMenuAuthority(MenuAction menuAction){
        if(menuAction.getAuthority()==null||menuAction.getAuthority().length()==0)
            return;
        String[] authoritys = menuAction.getAuthority().split(",");
        Map<String,String> map = new HashMap<String,String>();
        map.put("menuId",menuAction.getId());
        for(String authority:authoritys){
           map.put("url",authority);
           menuMapper.insertMenuAuthority(map);
        }
    }

}
