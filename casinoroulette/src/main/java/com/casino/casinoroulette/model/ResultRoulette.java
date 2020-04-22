package com.casino.casinoroulette.model;

import java.util.Objects;

public class ResultRoulette {
	String player;
	String betType;
	String outcome;
	Double winnings;
	Double amountBet;

	public ResultRoulette(String player) {
		this.player = player;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public Double getWinnings() {
		return winnings;
	}

	public void setWinnings(Double winnings) {
		this.winnings = winnings;
	}

	public Double getAmountBet() {
		return amountBet;
	}

	public void setAmountBet(Double amountBet) {
		this.amountBet = amountBet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ResultRoulette)) return false;
		ResultRoulette that = (ResultRoulette) o;
		return Objects.equals(player, that.player) &&
				Objects.equals(betType, that.betType) &&
				Objects.equals(outcome, that.outcome) &&
				Objects.equals(winnings, that.winnings) &&
				Objects.equals(amountBet, that.amountBet);
	}

	@Override
	public int hashCode() {

		return Objects.hash(player, betType, outcome, winnings, amountBet);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ResultRoulette{");
		sb.append("player='").append(player).append('\'');
		sb.append(", betType='").append(betType).append('\'');
		sb.append(", outcome='").append(outcome).append('\'');
		sb.append(", winnings=").append(winnings);
		sb.append(", amountBet=").append(amountBet);
		sb.append('}');
		return sb.toString();
	}
}
