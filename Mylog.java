
/*
usage:
static final String TAG = "myapp";
Mylog.d(TAG, "");
*/

public final class Mylog {

	public static final int DEBUG = 1;
	public static final int INFO = 2;
	public static final int ERROR = 3;

	public static  int level = ERROR;


    public static void d(String tag, String msg) 
    {
        if(level <= DEBUG)
        {
            StackTraceElement stackTraces[] = (new Throwable()).getStackTrace();

            System.out.println("<"+tag+" D > "
            +stackTraces[1].getFileName()
            +"["+ stackTraces[1].getMethodName()+"]"
            +"("+ stackTraces[1].getLineNumber()+") "+
            msg);
        }
    }

    public static void i(String tag, String msg) 
    {
        if(level <= INFO)
        {
            StackTraceElement stackTraces[] = (new Throwable()).getStackTrace();

            System.out.println("<"+tag+" I > "
            +stackTraces[1].getFileName()
            +"["+ stackTraces[1].getMethodName()+"]"
            +"("+ stackTraces[1].getLineNumber()+") "+
            msg);
        }
    }
    
    public static void e(String tag, String msg) 
    {
        if(level <= ERROR)
        {
            StackTraceElement stackTraces[] = (new Throwable()).getStackTrace();

            System.out.println("<"+tag+" E > "
            +stackTraces[1].getFileName()
            +"["+ stackTraces[1].getMethodName()+"]"
            +"("+ stackTraces[1].getLineNumber()+") "+
            msg);
        }
    }
    
}
