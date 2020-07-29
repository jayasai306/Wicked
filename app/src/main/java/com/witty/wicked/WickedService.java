package com.witty.wicked;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.witty.wicked.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.internal.Util;
import tech.gusavila92.websocketclient.WebSocketClient;

public class WickedService extends Service {
    private static WebSocketClient webSocketClient = null;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createWebSocketClient();
        return super.onStartCommand(intent, flags, startId);

    }

    private void createWebSocketClient() {
        URI uri;
        try {
            // Connect to local host
            uri = new URI("ws://192.168.1.22:3000/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen() {
                Log.i("WebSocket", "Session is starting");
               // webSocketClient.send("Hello World!");
            }
            @Override
            public void onTextReceived(String s) {
                Log.i("WebSocket", "Message received");
                //Toast.makeText(getApplicationContext(),"message received",Toast.LENGTH_SHORT);
                try {
                    JSONObject obj = new JSONObject(s);
                    if (obj.has("code")) {
                        String code = obj.getString("code");
                        Utils.setmCode(code);
                        if (Utils.getmMainHandler() != null) {
                            Utils.getmMainHandler().sendEmptyMessage(0);
                        } else {
                            Log.d("jaya","didn't go to joining screen");
                        }
                    } else if(obj.has("Questions")) {
                        Log.d("jaya","Questions received");
                        ArrayList arrayList = new ArrayList();
                        Iterator<String> keys = obj.keys();

                        while(keys.hasNext()) {
                            String key = keys.next();
                            if (!key.equals("Questions")) {
                                arrayList.add(obj.get(key));
                            }
                        }
                        Utils.setQuestionList(arrayList);
                        if (Utils.getJoinHandler()!=null) {
                            Utils.getJoinHandler().sendEmptyMessage(0);
                        }
                    } else if(obj.has("Answers")) {
                        Log.d("jaya","Answers received");
                        ArrayList arrayList = new ArrayList();
                        Iterator<String> keys = obj.keys();

                        while(keys.hasNext()) {
                            String key = keys.next();
                            if (!key.equals("Questions")) {
                                arrayList.add(obj.get(key));
                            }
                        }
                        Utils.setAnswerList(arrayList);
                        if (Utils.getmWickedHandler()!=null) {
                            Utils.getmWickedHandler().sendEmptyMessage(0);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onBinaryReceived(byte[] data) {
            }
            @Override
            public void onPingReceived(byte[] data) {
            }
            @Override
            public void onPongReceived(byte[] data) {
            }
            @Override
            public void onException(Exception e) {
                System.out.println(e.getMessage());
            }
            @Override
            public void onCloseReceived() {
                Log.i("WebSocket", "Closed ");
                System.out.println("onCloseReceived");
            }
        };
        webSocketClient.setConnectTimeout(10000);
        webSocketClient.setReadTimeout(60000);
        webSocketClient.enableAutomaticReconnection(5000);
        webSocketClient.connect();
    }

    public static void sendToServer(String msg) {
        if (webSocketClient != null) {
            webSocketClient.send(msg);
        }
    }
}
