package com.bitmotif.bendun.commandline;

import com.bitmotif.bendun.filesystem.FileSystemObservation;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 26, 2010
 * Time: 5:31:30 AM
 */
public class ListCommand implements Command {

   public String execute(List<FileSystemObservation> fileSystemObservations) {
      StringBuilder builder = new StringBuilder();
      for (FileSystemObservation fileSystemObservation : fileSystemObservations) {
         builder.append(fileSystemObservation.getFileName());
         builder.append(Constants.LINE_SEPARATOR);
         appendModificationTimes(fileSystemObservation, builder);
         builder.append(Constants.LINE_SEPARATOR);
      }
      return builder.toString();
   }

   private void appendModificationTimes(FileSystemObservation fileSystemObservation, StringBuilder builder) {
      List<Long> modificationTimes = fileSystemObservation.getModificationTimes();
      for (Long modificationTime : modificationTimes) {
         builder.append("\t");
         builder.append(modificationTime);
         builder.append(Constants.LINE_SEPARATOR);
      }
   }
}
