package com.nada.app.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nada.app.R;
import com.nada.app.activity.SplashActivity;
import com.nada.app.utils.Pref;
import com.nada.app.utils.StringUtils;
import com.nada.app.utils.TagUtils;

import org.json.JSONObject;

public class FirebaseMessanger extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TagUtils.getTag(), "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TagUtils.getTag(), "Message data payload: " + remoteMessage.getData());

//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
////                scheduleJob();
//            } else {
//                // Handle message within 10 seconds
////                handleNow();
//            }
            try {
                String notification = remoteMessage.getData().toString();
                String success = remoteMessage.getData().get("success");
                String result = remoteMessage.getData().get("result");
                String title = remoteMessage.getData().get("title");
                String description = remoteMessage.getData().get("description");
                String type = remoteMessage.getData().get("type");

                Log.d(TagUtils.getTag(), "notification:-" + notification);
                Log.d(TagUtils.getTag(), "success:-" + success);
                Log.d(TagUtils.getTag(), "result:-" + result);
                Log.d(TagUtils.getTag(), "type:-" + type);
                if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
                    checkType(type, result);
                }
            } catch (Exception e) {
                Log.d(TagUtils.getTag(), e.toString());
                try {
                    Log.d(TagUtils.getTag(), "From: " + remoteMessage.getFrom());
                    Log.d(TagUtils.getTag(), "Notification Message Body: " + remoteMessage.getNotification().getBody());
                } catch (Exception e1) {
                    Log.d(TagUtils.getTag(), e1.toString());
                }
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TagUtils.getTag(), "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TagUtils.getTag(), "Refreshed token: " + token);
        Pref.SetStringPref(getApplicationContext(), Pref.FCM_REGISTRATION_TOKEN, token);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(token);
    }

    public void checkType(String type, String result) {
        try {
            Log.d(TagUtils.getTag(), "type:-" + type);
            Log.d(TagUtils.getTag(), "result:-" + result);

            if (Pref.GetBooleanPref(getApplicationContext(), StringUtils.IS_LOGIN, false)) {
                sendPostNotification(type, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendPostNotification(String type, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);

            Log.d(TagUtils.getTag(), "notification message:-" + jsonObject.optString("msg"));
            Log.d(TagUtils.getTag(), "notification type:-" + type);

            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("type", type);
            intent.putExtra("data", data);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            int notificationId = 1;
            String channelId = "channel-01";
            String channelName = "Channel Name";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(
                        channelId, channelName, importance);
                notificationManager.createNotificationChannel(mChannel);
            }

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(type)
                    .setContentText(jsonObject.optString("msg"));

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
            stackBuilder.addNextIntent(intent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            mBuilder.setContentIntent(resultPendingIntent);

            notificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAlertNotification(String type, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);

            Log.d(TagUtils.getTag(), "notification message:-" + jsonObject.optString("msg"));
            Log.d(TagUtils.getTag(), "notification type:-" + type);

            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("type", jsonObject.optString("name") + " " + type);
//            intent.putExtra("data", jsonObject.optString("event_desc"));
            intent.putExtra("data", data);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            int notificationId = 1;
            String channelId = "channel-01";
            String channelName = "Channel Name";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(
                        channelId, channelName, importance);
                notificationManager.createNotificationChannel(mChannel);
            }

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(jsonObject.optJSONObject("data").optString("name") + " " + type)
                    .setContentText(jsonObject.optJSONObject("data").optString("event_desc"));

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
            stackBuilder.addNextIntent(intent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            mBuilder.setContentIntent(resultPendingIntent);

            notificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAlert(Context context) {
//        Intent intent = new Intent(StringUtils.UPDATE_ALERT);
//        //send broadcast
//        context.sendBroadcast(intent);
    }
}
