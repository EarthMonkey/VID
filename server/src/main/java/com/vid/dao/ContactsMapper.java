package com.vid.dao;

import com.vid.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by song on 17-2-10.
 * <p>
 * 联系人相关
 */
@Service
public interface ContactsMapper {

    /**
     * 获取所有联系人
     *
     * @param username 用户名
     * @return 联系人列表
     */
    List<Contact> getAllContacts(String username);

    /**
     * 判断contactName是否是username的联系人
     *
     * @param username    用户名
     * @param contactName 联系人的用户名
     * @return 若是，返回true，否则返回false
     */
    boolean isContacts(String username, String contactName);
}
