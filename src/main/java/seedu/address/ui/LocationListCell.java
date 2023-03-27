package seedu.address.ui;

import javafx.scene.control.ListCell;
import seedu.address.model.item.Item;

/**
 * The cell that displays the locations in the list.
 *
 * @param <Location> The location to display.
 */
public class LocationListCell<Location extends Item> extends ListCell<Location> {

    private static final String FXML = "LocationListCell.fxml";

    @Override
    protected void updateItem(Location location, boolean empty) {
        super.updateItem(location, empty);
        if (empty || location == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new LocationCard((seedu.address.model.location.Location) location).getRoot());
        }
    }
}