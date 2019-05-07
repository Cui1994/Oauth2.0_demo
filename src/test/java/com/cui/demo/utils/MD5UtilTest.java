package com.cui.demo.utils;

import static org.junit.Assert.*;

import com.cui.demo.DemoApplication;
import com.cui.demo.exc.SystemException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MD5UtilTest {

  @Test
  public void testMD5Gen() throws SystemException {
    String str1 = "123abc";
    String str2 = "123abc";
    String str3 = "123ab";

    String target1 = MD5Util.md5Gen(str1);
    String target2 = MD5Util.md5Gen(str2);
    String target3 = MD5Util.md5Gen(str3);

    assertEquals(target1.length(), 32);
    assertEquals(target1, target2);
    assertNotEquals(target1, target3);

  }

}