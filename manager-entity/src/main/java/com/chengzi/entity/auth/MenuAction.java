package com.chengzi.entity.auth;

import com.chengzi.entity.BaseEntity;

/**
 * [{"name":"权限管理","url":"","action":"delete","parent_id":null}
 * ,{"name":"菜单管理","url":"/web/auth/menu.html","action":"delete","parent_id":"1","id":"2"},{"name":"用户管理","url":"/web/auth/user.html","action":"delete","parent_id":"1","id":"3"},{"name":"权限组管理","url":"/web/auth/role.html","action":"delete","parent_id":"1","id":"4"},{"name":"权限管理","url":"/web/auth/auth.html","action":"delete","parent_id":"1","id":"5"}]
 */
public class MenuAction extends BaseEntity {
    private String name;
    private String url;
    private String action;
    private String parent_id;
    private String id;
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
