package com.casino.casinoroulette.gameround;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import com.casino.casinoroulette.exception.InvalidFileException;
import com.casino.casinoroulette.model.Player;

import org.apache.commons.lang3.math.NumberUtils;


public class FileParserDefault implements FileParser {

	@Override
	public List<Player> retrievePlayersFromFile(String filePath) throws InvalidFileException {
		if (StringUtils.isEmpty(filePath)) {
			throw new InvalidFileException("Invalid file path.");
		}
		Path file = Paths.get(filePath);
		try(BufferedReader reader = Files.newBufferedReader(file)) {
			return extractPlayerFromLine(reader.lines().collect(Collectors.toList()));
		} catch (FileNotFoundException e) {
			throw new InvalidFileException("File not found.", e);
		} catch (Exception e) {
			throw new InvalidFileException("Error parsing file.", e);
		}
	}

	private List<Player> extractPlayerFromLine(List<String> lines) throws InvalidFileException {
		if (lines == null || lines.isEmpty()) {
			throw new InvalidFileException("Invalid file. Empty file.");
		}
		List<Player> playerResult = new ArrayList<>();
		for (String line : lines) {
			Player player = new Player();
			String[] lineValues = line.split(",");
			
			
			if (lineValues.length == 3) {
				if (!NumberUtils.isCreatable(lineValues[1]) || !NumberUtils.isCreatable(lineValues[2])) {
					throw new InvalidFileException("Invalid file. Invalid values.");
				}
				
				player.setName(lineValues[0]);
				player.setTotalWin(Double.valueOf(lineValues[1]));
				player.setTotalBet(Double.valueOf(lineValues[2]));
			} 
		
		else {
				
				player.setName(lineValues[0]);
				player.setTotalBet(0.0);
				player.setTotalWin(0.0);
			}
			playerResult.add(player);
		}

		return Collections.synchronizedList(playerResult);
	}
}
