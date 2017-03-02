package com.vid.model;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import static org.junit.Assert.*;

/**
 * Created by song on 17-3-2.
 */
public class AllContactsTest {
    public static void main(String[] args) throws PinyinException {
        System.out.println(PinyinHelper.getShortPinyin("查大江"));
        System.out.println(PinyinHelper.getShortPinyin("查解放"));
        System.out.println(PinyinHelper.getShortPinyin("陈解放"));
        System.out.println(PinyinHelper.getShortPinyin("陈华"));
        System.out.println(PinyinHelper.getShortPinyin("查红雪"));
        System.out.println(PinyinHelper.getShortPinyin("查大江"));
        System.out.println(PinyinHelper.convertToPinyinString("查解放", "", PinyinFormat.WITHOUT_TONE));
    }
}