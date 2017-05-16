package com.riq.mylibrary.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.TextView;

/**
 * Created by riq on 2017/5/16.
 * 获取联系人,并设置到TextView（姓名,电话）
 * 使用方法:
 * 1.点击事件 getContact(this);
 * 2.重写方法
 *
 * @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 * switch (requestCode) {
 * case 0x100:  (对应getContact(this, 0x100))
 * setContact(this, data, etName, etPhone...);
 * break;
 * }
 * super.onActivityResult(requestCode, resultCode, data);
 * }
 */

public class ContactUtil {

    public static <T extends TextView> void setContact(Context context, Intent data, T... t) {
        if (data == null) {
            return;
        }
        Uri uri = data.getData();
        String[] contacts = getPhoneContacts(context, uri);
        assert contacts != null;
        String name = contacts[0];
        String phone = contacts[1];
        //只有一个TextView,则在上面显示电话,两个TextView就显示姓名和手机号
        if (t.length == 1) {
            (t[0]).setText(phone);   //手机号
        } else if (t.length == 2) {
            (t[0]).setText(name);    //姓名
            (t[1]).setText(phone);   //手机号
        }
    }

    private static String[] getPhoneContacts(Context context, Uri uri) {
        String[] contact = new String[2];
        //得到ContentResolver对象
        ContentResolver cr = context.getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if (phone != null) {
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            if (phone != null) {
                phone.close();
            }
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }

    public static void getContact(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), requestCode);

    }
}
