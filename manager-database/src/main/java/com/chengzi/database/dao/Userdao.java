package com.chengzi.database.dao;

import com.chengzi.database.entity.User;

public interface Userdao {
    User queryByName(String name);
}
