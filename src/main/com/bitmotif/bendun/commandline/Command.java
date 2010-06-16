package com.bitmotif.bendun.commandline;

import com.bitmotif.bendun.filesystem.FileSystemObservation;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: pjberry
 * Date: Jan 26, 2010
 * Time: 5:18:16 AM
 */
public interface Command {
   String execute(List<FileSystemObservation> fileSystemObservations);
}
