package com.bitmotif.bendun.timertask;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;

import java.util.*;
import java.io.File;
import java.io.IOException;

import com.bitmotif.bendun.filesystem.FileSystemObservation;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Feb 2, 2009
 * Time: 6:23:40 AM
 */
public class TimeStampDelta_UT {

   private File aFile;
   private File anotherFile;

   @Before
   public void setUp() throws IOException {
      aFile = File.createTempFile("bendunTestFile", ".txt");
      anotherFile = File.createTempFile("bendunTestFile", ".txt");
   }

   @Test
   public void testAddSinglFile_CheckTimeStampChages() throws InterruptedException, IOException {
      FileSystemObservation fileSystemObservation = new FileSystemObservation(aFile);
      List<FileSystemObservation> fileSystemObservations = Arrays.asList(fileSystemObservation);

      Date startDate = Calendar.getInstance().getTime();

      Timer timer = new Timer(true);
      timer.schedule(new TimestampDelta(fileSystemObservations), startDate, 500);

      Thread.sleep(1000);

      long expectedLastModifiedTime = buildTimePreciseToTheSecond();
      aFile.setLastModified(expectedLastModifiedTime);

      Thread.sleep(2000);

      List<Long> modificationTimes = fileSystemObservations.get(0).getModificationTimes();
      Long actualLastModified = modificationTimes.get( modificationTimes.size() - 1 );
      Assert.assertEquals(Long.valueOf(expectedLastModifiedTime), actualLastModified);
   }

   @Test
   public void testTwoFiles_CheckTimeStampChages() throws InterruptedException, IOException {
      FileSystemObservation aFileSystemObservation = new FileSystemObservation(aFile);
      List<FileSystemObservation> fileSystemObservations = new ArrayList<FileSystemObservation>();
      fileSystemObservations.add(aFileSystemObservation);

      Timer timer = new Timer(true);
      timer.schedule(new TimestampDelta(fileSystemObservations), Calendar.getInstance().getTime(), 500);

      Thread.sleep(1000);

      long aFileExpectedLastModifiedTimeOne = buildTimePreciseToTheSecond();
      aFile.setLastModified(aFileExpectedLastModifiedTimeOne);

      FileSystemObservation anotherFileSystemObservation = new FileSystemObservation(anotherFile);
      fileSystemObservations.add(anotherFileSystemObservation);

      Thread.sleep(2000);

      long aFileExpectedLastModifiedTimeTwo = buildTimePreciseToTheSecond();
      aFile.setLastModified(aFileExpectedLastModifiedTimeTwo);

      long anotherFileExpectedLastModifiedTimeOne = buildTimePreciseToTheSecond();
      anotherFile.setLastModified(anotherFileExpectedLastModifiedTimeOne);

      Thread.sleep(3000);

      List<Long> aFileModificationTimes = aFileSystemObservation.getModificationTimes();
      Long aFileActualLastModified = aFileModificationTimes.get( aFileModificationTimes.size() -1 );
      Assert.assertEquals(Long.valueOf(aFileExpectedLastModifiedTimeTwo), aFileActualLastModified);

      List<Long> anotherFileModificationTimes = anotherFileSystemObservation.getModificationTimes();
      Long anotherFileActualLastModified = anotherFileModificationTimes.get( anotherFileModificationTimes.size() - 1);
      Assert.assertEquals(Long.valueOf(anotherFileExpectedLastModifiedTimeOne), anotherFileActualLastModified);
   }

   private long buildTimePreciseToTheSecond() {
      long timeInMillis = Calendar.getInstance().getTimeInMillis();
      return timeInMillis - (timeInMillis % 1000);
   }
}
