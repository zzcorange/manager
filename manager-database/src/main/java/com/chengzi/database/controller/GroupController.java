package com.chengzi.database.controller;

import com.chengzi.database.dao.GroupMapper;
import com.chengzi.database.dao.MenuMapper;
import com.chengzi.database.service.GroupService;
import com.chengzi.entity.auth.Group;
import com.chengzi.entity.auth.Menu;
import com.chengzi.enums.Code;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private GroupService groupService;
    /**
     * 插入分组数据
     * 先插入数据group数据
     * 再插入group-menu数据
     * @param group
     * @return
     */
    @PostMapping("/add")
    public int add(@RequestBody Group group){
        int group_id = groupMapper.addGroup(group);//插入分组数据
        if(group_id<1)//插入失败
            return 0;
        //插入分组所能访问的页面
        Map<String,Object> map = new HashMap<>();
        map.put("group_id",group_id);
        for(String s:group.getMenulist()){
              map.put("menu_id",s);
            groupMapper.addGroupMenu(map);
        }
        return 1;
    }

    /**
     * 修改分组信息
     * 1.先修改分组信息
     * 2.删除分组-菜单信息
     * 3.插入新的分组-菜单信息
     * @param group
     * @return
     */
    @PostMapping("/modify")
    public int modify(@RequestBody Group group){
        groupMapper.modifyGroup(group);
        groupMapper.deleteGroupMenu(group.getId());
        //插入分组所能访问的页面
        Map<String,Object> map = new HashMap<>();
        map.put("group_id",group.getId());
        for(String s:group.getMenulist()){
            map.put("menu_id",s);
            groupMapper.addGroupMenu(map);
        }
        return 1;

    }
    /**
     * 删除分组
     * 1.删除分组信息
     * 2.删除分组能访问的权限信息
     */
    @PostMapping("/delete")
    public void delete(@RequestParam int group_id){
        groupMapper.deleteGroup(group_id);
        groupMapper.deleteGroupMenu(group_id);
    }

    /**
     * 查询所有的权限信息
     * @return
     */
    @GetMapping("/queryAll")
    public String queryAll(@RequestParam int page,@RequestParam int rows){
        Map<String,Integer> map = new HashMap<>();
        map.put("begin",(page-1)*rows);
        map.put("end",(page)*rows-1);
        List<Group> groups = groupMapper.queryAll(map);
        JSONArray jsonArray = new JSONArray();
        groups.forEach((group -> {
            jsonArray.put(group.toJSONObject());
        }));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",groupMapper.count());
        jsonObject.put("rows",jsonArray);
        return jsonObject.toString();

    }
    @GetMapping("/queryAllMenu")
    public String queryAllMenu(){
        List<Menu> list = menuMapper.queryAll();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        list.forEach(menu -> {
            sb.append(menu.toGroupString());
            sb.append(",");
        });
        if(sb.charAt(sb.length()-1)==','){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("]");
        return sb.toString();
    }
    @PostMapping("/addGroup")
    public int addGroup(@RequestBody Group group){
        return groupService.addGroup(group);
    }
    @GetMapping("/queryAllMenuById")
    public String queryAllMenuById(@RequestParam String id){
        Group group = groupMapper.selectGroup(id);
        List<String> open = group.getMenulist();
        List<Menu> list = menuMapper.queryAll();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        System.out.println(open);
        list.forEach(menu -> {
            System.out.println(menu.getId());
            sb.append(menu.toGroupString(open));
            sb.append(",");
        });
        if(sb.charAt(sb.length()-1)==','){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("]");
        return sb.toString();

    }
    @GetMapping("/queryOne")
    public String queryOne(@RequestParam String id){
        Group group = groupMapper.selectGroup(id);
        return null==group?Code.EMPTY.toString():Code.SUCCESS.toString(group.toJSONObject().toString());
    }
}
