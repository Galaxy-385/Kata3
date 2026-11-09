package software.ulpgc.kata3.ports;

import software.ulpgc.kata3.domain.model.GameDataset;

import java.io.IOException;
import java.io.InputStream;

public interface GameReader {
    GameDataset read(InputStream input, String source) throws IOException;
}