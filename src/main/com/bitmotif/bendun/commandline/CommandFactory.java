package com.bitmotif.bendun.commandline;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 26, 2010
 * Time: 5:15:57 AM
 */
public class CommandFactory {

   public Command buildCommand(String input) {
      if(input.startsWith("add ")) {
         return new AddCommand( input.split(" ")[1] );
      }
      else if(input.equals("list")) {
         return new ListCommand();
      }
      else {
         return new NullCommand();
      }
   }
}
