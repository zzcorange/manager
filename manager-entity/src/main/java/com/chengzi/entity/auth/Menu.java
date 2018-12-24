package com.chengzi.entity.auth;

import com.chengzi.entity.BaseEntity;

import java.util.List;

public class Menu extends BaseEntity {
    /**
     * 菜单ID
     */
    private String id;
    /**
     * 父菜单id
     */
    private String parent_id;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单跳转链接
     */
    private String url;
    /**
     * 子菜单集合
     */
    private List<Menu> childMenu;
    /**
     * 该菜单所有拥有的权限
     */
    private String authority;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
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
        if(childMenu.size()==0){
            sb.append("\"url\":\"");
            sb.append(url);
            sb.append("\",");
        }


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
    public String toZTreeString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"");
        sb.append(id);
        sb.append("\",");
        sb.append("\"pId\":\"");
        sb.append(parent_id);
        sb.append("\",");
        sb.append("\"name\":\"");
        sb.append(name);
        sb.append("\",");
        sb.append("\"urldata\":\"");
        sb.append(url);
        sb.append("\",");
        sb.append("\"authority\":\"");
        sb.append(authority);
        sb.append("\"},");
        for(Menu menu:childMenu){
            sb.append(menu.toZTreeString());
            sb.append(",");
        }
        if(sb.charAt(sb.length()-1)==',')
            sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }
}
