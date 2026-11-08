package software.ulpgc.kata3.domain.model;

import java.util.List;

public record GameDataset(
        String source,
        int totalGames,
        List<Game> games
) {
}
