package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.model.ContactProfile;
import com.vid.service.ContactsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    ContactsService contactsService;

    /**
     * 获取所有联系人
     *
     * @return
     */
    @RequestMapping("/all")
    @ResponseBody
    public MsgInfo getAllContacts(HttpSession session) {
        String username = (String) session.getAttribute("username");

        return contactsService.getAllContacts(username);
    }

    /**
     * 手动新建联系人，自己填写各项信息，没有视频
     * <p>
     * TODO profile -> ContactProfile
     *
     * @return
     */
    @RequestMapping("/new")
    @ResponseBody
    public MsgInfo createContact(HttpSession session, String profile) {
        String username = (String) session.getAttribute("username");
        ContactProfile contactProfile = new ContactProfile();

        return contactsService.createContact(username, contactProfile);
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
    public MsgInfo createContact(HttpSession session, String name, String videoID) {
        String username = (String) session.getAttribute("username");

        return contactsService.createContact(username, name, videoID);
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
     * 根据联系人id获取联系人信息
     *
     * @param contactName 联系人在系统中的用户名
     * @return
     */
    @RequestMapping("/profile")
    @ResponseBody
    public MsgInfo getContactProfile(HttpSession session, String contactName) {
        String username = (String) session.getAttribute("username");

        return contactsService.getContactsInfo(username, contactName);
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
