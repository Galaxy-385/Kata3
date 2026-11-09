package software.ulpgc.kata3;

import software.ulpgc.kata3.adapters.CsvGameReader;
import software.ulpgc.kata3.adapters.GsonJsonWriter;
import software.ulpgc.kata3.domain.model.GameDataset;
import software.ulpgc.kata3.ports.GameReader;
import software.ulpgc.kata3.ports.JsonWriter;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        GameReader reader = new CsvGameReader();
        JsonWriter writer = new GsonJsonWriter();

        try (InputStream input = Main.class.getClassLoader()
                .getResourceAsStream("games.csv")) {

            if (input == null) {
                throw new IOException("games.csv not found");
            }

            GameDataset dataset = reader.read(input, "games.csv");

            String json = writer.write(dataset);

            System.out.println(json);

        } catch (IOException e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
    }
}