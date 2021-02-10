package com.example.sms;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyPositionAdapter extends BaseAdapter {
Context con;
ArrayList<Position> data;

    public MyPositionAdapter(Context con, ArrayList<Position> data) {
        this.con = con;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       //creation des view :oncreate
        LayoutInflater inf=LayoutInflater.from(con);
        CardView element=(CardView)inf.inflate(R.layout.view_position,null);

        //récuperations des views : Holder
        TextView tv_id=element.findViewById(R.id.tv_id_view);
        TextView tv_num=element.findViewById(R.id.tv_num_view);
        TextView tv_lat=element.findViewById(R.id.tv_lat_view);
        TextView tv_lon=element.findViewById(R.id.tv_lon_view);

        Button btn_call=element.findViewById(R.id.btn_call_view);
        Button btn_map=element.findViewById(R.id.btn_map_view);
        Button btn_sms=element.findViewById(R.id.btn_sms_view);

         //affectation des valeurs :OnBind
        Position p= data.get(position);
        tv_id.setText(""+p.id);
        tv_num.setText(""+p.numero);
        tv_lon.setText(""+p.longitude);
        tv_lat.setText(""+p.altitude);



        return element;

    }
}
