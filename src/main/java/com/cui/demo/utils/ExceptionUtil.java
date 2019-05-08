package com.cui.demo.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常栈打印相关
 * @author tao
 */
public class ExceptionUtil {

  public static String getStackTrace(Throwable throwable) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    throwable.printStackTrace(pw);

    return sw.toString();
  }

}
