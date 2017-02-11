package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by song on 17-2-11.
 * <p>
 * 联系人分组相关
 */
@Controller
@RequestMapping(value = "/contacts/group", method = RequestMethod.POST)
public class GroupController {

    @Resource
    private GroupService groupService;

    /**
     * 增加分组
     *
     * @param name 分组名称
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public MsgInfo addGroup(HttpSession session, String name) {
        String username = (String) session.getAttribute("username");

        return groupService.addGroup(username, name);
    }

    /**
     * 修改分组名称
     *
     * @param origin 原组名
     * @param name     修改后的组名
     * @return
     */
    @RequestMapping("/rename")
    @ResponseBody
    public MsgInfo renameGroup(HttpSession session, String origin, String name) {
        String username = (String) session.getAttribute("username");

        return groupService.renameGroup(username, origin, name);
    }

    /**
     * 删除分组
     *
     * @param name 组名
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public MsgInfo removeGroup(HttpSession session, String name) {
        String username = (String) session.getAttribute("username");

        return groupService.removeGroup(username, name);
    }

    /**
     * 为联系人分组
     *
     * @param contactName 联系人在系统中的用户名
     * @param groupName   组名
     * @return
     */
    @RequestMapping("/group")
    public MsgInfo groupContact(HttpSession session, String contactName, String groupName) {
        String username = (String) session.getAttribute("username");

        return groupService.groupContact(username, contactName, groupName);
   }

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param contactName 联系人在系统中的用户名
     * @param origin      原组名
     * @param target 目标组名
     * @return
     */
    @RequestMapping("/move")
    @ResponseBody
    public MsgInfo moveContact(HttpSession session, String contactName, String origin, String target) {
        String username = (String) session.getAttribute("username");

        return groupService.moveContact(username, contactName, origin, target);
    }
}
