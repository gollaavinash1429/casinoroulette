package com.casino.casinoroulette.gameround;



import java.util.List;

import com.casino.casinoroulette.model.Player;
import com.casino.casinoroulette.model.ResultRoulette;

import de.vandermeer.asciitable.AsciiTable;

public class ResultView {
	public static void printResult(Integer rouletteNumber, List<ResultRoulette> results, List<Player> players) {
		if (results == null || results.isEmpty()) {
			printEmptyResultTable(rouletteNumber);
		} else {
			printResultTable(rouletteNumber, results);
		}
		printWinningResultTable(players);
	}

	private static void printResultTable(Integer rouletteNumber, List<ResultRoulette> results) {
		AsciiTable asciiTable = new AsciiTable();
		asciiTable.addRule();
		asciiTable.addRow("Number: " + rouletteNumber, "", "", "");
		asciiTable.addRule();
		asciiTable.addRow("Player", "Bet", "Outcome", "Winnings");
		asciiTable.addRule();
		results.forEach(result -> {
			asciiTable.addRow(result.getPlayer(), result.getBetType(), result.getOutcome(), result.getWinnings());
		});
		asciiTable.addRule();
		System.out.println(asciiTable.render());
	}

	private static void printEmptyResultTable(Integer rouletteNumber) {
		AsciiTable asciiTable = new AsciiTable();
		asciiTable.addRule();
		asciiTable.addRow("Number: " + rouletteNumber);
		asciiTable.addRule();
		asciiTable.addRow("No bets");
		asciiTable.addRule();
		System.out.println(asciiTable.render());
	}

	private static void printWinningResultTable(List<Player> players) {
		AsciiTable asciiTable = new AsciiTable();
		asciiTable.addRule();
		asciiTable.addRow("Player", "Total Win", "Total bet");
		asciiTable.addRule();
		players.forEach(result -> {
			asciiTable.addRow(result.getName(), result.getTotalWin(), result.getTotalBet());
		});
		asciiTable.addRule();
		System.out.println(asciiTable.render());
	}
}
