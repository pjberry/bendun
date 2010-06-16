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
         appendHeader(builder, fileSystemObservation);
         appendModificationTimes(fileSystemObservation, builder);
         appendLineBreak(builder);
      }
      return builder.toString();
   }

   private void appendHeader(StringBuilder builder, FileSystemObservation fileSystemObservation) {
      builder.append(fileSystemObservation.getFileName());
      appendLineBreak(builder);
   }

   private void appendLineBreak(StringBuilder builder) {
      builder.append(Constants.LINE_SEPARATOR);
   }

   private void appendModificationTimes(FileSystemObservation fileSystemObservation, StringBuilder builder) {
      List<Long> modificationTimes = fileSystemObservation.getModificationTimes();
      for (Long modificationTime : modificationTimes) {
         builder.append("\t");
         builder.append(modificationTime);
         appendLineBreak(builder);
      }
   }
}
