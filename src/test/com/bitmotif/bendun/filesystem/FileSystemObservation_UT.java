package com.bitmotif.bendun.filesystem;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 13, 2010
 * Time: 5:46:23 AM
 */
public class FileSystemObservation_UT {

   private File existingFile;

   @Before
   public void setUp() throws IOException {
      existingFile = File.createTempFile("bendunTestFile", ".txt");
      existingFile.deleteOnExit();
   }

   @Test
   public void testNoModificationTimesBeforeObserving() throws Exception {
      FileSystemObservation fileSystemObservation = new FileSystemObservation(existingFile);

      Assert.assertEquals(0, fileSystemObservation.getModificationTimes().size());

      fileSystemObservation.observe();

      Assert.assertEquals(1, fileSystemObservation.getModificationTimes().size());
   }

   @Test
   public void testExists_ModifiedBetweenObservations() throws Exception {
      long firstModificationTime = existingFile.lastModified();
      long secondModificationTime = firstModificationTime + 3000L;

      FileSystemObservation fileSystemObservation = new FileSystemObservation(existingFile);
      fileSystemObservation.observe();

      existingFile.setLastModified(secondModificationTime);

      fileSystemObservation.observe();

      List<Long> modificationTimes = fileSystemObservation.getModificationTimes();
      Iterator<Long> iterator = fileSystemObservation.getModificationTimes().iterator();

      Assert.assertEquals(2, modificationTimes.size());
      Assert.assertEquals(firstModificationTime, iterator.next().longValue());
      Assert.assertEquals(secondModificationTime, iterator.next().longValue());
   }

   @Test
   public void testDoesNotExist_IsCreatedBetweenObservations() throws Exception {
      String testFilePath = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "bendunTestFile.txt";
      File aFile = new File(testFilePath);
      aFile.deleteOnExit();

      FileSystemObservation fileSystemObservation = new FileSystemObservation(aFile);
      fileSystemObservation.observe();

      aFile.createNewFile();

      fileSystemObservation.observe();

      List<Long> modificationTimes = fileSystemObservation.getModificationTimes();
      Iterator<Long> iterator = fileSystemObservation.getModificationTimes().iterator();

      Assert.assertEquals(2, modificationTimes.size());
      Assert.assertEquals(0, iterator.next().longValue());
      Assert.assertEquals(aFile.lastModified(), iterator.next().longValue());
   }

   @Test
   public void testExists_IsDeletedBetweenObservations() throws Exception {
      long firstModificationTime = existingFile.lastModified();

      FileSystemObservation fileSystemObservation = new FileSystemObservation(existingFile);
      fileSystemObservation.observe();

      existingFile.delete();

      fileSystemObservation.observe();

      List<Long> modificationTimes = fileSystemObservation.getModificationTimes();
      Iterator<Long> iterator = fileSystemObservation.getModificationTimes().iterator();

      Assert.assertEquals(2, modificationTimes.size());
      Assert.assertEquals(firstModificationTime, iterator.next().longValue());
      Assert.assertEquals(0, iterator.next().longValue());
   }


   // for file
   // exits, not modified
   // exits, modified
   // exits, doesn't exit
   // doesnt exits, exist

   // directory really just recurse of file...

   // think about what moving a file/directory means...
}
