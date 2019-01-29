package com.chengzi.database.service.impl;

import com.chengzi.database.dao.GroupMapper;
import com.chengzi.database.service.GroupService;
import com.chengzi.entity.auth.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int addGroup(Group group) {
        groupMapper.addGroup(group);
        System.out.println("插入后的id="+group.getId());
        addGroupMenu(group);
        return group.getId();
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public void addGroupMenu(Group group){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("group_id",group.getId());
        for(String temp : group.getMenulist()){
            map.put("menu_id",temp);
            groupMapper.addGroupMenu(map);
        }
    }

}
