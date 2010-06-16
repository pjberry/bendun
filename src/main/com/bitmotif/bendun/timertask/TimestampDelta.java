package com.bitmotif.bendun.timertask;

import com.bitmotif.bendun.filesystem.FileSystemObservation;

import java.util.TimerTask;
import java.util.List;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 7, 2010
 * Time: 5:36:04 AM
 */
public class TimestampDelta extends TimerTask {

   private List<FileSystemObservation> fileSystemObservations;

   public TimestampDelta(List<FileSystemObservation> fileSystemObservations) {
      this.fileSystemObservations = fileSystemObservations;
      observeAll();
   }

   public void run() {
      observeAll();
   }

   private void observeAll() {
       for (FileSystemObservation fileSystemObservation : fileSystemObservations) {
         fileSystemObservation.observe();
      }
   }

}
