package com.chengzi.entity.auth;

import com.chengzi.entity.BaseEntity;
import com.chengzi.entity.tools.DateFormatUtils;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class Group extends BaseEntity {
    private int id;
    private String name;
    private List<String> menulist;
    private Date createTime;
    private Date modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMenulist() {
        return menulist;
    }

    public void setMenulist(List<String> menulist) {
        this.menulist = menulist;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String toListString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("createTime", DateFormatUtils.toyyyyMMddHHmmss(createTime));
        jsonObject.put("modifyTime", DateFormatUtils.toyyyyMMddHHmmss(modifyTime));
        return jsonObject.toString();
    }
    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("createTime", DateFormatUtils.toyyyyMMddHHmmss(createTime));
        jsonObject.put("modifyTime", DateFormatUtils.toyyyyMMddHHmmss(modifyTime));
        return jsonObject;
    }
}
