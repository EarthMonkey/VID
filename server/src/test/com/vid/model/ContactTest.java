package com.vid.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by song on 17-2-11.
 */
public class ContactTest {
    public static void main(String[] args) {
        List<Contact> contactList = new ArrayList<>(10);

        contactList.add(new Contact("张三", "1", "1"));
        contactList.add(new Contact("李四", "1", "1"));
        contactList.add(new Contact("王五", "1", "1"));
        

        contactList.forEach(System.out::println);
        contactList.sort(Comparator.comparing(Contact::getNoteName));
        contactList.forEach(System.out::println);
    }
}