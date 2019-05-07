package com.cui.demo.utils;

import com.cui.demo.exc.SystemException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author tao
 */
public class MD5Util {

  /**
   * 采用 MD5 加密字符串
   * @param str 原始字符串
   * @return 32 位 16 进制 字符串
   * @throws SystemException
   */
  public static String md5Gen(String str) throws SystemException {
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      byte[] bytes = str.getBytes();
      md5.update(bytes);
      byte[] resultArray = md5.digest();
      return MD5Util.byteArrayToHex(resultArray);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new SystemException("md5 加密失败");
    }

  }

  /**
   * 采用位运算将 byte 数组转换为 32 位 16 进制字符串
   * @param byteArray 加密后的字节数组
   * @return 加密字符串
   */
  private static String byteArrayToHex(byte[] byteArray) {
    char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
    char[] resultCharArray =new char[byteArray.length * 2];
    int index = 0;
    for (byte b : byteArray) {
      resultCharArray[index++] = hexDigits[b>>>4 & 0xf];
      resultCharArray[index++] = hexDigits[b & 0xf];
    }
    return new String(resultCharArray);
  }

}
