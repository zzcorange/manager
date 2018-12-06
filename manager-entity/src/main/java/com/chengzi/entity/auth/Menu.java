package com.chengzi.entity.auth;

import com.chengzi.entity.BaseEntity;

import java.util.List;

public class Menu extends BaseEntity {
    private int id;
    private int parent_id;
    private String name;
    private String url;
    private List<Menu> childMenu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
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

    public List<Menu> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<Menu> childMenu) {
        this.childMenu = childMenu;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"");
        sb.append(id);
        sb.append("\",");
        sb.append("\"parent_id\":\"");
        sb.append(parent_id);
        sb.append("\",");
        sb.append("\"name\":\"");
        sb.append(name);
        sb.append("\",");
        sb.append("\"url\":\"");
        sb.append(url);
        sb.append("\",");
        sb.append("\"childMenu\":[");
        for(Menu menu:childMenu){
            sb.append(menu.toString());
            sb.append(",");
        }
        if(sb.charAt(sb.length()-1)==',')
            sb.deleteCharAt(sb.length()-1);
        sb.append("]}");
        return sb.toString();
    }
}
