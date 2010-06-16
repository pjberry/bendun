package com.bitmotif.bendun.main;

import com.bitmotif.bendun.commandline.UserInputScanner;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 22, 2010
 * Time: 5:16:48 AM
 */
public class BendunMain {
   private UserInputScanner userInputScanner;

   public BendunMain(UserInputScanner userInputScanner) {
      this.userInputScanner = userInputScanner;
   }

   public void run() throws IOException {
      userInputScanner.run();
   }

   public static void main(String... strings) {
      try {
         new BendunMain( new UserInputScanner(System.in, System.out) ).run();
      } catch (IOException e) {
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
   }
}
