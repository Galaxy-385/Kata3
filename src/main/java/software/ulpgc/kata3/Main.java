
package software.ulpgc.kata3;

import software.ulpgc.kata3.adapters.CsvGameReader;
import software.ulpgc.kata3.adapters.GsonJsonWriter;
import software.ulpgc.kata3.domain.model.GameDataset;
import software.ulpgc.kata3.ports.GameReader;
import software.ulpgc.kata3.ports.JsonWriter;

public class Main {

    public static void main(String[] args) {
        GameReader reader = new CsvGameReader();
        JsonWriter writer = new GsonJsonWriter();

        GameDataset dataset = reader.read();

        String json = writer.write(dataset);

        System.out.println(json);
    }
}
