package com.bitmotif.bendun.commandline;

import com.bitmotif.bendun.filesystem.FileSystemObservation;

import java.io.IOException;
import java.io.File;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 26, 2010
 * Time: 5:19:36 AM
 */
public class AddCommand implements Command {
   
   private String fileName;

   public AddCommand(String fileName) {
      this.fileName = fileName;
   }

   public String execute(List<FileSystemObservation> fileSystemObservations) {
      File file = new File(fileName);
      fileSystemObservations.add( new FileSystemObservation(file) );
      return "added '" + fileName + "'" + Constants.LINE_SEPARATOR;
   }
}
