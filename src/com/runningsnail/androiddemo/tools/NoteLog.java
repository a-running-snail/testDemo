package com.runningsnail.androiddemo.tools;

import android.util.Log;

public class NoteLog {

	private static final boolean DEBUG = true;
    public static int d( String msg) {
        if (DEBUG) {
            return Log.d("notepadLog", msg+getFunctionName());
        } else {
            return 0;
        }
    }
    public static int i(String tag, String msg) {
        if (DEBUG) {
            return Log.d(tag, msg+getFunctionName());
        } else {
            return 0;
        }
    }
    
    public static int e(String tag, String msg) {
        if (DEBUG) {
            return Log.e(tag, msg+getFunctionName());
        } else {
            return 0;
        }
    }
    /**
     * ������־��λ
     * add by mafei 
     * @param tag ��ǩ
     * @return
     */
    private static String getFunctionName()  
    {  
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();  
        if(sts == null)  
        {  
            return null;  
        }  
        for(StackTraceElement st : sts)  
        {  
            if(st.isNativeMethod())  
            {  
                continue;  
            }  
            if(st.getClassName().equals(Thread.class.getName()))  
            {  
                continue;  
            }  
            if(st.getClassName().equals(NoteLog.class.getName()))  
            {  
                continue;  
            }  
            return "[" + Thread.currentThread().getName() + ": "  
                    + st.getFileName() + ":" + st.getLineNumber() + " "  
                    + st.getMethodName() + " ]";  
        }  
        return null;  
    }  

}
