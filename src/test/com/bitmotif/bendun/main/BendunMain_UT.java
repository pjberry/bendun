package com.bitmotif.bendun.main;

import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import com.bitmotif.bendun.commandline.UserInputScanner;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 22, 2010
 * Time: 5:12:28 AM
 */
public class BendunMain_UT {

   private File aFile;

   @Before
   public void setUp() throws IOException {
      aFile = File.createTempFile("bendunTestFile", ".txt");
      aFile.deleteOnExit();
   }

   @Test
   public void testAddAFile_ListFilesWatched() throws Exception {
      String inputString = buildString(
         "add " + aFile.getAbsolutePath(),
         "list",
         "stop");

      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputString.getBytes());
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      UserInputScanner userInputScanner = new UserInputScanner(byteArrayInputStream, byteArrayOutputStream);
      
      BendunMain bendunMain = new BendunMain(userInputScanner);
      bendunMain.run();
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
