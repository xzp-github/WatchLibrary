package com.android.mltcode.watchlib.config;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressLint("SimpleDateFormat")
public class ConfigUtil {
    private static final String PATH = "/Android/";
    private static boolean isWrite = false;

    private static String mRelPath;
    static ExecutorService executor;

    public static void init(Context context, boolean write) {
        isWrite = write;
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        StringBuffer bf = new StringBuffer();
        bf.append(Environment.getExternalStorageDirectory().getPath());
        bf.append(PATH);
        bf.append(context.getPackageName());
        bf.append("/log/");
        mRelPath = bf.toString();
    }

    public static void LogWirte(String fileName, String cont) {
        LogWirte(fileName, cont, "yyyy-MM-dd");
    }

    public static void LogWriteByHour(String fileName, String cont) {
        LogWirte(fileName, cont, "yyyy-MM-dd-HH");
    }

    public static void LogWirte(final String fileName, final String cont, final String dataFormat) {
        if (isWrite) {
            if (executor == null) {
                executor = Executors.newSingleThreadExecutor();
            }
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    FileOutputStream stream = null;
                    try {
                        if (TextUtils.isEmpty(cont)) {
                            return;
                        }

                        SimpleDateFormat sdf0 = new SimpleDateFormat(dataFormat);
                        Date now = new Date();
                        String time0 = sdf0.format(now);

                        File path = new File(mRelPath);
                        File file = new File(mRelPath + fileName + time0 + ".txt");
                        if (!path.exists()) {
                            path.mkdirs();
                        }
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
                        String time = sdf.format(now);
                        StringBuffer bf = new StringBuffer(time);
                        bf.append("  |  ");
                        bf.append(cont);
                        bf.append("\n");

                        stream = new FileOutputStream(file, true);
                        byte[] buf = bf.toString().getBytes();
                        stream.write(buf);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (stream != null) {
                                stream.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
