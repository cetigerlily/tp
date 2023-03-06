package seedu.address.storage.json.storage;

import java.nio.file.Path;

import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.location.Location;
import seedu.address.storage.json.JsonIdentifiableStorage;
import seedu.address.storage.json.adapted.JsonAdaptedLocation;
import seedu.address.storage.json.serializable.JsonSerializableLocationManager;

/**
 * Represents a storage for {@link Location}s.
 */
public class JsonLocationManagerStorage extends JsonIdentifiableStorage<Location,
        JsonAdaptedLocation, JsonSerializableLocationManager> {
    /**
     * Creates a new JsonIdentifiableStorage object.
     *
     * @param filePath the path to the file to be read from and written to.
     */
    public JsonLocationManagerStorage(Path filePath) {
        super(filePath);
    }

    @Override
    protected Class<JsonSerializableLocationManager> getManagerClass() {
        return JsonSerializableLocationManager.class;
    }

    @Override
    protected JsonSerializableLocationManager createManager(ReadOnlyIdentifiableManager<Location> modelManager) {
        return JsonSerializableLocationManager.from(modelManager);
    }
}