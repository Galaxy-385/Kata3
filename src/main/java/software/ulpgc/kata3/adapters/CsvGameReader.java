package software.ulpgc.kata3.adapters;

import software.ulpgc.kata3.domain.model.Game;
import software.ulpgc.kata3.domain.model.GameDataset;
import software.ulpgc.kata3.ports.GameReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvGameReader implements GameReader {

    private static final String EXPECTED_HEADER =
            "title,platform,year,genre";

    @Override
    public GameDataset read(InputStream input, String source) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8)
        );

        String header = reader.readLine();

        if (header == null || !EXPECTED_HEADER.equals(header.trim())) {
            throw new IOException("Invalid CSV header. Expected: " + EXPECTED_HEADER);
        }

        List<Game> games = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            parse(line).ifPresent(games::add);
        }

        return new GameDataset(source, games.size(), games);
    }

    private Optional<Game> parse(String line) {
        String[] values = line.split(",", -1);

        if (values.length != 4) {
            return Optional.empty();
        }

        String title = values[0].trim();
        String platform = values[1].trim();
        String year = values[2].trim();
        String genre = values[3].trim();

        if (title.isEmpty() || platform.isEmpty()
                || year.isEmpty() || genre.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(new Game(
                    title,
                    platform,
                    Integer.parseInt(year),
                    genre
            ));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}