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
     * 1. 添加成功：{"status":true,"info":"添加成功","object":groupID}
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
     * @param groupID groupID
     * @param name    修改后的组名
     * @return 包括：
     * 1. {"status":true,"info":"修改成功","object":null}
     * 2. {"status":false,"info":"分组不存在","object":null}
     * 3. {"status":false,"info":"已存在同名分组","object":null}
     */
    @RequestMapping("/rename")
    @ResponseBody
    public MsgInfo renameGroup(HttpSession session, int groupID, @RequestParam String name) {
        int userID = (int) session.getAttribute("userID");

        return groupService.renameGroup(userID, groupID, name);
    }

    /**
     * 删除分组
     *
     * @param groupID groupID
     * @return 包括
     * 1. {"status":true,"info":"删除成功","object":null}
     * 2. {"status":false,"info":"分组不存在","object":null}
     */
    @RequestMapping("/remove")
    @ResponseBody
    public MsgInfo removeGroup(HttpSession session, int groupID) {
        int userID = (int) session.getAttribute("userID");

        return groupService.removeGroup(userID, groupID);
    }

    /**
     * 为联系人分组
     *
     * @param contactID 联系人的userID
     * @param groupID groupID
     * @return 包括：
     * 1. {"status":true,"info":"分组成功","object":null}
     * 2. {"status":false,"info":"非联系人","object":null}
     * 3. {"status":false,"info":"分组不存在","object":null}
     */
    @RequestMapping("/group")
    @ResponseBody
    public MsgInfo groupContact(HttpSession session, int contactID, int groupID) {
        int userID = (int) session.getAttribute("userID");

        return groupService.groupContact(userID, contactID, groupID);
    }

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param contactID 联系人的userID
     * @param target    目标组groupID
     * @return 包括：
     * 1. {"status":true,"info":"移动成功","object":null}
     * 2. {"status":false,"info":"非联系人","object":null}
     * 3. {"status":false,"info":"原组不存在","object":null}
     * 4. {"status":false,"info":"目标分组不存在","object":null}
     */
    @RequestMapping("/move")
    @ResponseBody
    @Deprecated
    public MsgInfo moveContact(HttpSession session, int contactID, int origin, int target) {
        int userID = (int) session.getAttribute("userID");

        return groupService.moveContact(userID, contactID, origin, target);
    }
}
