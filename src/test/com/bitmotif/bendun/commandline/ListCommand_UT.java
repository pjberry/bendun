package com.bitmotif.bendun.commandline;

import org.junit.Test;
import org.junit.Assert;
import com.bitmotif.bendun.filesystem.FileSystemObservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 27, 2010
 * Time: 6:31:33 AM
 */
public class ListCommand_UT {

   @Test
   public void testMessage_EmptyObservations() throws Exception {
      ListCommand listCommand = new  ListCommand();

      String message = listCommand.execute(new ArrayList<FileSystemObservation>());

      Assert.assertEquals("", message);
   }

   @Test
   public void testMessage_SingleObservations() throws Exception {
      ListCommand listCommand = new  ListCommand();

      File aFile = buildFile();
      String message = listCommand.execute( buildObservations(aFile) );

      String expectedString =
         aFile.getAbsolutePath() +
            Constants.LINE_SEPARATOR +
            "\t0" +
            Constants.LINE_SEPARATOR +
            Constants.LINE_SEPARATOR;

      Assert.assertEquals(expectedString, message);
   }

   private List<FileSystemObservation> buildObservations(File aFile) {
      FileSystemObservation fileSystemObservation = new FileSystemObservation(aFile);
      fileSystemObservation.observe();

      return Arrays.asList(fileSystemObservation);
   }

   private File buildFile() {
      File aFile = new File("aFile");
      aFile.setLastModified(0);
      return aFile;
   }
}
