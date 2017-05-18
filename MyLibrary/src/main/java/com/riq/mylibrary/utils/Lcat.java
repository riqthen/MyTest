package com.riq.mylibrary.utils;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by riq on 2017/4/26.
 */

public class Lcat {
    private static int PRIORITY = Log.ERROR;    //默认优先级
    private static boolean IS_DEBUG = true;     //默认显示log

    //-----------> String
    public static void print(String s) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + s);
    }

    public static void print(String tag, String s) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + s);
    }

    //-----------> Object
    public static void print(Object o) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + o.toString());
    }

    public static void print(String tag, Object o) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + o.toString());
    }


    //-----------> Integer
    public static void print(int i) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + i);
    }

    public static void print(String tag, int i) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + i);
    }


    //-----------> Double
    public static void print(double d) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + d);
    }

    public static void print(String tag, double d) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + d);
    }


    //-----------> Long
    public static void print(long l) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + l);
    }

    public static void print(String tag, long l) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + l);
    }
    //-----------> Boolean
    public static void print(boolean b) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + b);
    }

    public static void print(String tag, boolean b) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + b);
    }

    //----------->  String[]
    public static void print(String[] strings) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(strings));
    }

    public static void print(String tag, String[] strings) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(strings));
    }

    //----------->  int[]
    public static void print(int[] ints) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(ints));
    }

    public static void print(String tag, int[] ints) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(ints));
    }

    //----------->  double[]
    public static void print(double[] doubles) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(doubles));
    }

    public static void print(String tag, double[] doubles) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(doubles));
    }
    //----------->  byte[]
    public static void print(byte[] bytes) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(bytes));
    }

    public static void print(String tag, byte[] bytes) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(bytes));
    }


    //----------->  Object[]
    public static void print(Object[] objects) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(objects));
    }

    public static void print(String tag, Object[] objects) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(objects));
    }

    //----------->  Long[]
    public static void print(Long[] longs) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(longs));
    }

    public static void print(String tag, Long[] longs) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + Arrays.toString(longs));
    }

    //-----------> List
    public static <T> void print(List<T> list) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(list));
    }

    public static <T> void print(String tag, List<T> list) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(list));
    }

    //-----------> Set
    public static <T> void print(Set<T> set) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(set));
    }

    public static <T> void print(String tag, Set<T> set) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(set));
    }

    //-----------> Map
    public static <K, V> void print(Map<K, V> map) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, "----------->", "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(map));
    }

    public static <K, V> void print(String tag, Map<K, V> map) {
        if (!IS_DEBUG) {
            return;
        }
        String threadName = Thread.currentThread().getName();
        String lineIndicator = getLineIndicator();
        Log.println(PRIORITY, " ---->" + tag, "Thread: " + threadName + "／" + lineIndicator + " " + String.valueOf(map));
    }


    //获取行所在的方法指示
    private static String getLineIndicator() {
        //2代表方法的调用深度：0-getLineIndicator，1-performPrint，2-print
        StackTraceElement element = ((new Exception()).getStackTrace())[2];
        String packageName = element.getClassName().substring(0, element.getClassName().lastIndexOf("."));
        return packageName + "／" +
                element.getMethodName() + "()／(" +
                element.getFileName() + ":" +
                element.getLineNumber() + ") ------>: ";
    }
}
