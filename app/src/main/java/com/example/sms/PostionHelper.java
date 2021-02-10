package com.example.sms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PostionHelper extends SQLiteOpenHelper {
    public static String nom_fichier="mespositions.db";
    public  static int version=1;
    public static final String table_pos="position";
    public static final String col_id="Id";
    public static final String col_num="Numero";
    public static final String col_long="Longitude";
    public static final String col_lat="Latitude";

    String req="create table "+table_pos+" (" +
          col_id +" integer primary Key autoincrement," +
            col_num +" Text not null," +
            col_long +" Text not null," +
            col_lat +" Text not null)";

    public PostionHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
        public void onCreate(SQLiteDatabase db) {
          db.execSQL(req);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+table_pos);
       onCreate(db) ;
        }
    }
