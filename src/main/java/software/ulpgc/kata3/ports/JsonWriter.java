package software.ulpgc.kata3.ports;

import software.ulpgc.kata3.domain.model.GameDataset;

public interface JsonWriter {
    String write(GameDataset gameDataset);
}
