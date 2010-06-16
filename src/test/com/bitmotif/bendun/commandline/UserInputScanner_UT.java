package com.bitmotif.bendun.commandline;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 3, 2010
 * Time: 7:21:09 PM
 */
public class UserInputScanner_UT extends TestCase {

   @Test
   public void testStop() throws Exception {
      String inputString = buildString(
         "This is input",
         "This is another",
         "Yet another",
         "stop",
         "After the stop" );

      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputString.getBytes());
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

      UserInputScanner userInputScanner = new UserInputScanner(byteArrayInputStream, byteArrayOutputStream);
      userInputScanner.run();

      Assert.assertEquals(Constants.LINE_SEPARATOR + Constants.LINE_SEPARATOR + Constants.LINE_SEPARATOR + Constants.LINE_SEPARATOR + "Finished", byteArrayOutputStream.toString());
   }

   private String buildString(String... strings) {
      StringBuilder stringBuilder = new StringBuilder();
      for (String string : strings) {
         stringBuilder.append(string);
         stringBuilder.append(System.getProperty("line.separator"));
      }
      return stringBuilder.toString();
   }
}
