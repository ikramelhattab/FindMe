package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Ajout extends AppCompatActivity {
    EditText edalti,edlong,ph;
    Button val,qte;
    PositionManager pm;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

     Bundle b =this.getIntent().getExtras();
     if(b!=null){
         String body=b.getString("BODY");
         String num=b.getString("NUMBER");
}


            ph=findViewById(R.id.ednum);
            edlong=findViewById(R.id.edlong);
            edalti=findViewById(R.id.edalt);
            val=findViewById(R.id.val);
            qte=findViewById(R.id.qte);

            qte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Ajout.this.finish();
                }
            });

            val.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String num = ph.getText().toString();
                    String alt =edalti.getText().toString();
                    String log =edlong.getText().toString();
                    pm =new PositionManager (Ajout.this,PostionHelper.nom_fichier,PostionHelper.version);
                   // pm.ouvrir("mon_fichier.txt",1);
                    pm.inserer(num,alt,log);
                    Toast.makeText(Ajout.this,""+num+alt+log ,Toast.LENGTH_LONG).show();

                }

            });

            Bundle bu = this.getIntent().getExtras();
            if (bu!=null){
                String numero= bu.getString("NUMBER");
                ph.setText(numero);

                String body= bu.getString("BODY");

                String[] t =body.split("#");
                edlong.setText(t[1]);
                edalti.setText(t[2]);

            }
            else {
                //ajout pos local
                ph.setHint("saisir num ");
                FusedLocationProviderClient mClient=
                        LocationServices.getFusedLocationProviderClient(this.getApplicationContext()
                        );

                mClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                      if (location !=null){
                          edalti.setText(location.getLatitude()+"");
                          edlong.setText(location.getLongitude()+"");

                      }
                    }
                });

                // màj
                LocationRequest request =LocationRequest.create().setInterval(10).setSmallestDisplacement(10) ;
                LocationCallback mcall =new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        Location l = locationResult.getLastLocation();

                        if (l!=null){
                            edalti.setText(l.getLatitude()+"");
                            edlong.setText(l.getLongitude()+"");

                        }
                    }
                };
                        mClient.requestLocationUpdates( request,mcall,null);
            }

        }

    }
