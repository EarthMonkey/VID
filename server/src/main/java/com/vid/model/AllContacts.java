package com.vid.model;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.*;

/**
 * Created by song on 17-2-12.
 * <p>
 * 所有联系人
 */
@SuppressWarnings("unchecked")
public class AllContacts {

    /**
     * userID，对应id字段
     */
    private int userID;

    /**
     * 系统用户名
     */
    private String username;

    /**
     * 用户所有分组组名列表
     */
    private List<String> groupList;

    /**
     * 所有联系人列表，按联系人姓名首字母分为27个list
     * 第0~25对应A～Z
     * 第26项为数字、特殊字符
     */
    private List[] contacts = new List[27];

    /**
     * 初始化list
     */
    private void init(List<Contact> contactList) {
        for (int i = 0; i < contacts.length; i++) {
            contacts[i] = new ArrayList();
        }

        try {
            for (Contact contact : contactList) {
                // 获取第一个字的拼音
                String pinyin = PinyinHelper.getShortPinyin(contact.getNoteName().substring(0, 1));
                char first = Character.toLowerCase(pinyin.charAt(0));

                if (Character.isLetter(first)) {
                    contacts[first - 'a'].add(contact);
                } else {
                    contacts[contacts.length - 1].add(contact);
                }
            }
        } catch (PinyinException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对同一list内的联系人进行排序
     */
    private void sortContacts() {
        Comparator<Contact> comparator = (contact1, contact2) -> {
            String pinyin1, pinyin2;

            try {
                pinyin1 = PinyinHelper.getShortPinyin(contact1.getNoteName());
                pinyin2 = PinyinHelper.getShortPinyin(contact2.getNoteName());

                return pinyin1.compareTo(pinyin2);
            } catch (PinyinException e) {
                e.printStackTrace();
            }

            return 0;
        };

        for (List contact : contacts) {
            contact.sort(comparator);
        }
    }

    public AllContacts(int userID, List<Contact> contactList, List<String> groupList) {
        this(userID, "", contactList, groupList);
    }

    public AllContacts(int userID, String userName, List<Contact> contactList, List<String> groupList) {
        this.userID = userID;
        this.username = userName;
        this.groupList = groupList;

        // 初始化
        init(contactList);
        // 排序
        sortContacts();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<String> groupList) {
        this.groupList = groupList;
    }

    public List[] getContacts() {
        return contacts;
    }

    public void setContacts(List[] contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "AllContacts{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", groupList=" + groupList +
                ", contacts=" + Arrays.toString(contacts) +
                '}';
    }
}
