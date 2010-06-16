package com.bitmotif.bendun.filesystem;

import java.io.File;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 14, 2010
 * Time: 5:55:14 AM
 */
public class FileSystemObservation {

   private List<Long> modificationTimes;
   private File file;

   public FileSystemObservation(File file) {
      this.file = file;
      modificationTimes = new ArrayList<Long>();
   }

   public List<Long> getModificationTimes() {
     return Collections.unmodifiableList(modificationTimes);
   }

   public void observe() {
      if ( !modificationTimes.contains( file.lastModified() )) {
         modificationTimes.add( file.lastModified() );
      }
   }

   public String getFileName() {
      return file.getAbsolutePath();
   }
}
