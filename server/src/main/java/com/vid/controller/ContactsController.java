package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.service.ContactsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by song on 17-2-6.
 * <p>
 * 联系人相关
 */
@Controller
@RequestMapping(value = "/contacts", method = RequestMethod.POST)
public class ContactsController {

    @Resource
    private ContactsService contactsService;

    /**
     * 获取所有联系人
     *
     * @return MsgInfo{"status":true,"info":"","object":{"groupList":[],"contacts":[[],[],...,[]]}}
     */
    @RequestMapping("/all")
    @ResponseBody
    public MsgInfo getAllContacts(HttpSession session) {
        int userID = (Integer) session.getAttribute("userID");

        return contactsService.getAllContacts(userID);
    }

    /**
     * 手动新建联系人，自己填写各项信息，没有视频
     *
     * @param profile json格式的联系人信息
     * @return 包括
     * 1. 联系人已存在： MsgInfo{"status":false,"info":"联系人已存在","object":null}
     * 2. profile参数错误： MsgInfo{"status":false,"info":"参数错误","object":null}
     * 3. 新建成功： MsgInfo{"status":true,"info":"新建成功","object":null}
     */
    @RequestMapping("/new")
    @ResponseBody
    public MsgInfo createContact(HttpSession session, @RequestParam String profile) {
        int userID = (Integer) session.getAttribute("userID");

        return contactsService.createContact(userID, profile);
    }

    /**
     * 根据视频添加联系人
     * 若已经是联系人，则添加到已有联系人
     *
     * @param name    联系人备注
     * @param videoID 视频id
     * @return 包括：
     * 1. {"status":true,"info":"添加成功","object":ContactProfile}
     * 2. {"status":false,"info":"视频不存在","object":null}
     */
    @RequestMapping("/new/video")
    @ResponseBody
    public MsgInfo createContactWithVideo(HttpSession session, @RequestParam String name, @RequestParam int videoID) {
        int userID = (Integer) session.getAttribute("userID");

        return contactsService.createContactWithVideo(userID, name, videoID);
    }

//    /**
//     * 根据视频添加到已有联系人
//     *
//     * @param contactName 已有联系人在系统中的用户名
//     * @param videoID     视频id
//     * @return
//     */
//    @RequestMapping("/add")
//    @ResponseBody
//    public MsgInfo addToExistingContact(HttpSession session, String contactName, String videoID) {
//        String username = (String) session.getAttribute("username");
//
//        return contactsService.addToExistingContact(username, contactName, videoID);
//    }

    /**
     * 根据联系人id获取联系人信息
     *
     * @param contactID 联系人的userID
     * @return 包括：
     * 1. {"status":true,"info":"","object":ContactProfile}
     * 2. {"status":false,"info":"联系人不存在","object":null}
     * 3. {"status":false,"info":"非联系人","object":null}
     */
    @RequestMapping("/profile")
    @ResponseBody
    public MsgInfo getContactProfile(HttpSession session, @RequestParam int contactID) {
        int userID = (Integer) session.getAttribute("userID");

        return contactsService.getContactsInfo(userID, contactID);
    }

    /**
     * 编辑联系人信息
     *
     * @param contactID 联系人的userID
     * @param profile   json格式的联系人信息
     * @return 包括：
     * 1. {"status":true,"info":"修改成功","object":null}
     * 2. {"status":false,"info":"联系人不存在","object":null}
     * 3. {"status":false,"info":"非联系人","object":null}
     * 4. {"status":false,"info":"参数错误","object":null}
     */
    @RequestMapping("/edit")
    @ResponseBody
    public MsgInfo editContactInfo(HttpSession session, @RequestParam int contactID, @RequestParam String profile) {
        int userID = (Integer) session.getAttribute("userID");

        return contactsService.editContactInfo(userID, contactID, profile);
    }

    /**
     * 删除联系人
     *
     * @param contactID 联系人的userID
     * @return 包括：
     * 1. {"status":true,"info":"删除成功","object":null}
     * 2. {"status":false,"info":"联系人不存在","object":null}
     * 3. {"status":false,"info":"非联系人","object":null}
     */
    @RequestMapping("/remove")
    @ResponseBody
    public MsgInfo removeContact(HttpSession session, @RequestParam int contactID) {
        int userID = (Integer) session.getAttribute("userID");

        return contactsService.removeContact(userID, contactID);
    }
}
