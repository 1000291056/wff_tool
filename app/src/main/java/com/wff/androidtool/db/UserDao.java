package com.wff.androidtool.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wufeifei on 2017/2/24.
 */

public class UserDao {
    private DBHelp mDBHelp;

    public UserDao(Context context) {
        mDBHelp = new DBHelp(context, "user");

    }

    private void insert() {
        SQLiteDatabase sqLiteDatabase=mDBHelp.getWritableDatabase();
//        sqLiteDatabase.
    }
}
