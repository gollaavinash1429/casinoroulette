package com.casino.casinoroulette.gameround;

import java.util.List;

import com.casino.casinoroulette.exception.InvalidFileException;
import com.casino.casinoroulette.model.Player;

public interface FileParser {
	public List<Player> retrievePlayersFromFile(String filePath) throws InvalidFileException;
}
