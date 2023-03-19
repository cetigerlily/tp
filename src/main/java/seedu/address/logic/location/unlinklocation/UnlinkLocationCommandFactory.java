package seedu.address.logic.location.unlinklocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.fp.Lazy;
import seedu.address.commons.util.GetUtil;
import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyItemManager;
import seedu.address.model.flight.Flight;
import seedu.address.model.location.FlightLocationType;
import seedu.address.model.location.Location;

/**
 * The factory that creates {@code UnlinkLocationCommand}.
 */
public class UnlinkLocationCommandFactory implements CommandFactory<UnlinkLocationCommand> {
    public static final String COMMAND_WORD = "unlink";
    public static final String FLIGHT_PREFIX = "/flight";
    public static final String LOCATION_DEPARTURE_PREFIX = "/from";
    public static final String LOCATION_ARRIVAL_PREFIX = "/to";
    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered. Please enter /from for the departure location"
                    + " and /to for the arrival location.";
    private static final String NO_FLIGHT_MESSAGE =
            "No flight has been entered. Please enter /flight for the flight.";

    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy;

    /**
     * Creates a new unlink command factory with the model registered.
     */
    public UnlinkLocationCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new unlink command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public UnlinkLocationCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getFlightManager)
        );
    }

    /**
     * Creates a new unlink location command factory with the given location manager
     * lazy and the flight manager lazy.
     *
     * @param locationManagerLazy  the lazy instance of the location manager.
     * @param flightManagerLazy the lazy instance of the flight manager.
     */
    public UnlinkLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy
    ) {
        this.locationManagerLazy = locationManagerLazy;
        this.flightManagerLazy = flightManagerLazy;
    }

    /**
     * Creates a new unlink location command factory with the given location manager
     * and the flight manager.
     *
     * @param locationManager  the location manager.
     * @param flightManager the flight manager.
     */
    public UnlinkLocationCommandFactory(
            ReadOnlyItemManager<Location> locationManager,
            ReadOnlyItemManager<Flight> flightManager
    ) {
        this(Lazy.of(locationManager), Lazy.of(flightManager));
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                FLIGHT_PREFIX,
                LOCATION_DEPARTURE_PREFIX,
                LOCATION_ARRIVAL_PREFIX
        ));
    }

    private boolean addLocation(
            Optional<String> locationIdOptional,
            FlightLocationType type,
            Map<FlightLocationType, Location> target
    ) {
        if (locationIdOptional.isEmpty()) {
            return false;
        }
        Optional<Location> locationOptional =
                locationManagerLazy.get().getItem(locationIdOptional.get());
        if (locationOptional.isEmpty()) {
            return false;
        }
        target.put(type, locationOptional.get());
        return true;
    }

    private Flight getFlightOrThrow(
            Optional<String> flightIdOptional
    ) throws ParseException {
        if (flightIdOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        Optional<Flight> flightOptional =
                flightManagerLazy.get().getItem(flightIdOptional.get());
        if (flightOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        return flightOptional.get();
    }


    @Override
    public UnlinkLocationCommand createCommand(CommandParam param) throws ParseException {
        Optional<String> locationDepartureIdOptional =
                param.getNamedValues(LOCATION_DEPARTURE_PREFIX);
        Optional<String> locationArrivalIdOptional =
                param.getNamedValues(LOCATION_ARRIVAL_PREFIX);
        Map<FlightLocationType, Location> locations = new HashMap<>();

        boolean hasFoundLocation = addLocation(
                locationDepartureIdOptional,
                FlightLocationType.LOCATION_DEPARTURE,
                locations
        ) || addLocation(
                locationArrivalIdOptional,
                FlightLocationType.LOCATION_ARRIVAL,
                locations
        );

        if (!hasFoundLocation) {
            throw new ParseException(NO_LOCATION_MESSAGE);
        }

        Flight flight = getFlightOrThrow(param.getNamedValues(FLIGHT_PREFIX));
        return new UnlinkLocationCommand(locations, flight);
    }
}
