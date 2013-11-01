/*	Copyright 2013 havocx42
	
	This file is part of PRStats.

    PRStats is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    PRStats is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with PRStats.  If not, see <http://www.gnu.org/licenses/>.
*/
package havocx42;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class Program {

	/**
	 * @param args
	 */

	private final static Logger	LOGGER	= Logger.getLogger(Program.class.getName());
	public final static String	VERSION	= "v1.0";

	private static void initRootLogger() throws SecurityException, IOException {

		FileHandler fileHandler;
		fileHandler = new FileHandler("PRWeaponStats.%u.%g.txt", 1024 * 1024 * 8, 3, false);
		fileHandler.setLevel(Level.FINEST);
		fileHandler.setFormatter(new SimpleFormatter());
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		for (Handler handler : handlers) {
			handler.setLevel(Level.INFO);
		}
		rootLogger.setLevel(Level.FINEST);
		rootLogger.addHandler(fileHandler);
	}

	public static void main(String[] args) throws ParseException {
		try {
			initRootLogger();
		} catch (SecurityException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		// get location

		Options options = new Options();
		options.addOption("nogui", false, "run as a command line tool, must also supply -target and -source arguments");
		options.addOption("source", true, "source directory where the PR weapons folder has been extracted");
		options.addOption("target", true, "target file to write to");
		options.addOption("version", false, "print the version information and exit");
		options.addOption("help", false, "print this message");
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(options, args);

		if (cmd.hasOption("version")) {
			System.out.println("PRStats " + VERSION);
			System.out.println("Written by havocx42");
			return;
		}

		if ((cmd.hasOption("nogui") && (!cmd.hasOption("source") || !cmd.hasOption("target"))) || cmd.hasOption("help")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("PRStats", options);
			return;
		}

		final String target;
		final String source;

		source = cmd.getOptionValue("source");
		target = cmd.getOptionValue("target");
		LOGGER.info("Source Argument: "+source);
		LOGGER.info("Target Argument: "+target);

		if (!cmd.hasOption("nogui")) {
			EventQueue.invokeLater(new Runnable() {
				@SuppressWarnings("unused")
				public void run() {
					try {
						Gui window = new Gui(source, target);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return;
		}

		File targetFile = new File(target);
		File sourceFile = new File(source);
		Controller controller = new Controller();
		controller.run(sourceFile, targetFile);

	}

}
