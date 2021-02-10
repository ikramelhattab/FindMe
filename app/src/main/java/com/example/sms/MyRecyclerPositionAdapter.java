package com.example.sms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.ArrayList;

public class MyRecyclerPositionAdapter extends RecyclerView.Adapter<MyRecyclerPositionAdapter.MyViewHolder> {
   Context con;
   ArrayList<Position>data;

    public MyRecyclerPositionAdapter(Context con, ArrayList<Position> data) {
        this.con = con;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creation des view :oncreate
        LayoutInflater inf=LayoutInflater.from(con);
        CardView element=(CardView)inf.inflate(R.layout.view_position,null);
        return new MyViewHolder(element);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //affectation des valeurs :OnBind
        Position p= data.get(position);
        holder.tv_id.setText(""+p.id);
        holder.tv_num.setText(""+p.numero);
        holder.tv_lon.setText(""+p.longitude);
        holder.tv_lat.setText(""+p.altitude);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id,tv_num,tv_lon,tv_lat;
        public MyViewHolder(@NonNull View element) {
            super(element);

             tv_id=element.findViewById(R.id.tv_id_view);
             tv_num=element.findViewById(R.id.tv_num_view);
             tv_lat=element.findViewById(R.id.tv_lat_view);
             tv_lon=element.findViewById(R.id.tv_lon_view);

            Button btn_call=element.findViewById(R.id.btn_call_view);
            Button btn_map=element.findViewById(R.id.btn_map_view);
            Button btn_sms=element.findViewById(R.id.btn_sms_view);
            btn_call.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View v) {
                    int indice=getAdapterPosition(); //renvoie l'element selectionné
                    Toast.makeText(con, "appel à " +data.get(indice).numero, Toast.LENGTH_SHORT).show();
                    //à faire :appel vers le numero
                    String num = tv_num.getText().toString();
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + num));
                    con.startActivity(callIntent);



                }
            });
            btn_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Position p =data.get(getAdapterPosition());
                    Intent i =new Intent(con,MapsActivity.class);
                    i.putExtra("Numero",p.numero);
                    i.putExtra("Longitude",p.longitude);
                    i.putExtra("Latitude",p.altitude);
                    con.startActivity(i);

                }
            });


        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Position p=data.get(getAdapterPosition());
                SmsManager manager = SmsManager.getDefault();

                Intent i =new Intent(con,MapsActivity.class);
                i.putExtra("Numero",p.numero);
                i.putExtra("Longitude",p.longitude);
                i.putExtra("Latitude",p.altitude);

                manager.sendTextMessage(p.numero,
                        null,
                        "Findme"+p.longitude+p.altitude,  //lat#long
                        //a faire : recuperer position courant

                        null,
                        null
                );
            }
        });
        }
    }


}
