package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.model.User;
import com.vid.service.ContactsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by song on 17-2-6.
 * <p>
 * 联系人相关
 */
@Controller
@RequestMapping("/contacts")
public class ContactsController {

    @Resource
    ContactsService contactsService;

    /**
     * 根据id获取所有联系人
     *
     * @param id 用户id(邮箱/手机号)
     */
    @RequestMapping("/all")
    @ResponseBody
    public MsgInfo getAllContacts(String id) {
        return new MsgInfo(true, "test");
    }

    /**
     * 获取联系人信息
     *
     * @param id 联系人id
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public MsgInfo getContactInfo(String id) {
        return null;
    }

    /**
     * 添加联系人
     * TODO 是否分配id
     * @param name
     * @return
     */
    public MsgInfo AddContact(String name) {
        return null;
    }

    /**
     * 编辑联系人信息
     *
     * @param id   联系人id
     * @param user
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public MsgInfo editContactInfo(String id, User user) {
        return null;
    }

    /**
     * 删除联系人
     *
     * @param id 联系人id
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public MsgInfo removeContact(String id) {
        return null;
    }

    /**
     * 增加分组
     *
     * @param name 分组名称
     * @return
     */
    @RequestMapping("/group/add")
    @ResponseBody
    public MsgInfo addGroup(String name) {
        return null;
    }

    /**
     * 修改分组名称
     *
     * @param original 原组名
     * @param name     修改后的组名
     * @return
     */
    @RequestMapping("/group/edit")
    @ResponseBody
    public MsgInfo editGroup(String original, String name) {
        return null;
    }

    /**
     * 删除分组
     *
     * @param name 组名
     * @return
     */
    @RequestMapping("/group/remove")
    @ResponseBody
    public MsgInfo removeGroup(String name) {
        return null;
    }

    /**
     * 为联系人分组
     *
     * @param id        联系人id
     * @param groupName 组名
     * @return
     */
    @RequestMapping("/group/group")
    public MsgInfo groupContact(String id, String groupName) {
        return null;
    }

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param id          联系人id
     * @param original    原组名
     * @param destination 目标组名
     * @return
     */
    @RequestMapping("/group/move")
    @ResponseBody
    public MsgInfo moveContact(String id, String original, String destination) {
        return null;
    }
}
