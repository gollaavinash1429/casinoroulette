package com.roulette.ConsoleRoulette;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roulette.ConsoleRoulette.exception.InvalidFileException;
import com.roulette.ConsoleRoulette.gameround.FileParser;
import com.roulette.ConsoleRoulette.gameround.FileParserDefault;
import com.roulette.ConsoleRoulette.gameround.GameRoundThread;
import com.roulette.ConsoleRoulette.model.Bet;
import com.roulette.ConsoleRoulette.model.BetType;
import com.roulette.ConsoleRoulette.model.Player;
import com.roulette.ConsoleRoulette.validation.CommandLineValidator;

@SpringBootApplication
public class ConsoleRouletteApplication {
	
	private static final String ENTER_THE_PLAYERS_FILE = "Enter players file:";

	public static void main(String[] args) {
		SpringApplication.run(ConsoleRouletteApplication.class, args);
		
		Scanner in = new Scanner(System.in);
		boolean invalidFile = true;
		List<Player> players = null;
		
		System.out.println(ENTER_THE_PLAYERS_FILE);
		while (invalidFile) {
			System.out.println("before filepath");
			String filePath = in.nextLine();
			System.out.println("filepath is "+filePath);
			FileParser fileParser = new FileParserDefault();
			System.out.println("after filepath");
			try {
				players = fileParser.retrievePlayersFromFile(filePath);
				invalidFile = false;
			} catch (InvalidFileException e) {
				System.out.println(e.getMessage());
				System.out.println("Try again.");
				System.out.println(ENTER_THE_PLAYERS_FILE);
				System.out.println(e.toString());
			}
		}

		System.out.println("Starting game. Place your bets!");

		GameRoundThread gameRoundThread = new GameRoundThread(players);
		gameRoundThread.start();

		while (true) {
			String commandLine = in.nextLine();
			if (CommandLineValidator.isCommandLineValid(commandLine, players)) {
				placeBetFromLine(commandLine, gameRoundThread);
			}
			System.out.println("Next bet:");
		}
	}

private static void placeBetFromLine(String commandLine, GameRoundThread gameRoundThread) {
	if (commandLine.equals("exit")) {
		System.exit(0);
	}
	String[] commands = commandLine.split(" ");
	Bet bet = createBetFromCommandLine(commands[1], commands[2]);
	gameRoundThread.placeBet(commands[0], bet);
}

private static Bet createBetFromCommandLine(String betTypeStr, String betValueStr) {
	Bet bet = new Bet();
	switch (betTypeStr) {
		case "EVEN" :
			bet.setBetType(BetType.EVEN);
			break;
		case "ODD" :
			bet.setBetType(BetType.ODD);
			break;
		default :
			bet.setBetType(BetType.NUMBER);
			bet.setBetNumber(Integer.valueOf(betTypeStr));
			break;
	}
	bet.setValue(Double.valueOf(betValueStr));
	return bet;
}

}
