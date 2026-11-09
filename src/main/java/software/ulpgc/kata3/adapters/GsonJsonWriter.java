
package software.ulpgc.kata3.adapters;

import com.google.gson.Gson;
import software.ulpgc.kata3.domain.model.GameDataset;
import software.ulpgc.kata3.ports.JsonWriter;

public class GsonJsonWriter implements JsonWriter {

    private final Gson gson;

    public GsonJsonWriter() {
        this.gson = new Gson();
    }

    @Override
    public String write(GameDataset dataset) {
        return gson.toJson(dataset);
    }
}
