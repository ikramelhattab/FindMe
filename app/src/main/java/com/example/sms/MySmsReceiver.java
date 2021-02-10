package com.example.sms;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MySmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       // Toast.makeText(context,"sms reçu",Toast.LENGTH_SHORT).show();
        String messageBody,phoneNumber;
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            Bundle bundle =intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    messageBody = messages[0].getMessageBody();
                    phoneNumber = messages[0].getDisplayOriginatingAddress();
if (messageBody.contains("Findme")){

    NotificationCompat.Builder mnotif =new NotificationCompat.Builder(context,"CHANNEL_FINDME");
    mnotif.setSmallIcon(android.R.drawable.ic_dialog_alert);
    mnotif.setContentTitle("nouvelle position reçu");
    mnotif.setContentText(messageBody);
    mnotif.setPriority(NotificationCompat.PRIORITY_DEFAULT);
    mnotif.setVibrate((new long[]{1000,2000,500,1000}));

    Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
     mnotif.setSound(sound);

   Intent i=new Intent(context,Ajout.class);
   i.putExtra("BODY",messageBody);
   i.putExtra("NUMBER",phoneNumber);
    PendingIntent pi =PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_ONE_SHOT);
   mnotif.setContentIntent(pi);

    NotificationManagerCompat manager=NotificationManagerCompat.from(context);

   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
   {
       NotificationChannel canal =new NotificationChannel(
               "CHANNEL_FINDME","Mon app FINDME",
               NotificationManager.IMPORTANCE_DEFAULT);

       canal.setDescription(("ici votre description"));
       manager.createNotificationChannel(canal);
   }

     manager.notify(1, mnotif.build());

}


                }
            }
        }

    }
}
