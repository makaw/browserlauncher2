/************************************************
    Copyright 2005 Jeff Chapman

    This file is part of BrowserLauncher2.

    BrowserLauncher2 is free software; you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    BrowserLauncher2 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with BrowserLauncher2; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 ************************************************/
// $Id: BrowserLauncherRunner.java,v 1.1 2005/02/03 00:59:12 jchapman0 Exp $
package edu.stanford.ejalbert;

import edu.stanford.ejalbert.exceptionhandler.BrowserLauncherErrorHandler;
import edu.stanford.ejalbert.exceptionhandler.BrowserLauncherDefaultErrorHandler;

/**
 * This is a convenience class to facilitate executing the browser launch in
 * a separate thread. Applications will need to create a thread passing an
 * instance of this class, then call start() on the thread created.
 *
 * @author Jeff Chapman
 */
public class BrowserLauncherRunner
        implements Runnable {
    private String url; // in ctor
    private BrowserLauncherErrorHandler errorHandler; // in ctor
    private BrowserLauncher launcher; // in ctor

    /**
     * Takes the items necessary for launching a browser and handling any
     * exceptions.
     *
     * If the errorHandler is null, an instance of the
     * BrowserLauncherDefaultErrorHandler will be used.
     *
     * @param launcher BrowserLauncher
     * @param url String
     * @param errorHandler BrowserLauncherErrorHandler
     */
    public BrowserLauncherRunner(BrowserLauncher launcher,
                                 String url,
                                 BrowserLauncherErrorHandler errorHandler) {
        this.launcher = launcher;
        this.url = url;
        if(errorHandler == null) {
            errorHandler = new BrowserLauncherDefaultErrorHandler();
        }
        this.errorHandler = errorHandler;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used to
     * create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     *
     * This method will make the call to open the browser and display the
     * url. If an exception occurs, it will be passed to the instance of
     * BrowserLauncherErrorHandler that has been passed into the constructor.
     * If no error handler is available, an instance of the default error
     * handler will be used.
     */
    public void run() {
        try {
            launcher.openURLinBrowser(url);
        }
        catch (Exception ex) {
            errorHandler.handleException(ex);
        }
    }
}