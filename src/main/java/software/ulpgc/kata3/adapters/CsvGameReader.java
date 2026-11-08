
package software.ulpgc.kata3.adapters;

import software.ulpgc.kata3.domain.model.Game;
import software.ulpgc.kata3.domain.model.GameDataset;
import software.ulpgc.kata3.ports.GameReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvGameReader implements GameReader {

    @Override
    public GameDataset read() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("games.csv")) {
            if (input == null) {
                throw new RuntimeException("games.csv not found");
            }

            return readFrom(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private GameDataset readFrom(InputStream input) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            reader.readLine();

            List<Game> games = reader.lines()
                    .map(this::toGame)
                    .filter(game -> game != null)
                    .toList();

            return new GameDataset("games.csv", games.size(), games);
        }
    }

    private Game toGame(String line) {
        String[] values = line.split(",", -1);

        if (values.length != 4) {
            return null;
        }

        try {
            return new Game(
                    values[0].trim(),
                    values[1].trim(),
                    Integer.parseInt(values[2].trim()),
                    values[3].trim()
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
