package com.vid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 17-2-12.
 */
public class AllContactsTest {
    public static void main(String[] args) {
        List<Contact> contactList = new ArrayList<>(10);

        contactList.add(new Contact("张三", "朋友", "123"));
        contactList.add(new Contact("张三a", "朋友", "123"));
        contactList.add(new Contact("a张三", "朋友", "123"));
        contactList.add(new Contact("1张三", "朋友", "123"));
        contactList.add(new Contact("aaa", "朋友", "123"));
        contactList.add(new Contact("李四", "朋友", "123"));
        contactList.add(new Contact("宋", "朋友", "123"));
        contactList.add(new Contact("李", "朋友", "123"));
        contactList.add(new Contact("怡情", "朋友", "123"));
    }
}
