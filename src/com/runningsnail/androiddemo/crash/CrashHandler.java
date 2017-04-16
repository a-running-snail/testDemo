package com.runningsnail.androiddemo.crash;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.runningsnail.androiddemo.tools.NoteLog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

@SuppressLint("NewApi")
public class CrashHandler implements UncaughtExceptionHandler{

	 /** Debug Log Tag */  
   public static final String TAG = "CrashHandler";  
   /** �Ƿ�����־���, ��Debug״̬�¿���, ��Release״̬�¹ر��������������� */  
   public static final boolean DEBUG = true;  
   /** CrashHandlerʵ�� */  
   private static CrashHandler INSTANCE;  
   /** �����Context���� */  
   private Context mContext;  
   /** ϵͳĬ�ϵ�UncaughtException������ */  
   private Thread.UncaughtExceptionHandler mDefaultHandler;  

   /** ���󱨸��ļ�����չ�� */  
   private static final String CRASH_REPORTER_EXTENSION = ".txt";  
   private static String path = Environment.getExternalStorageDirectory() + File.separator+ "snail"+File.separator+"CrashLog"+File.separator;
     
   /** ��ֻ֤��һ��CrashHandlerʵ�� */  
   private CrashHandler() {  
   }  
 
   /** ��ȡCrashHandlerʵ�� ,����ģʽ */  
   public static CrashHandler getInstance() {  
       if (INSTANCE == null)  
           INSTANCE = new CrashHandler();  
       return INSTANCE;  
   }  
     
   /** 
    * ��ʼ��,ע��Context����, ��ȡϵͳĬ�ϵ�UncaughtException������, ���ø�CrashHandlerΪ�����Ĭ�ϴ����� 
    *  
    * @param ctx 
    */  
   public void init(Context ctx) {  
       mContext = ctx;  
       mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();  
       Thread.setDefaultUncaughtExceptionHandler(this);
   }  
     

	/** 
    * ��UncaughtException����ʱ��ת��ú��������� 
    */  
   @Override  
   public void uncaughtException(Thread thread, Throwable ex) {  
       if (!handleException(ex) && mDefaultHandler != null) {  
           // ����û�û�д�������ϵͳĬ�ϵ��쳣������������  
           mDefaultHandler.uncaughtException(thread, ex);  
       } else {  
           // Sleepһ����������  
           // �����߳�ֹͣһ����Ϊ����ʾToast��Ϣ���û���Ȼ��Kill����  
           try {  
               Thread.sleep(3000);  
           } catch (InterruptedException e) {  
               Log.e(TAG, "Error:", e);  
           }
           //ע�⣺������Ҫɱ���̣�����home���˵����棬��ͼ�꣬�ᱨ�쳣��Java.lang.IllegalThreadStateException:thread already started
           android.os.Process.killProcess(android.os.Process.myPid());  
           System.exit(10); 
       }  
   }  
 
   /** 
    * �Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����. �����߿��Ը����Լ���������Զ����쳣�����߼� 
    *  
    * @param ex 
    * @return true:��������˸��쳣��Ϣ;���򷵻�false 
    */  
   private boolean handleException(Throwable ex) {  
       if (ex == null) {  
           return true;  
       }  
       final String msg = ex.getLocalizedMessage();  
       // ʹ��Toast����ʾ�쳣��Ϣ  
       new Thread() {  
           @Override  
           public void run() {  
               // Toast ��ʾ��Ҫ������һ���̵߳���Ϣ������  
               Looper.prepare();  
//               Toast.makeText(mContext, "���������:" + msg, Toast.LENGTH_LONG).show();  
               Looper.loop();  
           }  
       }.start();  
       // �ռ��豸��Ϣ  
//       collectCrashDeviceInfo(mContext);  
       // ������󱨸��ļ�  
//       String crashFileName = saveCrashInfoToFile(ex); 
       extractLogToFile(ex);
       // ���ʹ��󱨸浽������  
//       sendCrashReportsToServer(mContext);  
       return true;  
   }
   
	private String extractLogToFile(Throwable ex) {
		PackageManager manager = mContext.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(mContext.getPackageName(), 0);
		} catch (NameNotFoundException e2) {
		}
		String model = Build.MODEL;
		if (!model.startsWith(Build.MANUFACTURER))
			model = Build.MANUFACTURER + " " + model;

		// Make file name - file must be saved to external storage or it wont be
		// readable by
		// the email app.
		if (!hasSDCard()) {
			return null;
		}
		
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}else {
			//�жϸ�Ŀ¼�µ��ļ��Ƿ���ڲ��������Ƿ����3
			if (fileDir.isDirectory()) {
				File[] fs = fileDir.listFiles();
	    		if (fs.length==5) {
	    			//���޸�ʱ������
	    			Arrays.sort(fs, new Comparator<File>() {
	    				public int compare(File f1, File f2) {
	    					long diff = f1.lastModified() - f2.lastModified();
	    					if (diff > 0)
	    						return 1;
	    					else if (diff == 0)
	    						return 0;
	    					else
	    						return -1;
	    				}

	    				public boolean equals(Object obj) {
	    					return true;
	    				}
	    			});
	    			
	    			for (int i = 0; i <fs.length; i++) {
	    				NoteLog.i("crashhandler",fs[i].getName());
	    			}
	    			File oldestFile = fs[0];
	    			oldestFile.delete();
				}
			}
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(new Date(System.currentTimeMillis()));
		String fullName = path + "/crash-" + dateStr
				+ CRASH_REPORTER_EXTENSION;
		
		// Extract to file.
		InputStreamReader reader = null;
		FileWriter writer = null;
		try {
			String cmd = (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) ? "logcat -d -v time MyApp:v dalvikvm:v System.err:v *:s"
					: "logcat -d -v time";

			// get input stream
			Process process = Runtime.getRuntime().exec(cmd);
			reader = new InputStreamReader(process.getInputStream());

			// write output stream
			writer = new FileWriter(fullName);
			writer.write("Android version: " + Build.VERSION.SDK_INT + "\n");
			writer.write("Device: " + model + "\n");
			writer.write("App version: " + (info == null ? "(null)" : info.versionCode) + "\n");
			writer.write("Cause by:"+ex.getLocalizedMessage());
			char[] buffer = new char[10000];
			do {
				int n = reader.read(buffer, 0, buffer.length);
				if (n == -1)
					break;
				writer.write(buffer, 0, n);
			} while (true);

			reader.close();
			writer.close();
		} catch (IOException e) {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e1) {
				}
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e1) {
				}

			return null;
		}

		return fullName;
	}

	private boolean hasSDCard(){
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	
}