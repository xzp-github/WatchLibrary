package com.android.mltcode.watchlib.config;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class Logger {
    private static final String PATH = "/Android/com.android.mltcode/";
    private static final String CONFIG_FILE_NAME = ".cofig.txt";

    private static boolean isDebug = false;
    private static final String LOG = "log";

    public static void init(Context context) {
        boolean isWriteLog = false;
        Config config = readConfig(context);
		if(config != null){
			isDebug = config.isDebug;
			isWriteLog = config.isWriteLog;
		}
        ConfigUtil.init(context, isWriteLog);
    }

    public static void d(String tag, String content) {
        if (isDebug) {
            Log.d(tag, content);
        }
        ConfigUtil.LogWirte(LOG, "d  +" + tag + " :" + content);
    }

    public static void i(String tag, String content) {
        if (isDebug) {
            Log.d(tag, content);
        }
        ConfigUtil.LogWirte(LOG, "i  +" + tag + " :" + content);
    }

    public static void e(String tag, String content) {
        if (isDebug) {
            Log.d(tag, content);
        }
        ConfigUtil.LogWirte(LOG, "e  +" + tag + " :" + content);
    }

    public static void w(String tag, String content) {
        if (isDebug) {
            Log.d(tag, content);
        }
        ConfigUtil.LogWirte(LOG, "w  +" + tag + " :" + content);
    }

    private static Config readConfig(Context context) {
        PackageManager pm = context.getPackageManager();
        if (PackageManager.PERMISSION_GRANTED != pm.checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                context.getPackageName())) {
            return null;
        }
        File file = createFile();
        String str = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String readline = "";
            StringBuffer sb = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                sb.append(readline);
            }
            br.close();
            str = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        Config config;
        if (TextUtils.isEmpty(str)) {
            config = getDefaultConfig();
        } else {
            try {
                str = new String(Base64.decode(str, Base64.DEFAULT));
                config = new Config();
                JSONObject jo = new JSONObject(str);
                config.isDebug = jo.optBoolean("isDebug", false);
                config.isWriteLog = jo.optBoolean("isWriteLog", false);
                config.mode = jo.optInt("mode", BusinessConfig.OFFICIAL_MODE);
            } catch (JSONException e) {
                e.printStackTrace();
                config = getDefaultConfig();
            }
        }
        return config;
    }

    private static Config getDefaultConfig() {
        Config config = new Config();
        config.isDebug = false;
        config.isWriteLog = false;
        config.mode = BusinessConfig.OFFICIAL_MODE;
        return config;
    }

    private static File createFile() {
        StringBuffer bf = new StringBuffer();
        bf.append(Environment.getExternalStorageDirectory().getPath());
        bf.append(PATH);
        File fileDir = new File(bf.toString());
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        bf.append(CONFIG_FILE_NAME);
        File file = new File(bf.toString());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }
}
