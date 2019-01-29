package com.chengzi.database.dao;

import com.chengzi.entity.auth.Group;

import java.util.List;
import java.util.Map;

public interface GroupMapper {
    List<Group> queryAll(Map map);
    int addGroup(Group group);
    int addGroupMenu(Map map);
    int deleteGroupMenu(int group_id);
    int modifyGroup(Group group);
    int deleteGroup(int group_id);
    int count();
    Group selectGroup(String id);

}
