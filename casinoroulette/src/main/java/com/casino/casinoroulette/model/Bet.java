package com.casino.casinoroulette.model;

import java.util.Objects;

public class Bet {
	BetType betType;
	Double value;
	Integer betNumber;

	public Bet(){
	}

	public Bet(BetType betType, Double value, Integer betNumber) {
		this.betType = betType;
		this.value = value;
		this.betNumber = betNumber;
	}

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getBetNumber() {
		return betNumber;
	}

	public void setBetNumber(Integer betNumber) {
		this.betNumber = betNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Bet)) return false;
		Bet bet = (Bet) o;
		return betType == bet.betType &&
				Objects.equals(value, bet.value) &&
				Objects.equals(betNumber, bet.betNumber);
	}

	@Override
	public int hashCode() {

		return Objects.hash(betType, value, betNumber);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Bet{");
		sb.append("betType=").append(betType);
		sb.append(", value=").append(value);
		sb.append(", betNumber=").append(betNumber);
		sb.append('}');
		return sb.toString();
	}
}
