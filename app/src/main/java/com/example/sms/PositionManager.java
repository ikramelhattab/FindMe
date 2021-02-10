package com.example.sms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PositionManager {
Context con;
    SQLiteDatabase mabase;
    public PositionManager(Context con, String fichier,int version) {
        this.con = con;
        ouvrir(fichier,version);
    }


    private void ouvrir(String nom_fichier, int version){

        PostionHelper helper =new PostionHelper(con,nom_fichier,null,version);
        mabase= helper.getWritableDatabase();
   }
    public long inserer (String num,String lon,String lat){
      ContentValues v = new ContentValues();
      v.put(PostionHelper.col_num,num);
      v.put(PostionHelper.col_lat,lat);
      v.put(PostionHelper.col_long,lon);
      long a=mabase.insert(PostionHelper.table_pos,null,v);
    return a;
}

    public ArrayList<Position> selectionnertout() {
        ArrayList<Position> a =new ArrayList<Position>();
        Cursor cr =mabase.query(PostionHelper.table_pos,
                new String[]{PostionHelper.col_id,PostionHelper.col_num,PostionHelper.col_lat,PostionHelper.col_long},
                null,
                null,
                PostionHelper.col_num,
                null,
                PostionHelper.col_id);

        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            int i = cr.getInt(0);
            String num = cr.getString(1);
            String la = cr.getString(2);
            String lo = cr.getString(3);
            Position p = new Position(i, num, la, lo);
            a.add(p);
            cr.moveToNext();
        }

        return a;
    }
    public int supprimerNum(String num){
        int a=1;
        mabase.delete(PostionHelper.table_pos,
                PostionHelper.col_num+" = "+num,
                null);
                return a;
    }

}
