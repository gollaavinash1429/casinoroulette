package com.casino.casinoroulette.model;

import java.util.Objects;

public class Player {
	String name;
	Double totalWin;
	Double totalBet;

	public Player(String name, Double totalWin, Double totalBet) {
		this.name = name;
		this.totalWin = totalWin;
		this.totalBet = totalBet;
	}

	public Player() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalWin() {
		return totalWin;
	}

	public void setTotalWin(Double totalWin) {
		this.totalWin = totalWin;
	}

	public void sumTotalWin(Double win) {
		this.totalWin += win;
	}

	public Double getTotalBet() {
		return totalBet;
	}

	public void setTotalBet(Double totalBet) {
		this.totalBet = totalBet;
	}

	public void sumTotalBet(Double bet) {
		this.totalBet += bet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;
		Player that = (Player) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(totalWin, that.totalWin) &&
				Objects.equals(totalBet, that.totalBet);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name, totalWin, totalBet);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Player{");
		sb.append("name='").append(name).append('\'');
		sb.append(", totalWin=").append(totalWin);
		sb.append(", totalBet=").append(totalBet);
		sb.append('}');
		return sb.toString();
	}
}
