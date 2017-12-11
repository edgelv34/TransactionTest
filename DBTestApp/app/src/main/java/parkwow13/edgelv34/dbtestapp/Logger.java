package parkwow13.edgelv34.dbtestapp;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by com on 2017-03-29.
 */

public class Logger {

    private static final String LOG_TAG = "LottoMonster";
    private static final String FORMAT = "[%s]: %s";

    public static boolean isWrite = false;


    private static boolean isDebuggable() {
        return false;
    }

    /**
     * {@link Log#v(String, String)}
     * @param msg Verbose Log message
     */
    public static void v(String msg) {
        if (isDebuggable()) return;
        String logMsg = String.format(FORMAT, getCallerInfo(), msg);
        Log.v(LOG_TAG, logMsg);
        appendLog(logMsg);
    }

    /**
     * {@link Log#d(String, String)}
     * @param msg Debug Log message
     */
    public static void d(String msg) {
        if (isDebuggable()) return;
        String logMsg = String.format(FORMAT, getCallerInfo(), msg);
        Log.d(LOG_TAG, logMsg);
        appendLog(logMsg);
    }

    /**
     * {@link Log#i(String, String)}
     * @param msg Info Log message
     */
    public static void i(String msg) {
        if (isDebuggable()) return;
        String logMsg = String.format(FORMAT, getCallerInfo(), msg);
        Log.i(LOG_TAG, logMsg);
        appendLog(logMsg);
    }

    /**
     * {@link Log#w(String, String)}
     * @param msg Warning Log message
     */
    public static void w(String msg) {
        if (isDebuggable()) return;
        String logMsg = String.format(FORMAT, getCallerInfo(), msg);
        Log.w(LOG_TAG, logMsg);
        appendLog(logMsg);
    }

    /**
     * {@link Log#e(String, String)}
     * @param msg Error Log message
     */
    public static void e(String msg) {
        if (isDebuggable()) return;
        String logMsg = String.format(FORMAT, getCallerInfo(), msg);
        Log.e(LOG_TAG, logMsg);
        appendLog(logMsg);
    }

    /**
     * @return Logger 클래스를 호출한 Class, Method, Line Number에 대한 정보를 Return
     */
    private static String getCallerInfo() {
        StackTraceElement[] elements = new Exception().getStackTrace();

//        String sumClassString = "";
//        String sumMethodString = "";
//        String sumLineNumString = "";
//
//        for (int i = 0 ; i < elements.length ; i++) {
//            sumClassString = sumClassString + "[ " + i + " ]  : " + elements[i].getClassName() + " //  ";
//            sumMethodString = sumMethodString + "[ " + i + " ]  : " + elements[i].getMethodName() + " //  ";
//            sumLineNumString = sumLineNumString + "[ " + i + " ]  : " + elements[i].getLineNumber() + " //  ";
//        }
//
//        return sumClassString + " /n  " + sumMethodString + " /n  " + sumLineNumString;

        /**
         * elements[2]를 하는 이유는 StacktraceElement[0] 은 첫번째로 StacktraceElement 가 호출된 부분 즉 getCallerInfo() 이며,
         * StacktraceElement[1] 은 두번째로 StacktraceElement 가 호출된 부분 즉 v, d, i, w, e 메소드이다.
         * 결국은 로그를 호출하는 부분은 그다음 StacktraceElement 가 호출되는 부분이므로 배열로는 0->1->2 부분이 되어진다.
         * 테스트를 위해 위에 주석된 부분을 해제하고 아래 실제코드를 주석처리하여 테스트를 해보면 바로 알 수가 있다.
         */
        if (elements.length < 2) return "";

        String className = elements[2].getClassName();
        String methodName = elements[2].getMethodName();
        return " Class : " + className.substring(className.lastIndexOf(".") + 1, className.length()) + " , Method : " + methodName + " , LineNum : " + elements[2].getLineNumber();
    }


    private static void appendLog(String text) {
        if (!isWrite) {
            return;
        }

        File logFile = new File("sdcard/log.file");
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // 적절한 예외처리를 해주면됩니다.
                e.printStackTrace();
            }
        }
        try
        {
            //퍼포먼스를 위해 BufferedWriter를 썼고 FileWriter의 true는 파일을 이어쓸 수 있게 하기 위해서 해줬습니다.
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // 적절한 예외처리를 해주면됩니다.
            e.printStackTrace();
        }
    }


}
