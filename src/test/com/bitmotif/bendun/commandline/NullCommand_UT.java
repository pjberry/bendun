package com.bitmotif.bendun.commandline;

import org.junit.Test;
import org.junit.Assert;
import com.bitmotif.bendun.filesystem.FileSystemObservation;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 28, 2010
 * Time: 5:58:11 AM
 */
public class NullCommand_UT {

   @Test
   public void testExecute() throws Exception {
      NullCommand nullCommand = new NullCommand();

      String message = nullCommand.execute(new ArrayList<FileSystemObservation>());

      Assert.assertEquals(Constants.LINE_SEPARATOR, message);
   }
   
   @Test
   public void testExecute_InputDoesNotMatter() throws Exception {
      NullCommand nullCommand = new NullCommand();

      String message = nullCommand.execute(null);

      Assert.assertEquals(Constants.LINE_SEPARATOR, message);
   }
}
