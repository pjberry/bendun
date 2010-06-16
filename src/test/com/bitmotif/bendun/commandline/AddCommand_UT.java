package com.bitmotif.bendun.commandline;

import org.junit.Test;
import org.junit.Assert;
import com.bitmotif.bendun.filesystem.FileSystemObservation;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 27, 2010
 * Time: 6:23:54 AM
 */
public class AddCommand_UT {

   @Test
   public void testMessage() throws Exception {
      AddCommand addCommand = new AddCommand("aFileName");

      String message = addCommand.execute(new ArrayList<FileSystemObservation>());

      Assert.assertEquals("added 'aFileName'" + Constants.LINE_SEPARATOR, message);
   }
   
   @Test
   public void testObservationsUpdated() throws Exception {
      AddCommand addCommand = new AddCommand("aFileName");
      ArrayList<FileSystemObservation> fileSystemObservations = new ArrayList<FileSystemObservation>();

      addCommand.execute(fileSystemObservations);

      Assert.assertEquals(1, fileSystemObservations.size());
   }
}
