package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.model.ContactProfile;
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
        String username = (String) session.getAttribute("username");

        return contactsService.getAllContacts(username);
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
        String username = (String) session.getAttribute("username");

        return contactsService.createContact(username, profile);
    }

    /**
     * 根据视频新建联系人
     *
     * @param name    联系人备注
     * @param videoID 视频id
     * @return
     */
    @RequestMapping("/new/video")
    @ResponseBody
    public MsgInfo createContactWithVideo(HttpSession session, String name, String videoID) {
        String username = (String) session.getAttribute("username");

        return contactsService.createContactWithVideo(username, name, videoID);
    }

    /**
     * 根据视频添加到已有联系人
     *
     * @param contactName 已有联系人在系统中的用户名
     * @param videoID     视频id
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public MsgInfo addToExistingContact(HttpSession session, String contactName, String videoID) {
        String username = (String) session.getAttribute("username");

        return contactsService.addToExistingContact(username, contactName, videoID);
    }

    /**
     * 根据联系人备注获取联系人信息
     *
     * @param noteName 联系人的备注姓名
     * @return
     */
    @RequestMapping("/profile")
    @ResponseBody
    public MsgInfo getContactProfile(HttpSession session, String noteName) {
        String username = (String) session.getAttribute("username");

        return contactsService.getContactsInfo(username, noteName);
    }

    /**
     * 编辑联系人信息
     *
     * @param contactName 联系人在系统中的用户名
     * @param profile     联系人信息
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public MsgInfo editContactInfo(HttpSession session, String contactName, String profile) {
        String username = (String) session.getAttribute("username");
        ContactProfile contactProfile = new ContactProfile();
        // TODO profile

        return contactsService.editContactInfo(username, contactProfile);
    }

    /**
     * 删除联系人
     *
     * @param contactName 联系人id
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public MsgInfo removeContact(HttpSession session, String contactName) {
        String username = (String) session.getAttribute("username");

        return contactsService.removeContact(username, contactName);
    }

    /**
     * 搜索联系人
     *
     * @param content 搜索内容
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public MsgInfo searchContact(HttpSession session, String content) {
        String username = (String) session.getAttribute("username");

        return contactsService.searchContact(username, content);
    }
}
