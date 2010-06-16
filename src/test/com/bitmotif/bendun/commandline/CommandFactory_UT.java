package com.bitmotif.bendun.commandline;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 28, 2010
 * Time: 5:48:59 AM
 */
public class CommandFactory_UT {
   
   @Test
   public void testCreateAddCommand() throws Exception {
      CommandFactory commandFactory = new CommandFactory();

      Command command = commandFactory.buildCommand("add a");

      Assert.assertTrue(command instanceof AddCommand);
   }

   @Test
   public void testCreateListCommand() throws Exception {
      CommandFactory commandFactory = new CommandFactory();

      Command command = commandFactory.buildCommand("list");

      Assert.assertTrue(command instanceof ListCommand);
   }

   @Test
   public void testInputDoesNotMapToACommand() throws Exception {
      CommandFactory commandFactory = new CommandFactory();

      Command command = commandFactory.buildCommand("x y z");

      Assert.assertTrue(command instanceof NullCommand);
   }
}
