package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
     * @return 包括：
     * 1. 添加成功：{"status":true,"info":"添加成功","object":null}
     * 2. 分组已存在 : {"status":false,"info":"分组已存在","object":null}
     */
    @RequestMapping("/add")
    @ResponseBody
    public MsgInfo addGroup(HttpSession session, @RequestParam String name) {
        int userID = (int) session.getAttribute("userID");

        return groupService.addGroup(userID, name);
    }

    /**
     * 修改分组名称
     *
     * @param origin 原组名
     * @param name   修改后的组名
     * @return 包括：
     * 1. {"status":true,"info":"修改成功","object":null}
     * 2. {"status":false,"info":"原组不存在","object":null}
     * 3. {"status":false,"info":"组名已存在","object":null}
     */
    @RequestMapping("/rename")
    @ResponseBody
    public MsgInfo renameGroup(HttpSession session, @RequestParam String origin, @RequestParam String name) {
        int userID = (int) session.getAttribute("userID");

        return groupService.renameGroup(userID, origin, name);
    }

    /**
     * 删除分组
     *
     * @param name 组名
     * @return 包括
     * 1. {"status":true,"info":"删除成功","object":null}
     * 2. {"status":false,"info":"分组不存在","object":null}
     */
    @RequestMapping("/remove")
    @ResponseBody
    public MsgInfo removeGroup(HttpSession session, @RequestParam String name) {
        int userID = (int) session.getAttribute("userID");

        return groupService.removeGroup(userID, name);
    }

    /**
     * 为联系人分组
     *
     * @param contactID 联系人的userID
     * @param groupName 组名
     * @return 包括：
     * 1. {"status":true,"info":"分组成功","object":null}
     * 2. {"status":false,"info":"非联系人","object":null}
     * 3. {"status":false,"info":"分组不存在","object":null}
     */
    @RequestMapping("/group")
    public MsgInfo groupContact(HttpSession session, @RequestParam int contactID, @RequestParam String groupName) {
        int userID = (int) session.getAttribute("userID");

        return groupService.groupContact(userID, contactID, groupName);
    }

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param contactID 联系人的userID
     * @param origin    原组名
     * @param target    目标组名
     * @return 包括：
     * 1. {"status":true,"info":"移动成功","object":null}
     * 2. {"status":false,"info":"非联系人","object":null}
     * 3. {"status":false,"info":"原组不存在","object":null}
     * 4. {"status":false,"info":"目标分组不存在","object":null}
     */
    @RequestMapping("/move")
    @ResponseBody
    public MsgInfo moveContact(HttpSession session, int contactID, @RequestParam String origin, @RequestParam String target) {
        int userID = (int) session.getAttribute("userID");

        return groupService.moveContact(userID, contactID, origin, target);
    }
}
