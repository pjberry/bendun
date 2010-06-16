package com.bitmotif.bendun.commandline;

import com.bitmotif.bendun.filesystem.FileSystemObservation;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 28, 2010
 * Time: 5:56:34 AM
 */
public class NullCommand implements Command {
   
   public String execute(List<FileSystemObservation> fileSystemObservations) {
      return Constants.LINE_SEPARATOR;
   }
}
