package lol.lolpany;

import lol.lolpany.core.*;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.File;
import java.io.IOException;


//		''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
//		'':llllllllllc;'',cllllcllllllllllllc:'.'';clcllllllllllclllcc;'',cccl:''';clc;'
//		';OWWWWWWWWWWXo''lKWWWWWWWWWWWWWWWWWWk,..,xNWWWWWWWWWWWWWWWWWXo'.cKWWWO;.,xWWXl'
//		';0MMMMWWWMMMNo''lXMMMWNXXXXXXXXNWMMMk,..,dNWWWWWWWWWWWWWWMMMNo''lXMMM0:.,kWMNo'
//		.;0MMNkllkNMMNo''lXMMWOc::::::::cOWMWk,..';ccccccccccccccoKMMNo''lXMMM0:.,kWMNo'
//		';0MMXl''lXMMNo''lXMMWOc:::::;'',kWMWO:,,,,,,,,,,,,,,,''.:0MMNo'.lXMMM0:';kWMNo'
//		':0MMXl''lXMMNo''lXMMMWNNNNNNk;.,kWMMNXKKKKKKKKKKKKKK0o'.:0MMNo''lXMMMNK00NMMNo'
//		':0MMXl''lXMMNo''lXMMMMMMMMMWO;.,kWMMWWWWWMMMMMMMMMMMWd'.:0MMNo''lXMMMMMMMMMMNo'
//		';0MMXl'';lool;'';looooooooooc,.,kWMWklccdXMMW0ddddddo:'.:0MMNo''lXMMMX0O0NMMNo'
//		.;0MMXl''',,,,,,,,,,,,,,,,,,,,,,;kWMNd'..:0MMWx,''''''''':0MMNo''lXMMMO;';OMMNo'
//		';0MMWK0000K00000000K000000KK000KNMMNd'..:0MMMX0000000000KNMMNo'.lXMMWk,.;OMMNo'
//		.;0MMMMWWWWWWWWWWMMMMMMMMMMMMMMMMMMMNd'..:0MMMMMMMMMMMMMMMMMMNo''lXMMWk;.;OMMNo'
//		.;0MMWOllllllllloOWMMXxoooooooooooool;'..:0MMWKxdddddxddxdxddd:''lXMMWk;.;OMMNo'
//		.;0MMNo'.''''''',xWMM0:.'''''''''''''''..:0MMWx,..'''''''.'''''''lXMMMO;.;OMMNo'
//		.;0MMNo'.;x000000XWMM0:''lkOOOOOOOOOkc''.:0MMWx'',okkkkkkkkkkkkkk0WMMMk,.;OMMNo'
//		.:0MMNo'.cKMMMMMMMMMM0:.'xWMMMMMMMMMNd'..:0MMWx,.;0MMMMMMMMMMMMMMMMMMMk,.;OMMNo'
//		':0MMNo'.;oxddxdxxdddl,.,xWMMNOkOKWMNd'..:0MMWx,.;0MMWKOOOOOOOOOOKWMMMk,.;OMMNo'
//		':0MMNo''''''''.'''''.'''dWMM0:',xWMNd'..:0MMWx,.;0MMKc''''''''''oNMMMk,.;OMMNo'
//		':0MMWKkkkkkkkkkkkkkko,''dWMMO;',xWMNd''.:0MMWx,.;0MMKc'';oool,''oNMMMk;.;OMMNo'
//		.:0MMMMMMMMMMMMMMMMMW0:.'xWMM0;.,xWMNd'..:0MMWx,.;0MMKc.'oNMMKc''oNMMWO;.,okkx:'
//		',lxxxxxxxxxxxxxxO0OKO:.'xWMM0:',xWMNd'..:0MMWx,.;OMMKc.'oNMMKc.'oNMMMk;.'''''.'
//		'''''''''''''''',oOOKO:.'dWMM0;.,xWMNd'..:0MMWx'.;OMMKc.'oNMMKc.'oNMMMk;.,lxxl,'
//		':dkkkkkkkkkkkkkkXWMM0:.'xWMM0;.,xWMNd'..:0MMWKxdkXMMKc.'oNMMKc.'oNMMMk;.;OMM0:'
//		'oNMMMMMMMMMMMMMMMMMM0:.'xWMM0;.,xWMNd'..:0MMMMMMMMMMKc.'oNMMKc.'oNMMMk;.;OMM0:'
//		'oXMMWKkkkkkkkkkkkkkko,.,xWMM0:.,xWMNd'..;dkkkkkkkkkkd;.'oNMMKc.'oNMMMk;.;OMM0:'
//		'oNMMXl'''''''''''''''..,kWMM0:',xWMNd'..''''''''''''''.'oNMMKc.'oNMMWk,.;OMM0:.
//		'oXWWNOdddddddddddddddddxKMMM0:.,xWWNOdddddddddddddddddddOWMMKc.'oNMMM0oloKMM0:'
//		'oXXXXXWMMMMMMMMMMMMMMMMMMMMM0;.'o000XWMMMMMMMMMMMMMMMMMMMMMMKc.'oNMMMWWWWWMM0:'
//		':dxxxxkkkkkkkkkkkkkkkkkkkkkko,.':oodxkkkkkkkkkkkkkkkkkkkkkkkd;'':xkkkkkkkkkko,'
//		''''''''''''''''''''''''''''''''.'''''''''''''''''''''''''''''''''''''''''''''''
public class PathFindingApp {

















	//	              __  .__
	//	 ____ _______/  |_|__| ____   ____   ______
	//	/  _ \\____ \   __\  |/  _ \ /    \ /  ___/
	// (  <_> )  |_> >  | |  (  <_> )   |  \\___ \
	//	\____/|   __/|__| |__|\____/|___|  /____  >
	// 	      |__|                       \/     \/
	private enum AppOption {
		GRID_FILE("file", true, "resources/lol.grid"),
		FROM("from", true, "0:0"),
		TO("to", true, "0:0");

		private static String TILE_SEPARATOR = ":";

		private String name;
		private boolean hadArg;
		private String defaultValue;

		AppOption(String name, boolean hadArg, String defaultValue) {
			this.name = name;
			this.hadArg = hadArg;
			this.defaultValue = defaultValue;
		}

		public Option getOption() {
			return new Option(name, hadArg, "");
		}

		public String getName() {
			return name;
		}

		public String getDefaultValue() {
			return defaultValue;
		}

		public static Tile parseTile(String tileOptonValie) {
			return new Tile(Integer.parseInt(tileOptonValie.split(TILE_SEPARATOR)[0]), Integer.parseInt(tileOptonValie.split(TILE_SEPARATOR)[1]));
		}
	}
























	//	  ____   ____
	//	 / ___\ /  _ \
	//	/ /_/  >  <_> )
	//	\___  / \____/
	// /_____/
	public static void main (String[] args) {
		try {
			CommandLineParser commandLineParser = new DefaultParser();
			Options options = new Options();
			for (AppOption appOption : AppOption.values()) {
				options.addOption(appOption.getOption());
			}
			CommandLine cmd = null;

			cmd = commandLineParser.parse(options, args);

			Grid grid = new Grid(FileUtils.readFileToString(new File(cmd.getOptionValue(AppOption.GRID_FILE.getName(), AppOption.GRID_FILE.getDefaultValue()))));

			Tile from = AppOption.parseTile(cmd.getOptionValue(AppOption.FROM.getName(), AppOption.FROM.getDefaultValue()));

			Tile to = AppOption.parseTile(cmd.getOptionValue(AppOption.TO.getName(), AppOption.TO.getDefaultValue()));

			Heuristic heuristic = new ClosestHeuristic();

			PathFinder pathFinder = new AStarPathFinder(grid, heuristic, to);

			System.out.println(pathFinder.findPath(from, to));

		} catch (ParseException e) {
			System.out.println("invalid options!!! - " + ExceptionUtils.getStackTrace(e));
		} catch (IOException e) {
			System.out.println("unable to read grid file!!! - " + ExceptionUtils.getStackTrace(e));
		} catch (Exception e) {
			System.out.println("oh, this is bad!!! - " + ExceptionUtils.getStackTrace(e));
		}
	}



















}
