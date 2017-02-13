package com.vid.model;

import com.vid.utils.ObjectMerge;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by song on 17-2-12.
 */
public class ContactProfileTest {
    public static void main(String[] args) throws JSONException, ClassNotFoundException, IllegalAccessException {
        String json = "{\"name\": \"tom\",\"contactName\":\"null\",\"group\": \"朋友\",\"phoneNum\": \"132****1257\"}";

        JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject.getString("contactName"));
        System.out.println(jsonObject.getString("contactName").equals("sdf"));
        ContactProfile contactProfile = new ContactProfile();
        System.out.println(contactProfile);
        ObjectMerge.merge(contactProfile, json);
        System.out.println(contactProfile);
    }
}