package com.casino.casinoroulette.gameround;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.casino.casinoroulette.model.Bet;
import com.casino.casinoroulette.model.BetType;
import com.casino.casinoroulette.model.Player;
import com.casino.casinoroulette.model.ResultRoulette;

public class GameRoundThread extends Thread {

	private volatile Map<String, List<Bet>> bets = new ConcurrentHashMap<>();
	private static final long ROUND_DELAY_IN_MILLS = 30000;
	private List<Player> players;

	public GameRoundThread(List<Player> players) {
		super();
		this.players = players;
	}

	@Override
	public void run() {
		while (true) {
			try {
				sleep(ROUND_DELAY_IN_MILLS);
				Integer rouletteNumber = runRoulette();
				List<ResultRoulette> results = new ArrayList<>();
				bets.forEach((player, bet) -> results.addAll(evaluateResultForPlayer(player, bet, rouletteNumber)));
				ResultView.printResult(rouletteNumber, results, players);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				resetBets();
			}
		}
	}

	public synchronized void placeBet(String player, Bet bet) {
		if (bets.containsKey(player)) {
			bets.get(player).add(bet);
		} else {
			List<Bet> playerBets = Collections.synchronizedList(new ArrayList<>());
			playerBets.add(bet);
			bets.put(player,playerBets);
		}
	}

	protected synchronized void resetBets() {
		bets.clear();
	}

	protected Integer runRoulette() {
		Random random = new Random();
		return random.ints(0, (37)).limit(1).findFirst().getAsInt();
	}

	protected List<ResultRoulette> evaluateResultForPlayer(String player, List<Bet> bets, Integer rouletteNumber) {
		List<ResultRoulette> results = new ArrayList<>();
		bets.parallelStream().forEach(bet ->{
			ResultRoulette resultRoulette = new ResultRoulette(player);
			resultRoulette.setBetType(getBetTypeDescription(bet));
			resultRoulette.setWinnings(calculateWinnings(bet, rouletteNumber));
			resultRoulette.setOutcome(calculateOutcome(resultRoulette));
			resultRoulette.setAmountBet(bet.getValue());
			registerPlayerBet(resultRoulette);
			results.add(resultRoulette);
		});
		return results;
	}

	private String getBetTypeDescription(Bet bet) {
		if (bet.getBetType().equals(BetType.NUMBER)) {
			return bet.getBetNumber().toString();
		}
		return bet.getBetType().name();
	}

	private String calculateOutcome(ResultRoulette resultRoulette) {
		if (resultRoulette.getWinnings().equals(0.0)) {
			return "LOSE";
		}
		return "WIN";
	}

	private Double calculateWinnings(Bet bet, Integer rouletteNumber) {
		if (bet.getBetType().equals(BetType.NUMBER)) {
			if (bet.getBetNumber().equals(rouletteNumber)) {
				return bet.getValue() * 36;
			} else {
				return 0.0;
			}
		} else if (bet.getBetType().equals(BetType.EVEN)) {
			if (isNumberEven(rouletteNumber)) {
				return bet.getValue() * 2;
			} else {
				return 0.0;
			}
		} else {
			if (isNumberOdd(rouletteNumber)) {
				return bet.getValue() * 2;
			} else {
				return 0.0;
			}
		}
	}

	private boolean isNumberEven(Integer rouletteNumber) {
		return rouletteNumber % 2 == 0;
	}

	private boolean isNumberOdd(Integer rouletteNumber) {
		return rouletteNumber % 2 != 0;
	}

	private void registerPlayerBet(ResultRoulette resultRoulette) {
		Player player = players.stream().filter(
				playerStats -> playerStats.getName().equals(resultRoulette.getPlayer()))
				.findFirst().get();
		player.sumTotalBet(resultRoulette.getAmountBet());
		player.sumTotalWin(resultRoulette.getWinnings());
	}

	protected Map<String, List<Bet>> getBets() {
		return Collections.unmodifiableMap(this.bets);
	}
}