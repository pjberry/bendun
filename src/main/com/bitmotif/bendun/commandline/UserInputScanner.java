package com.bitmotif.bendun.commandline;

import com.bitmotif.bendun.filesystem.FileSystemObservation;
import com.bitmotif.bendun.timertask.TimestampDelta;

import java.util.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 3, 2010
 * Time: 7:26:37 PM
 */
public class UserInputScanner {

   private Scanner scanner;
   private OutputStream outputStream;
   private List<FileSystemObservation> fileSystemObservations;

   public UserInputScanner(InputStream inputStream, OutputStream outputStream) {
      this.scanner = new Scanner(inputStream);
      this.outputStream = outputStream;
      this.fileSystemObservations = new ArrayList<FileSystemObservation>();
      Timer timer = new Timer(true);
      timer.schedule(new TimestampDelta(fileSystemObservations), Calendar.getInstance().getTime(), 1000);
   }

   public void run() throws IOException {
      CommandFactory commandFactory = new CommandFactory();
      String input = "";
      do {
         input = scanner.nextLine();
         Command command = commandFactory.buildCommand(input);
         String result = command.execute(fileSystemObservations);
         outputStream.write(result.getBytes());
      }
      while( !"stop".equals(input) );

      outputStream.write("Finished".getBytes());
   }

}
