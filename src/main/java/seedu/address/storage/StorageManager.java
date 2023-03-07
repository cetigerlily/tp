package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.flight.Flight;
import seedu.address.model.crew.Crew;
import seedu.address.model.location.Location;
import seedu.address.model.pilot.Pilot;
import seedu.address.model.plane.Plane;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final AddressBookStorage addressBookStorage;
    private final UserPrefsStorage userPrefsStorage;

    private final IdentifiableStorage<Pilot> pilotStorage;
    private final IdentifiableStorage<Location> locationStorage;
    private final IdentifiableStorage<Crew> crewStorage;
    private final IdentifiableStorage<Plane> planeStorage;
    private final IdentifiableStorage<Flight> flightStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage,
                          IdentifiableStorage<Pilot> pilotStorage,
                          IdentifiableStorage<Location> locationStorage,
                          IdentifiableStorage<Crew> crewStorage,
                          IdentifiableStorage<Plane> planeStorage,
                          IdentifiableStorage<Flight> flightStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.pilotStorage = pilotStorage;
        this.locationStorage = locationStorage;
        this.crewStorage = crewStorage;
        this.planeStorage = planeStorage;
        this.flightStorage = flightStorage;
    }


    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }


    // ================ Pilot methods ==============================

    @Override
    public Path getPilotManagerFilePath() {
        return pilotStorage.getPath();
    }

    @Override
    public Optional<? extends ReadOnlyIdentifiableManager<Pilot>> readPilotManager()
            throws DataConversionException, IOException {
        return readPilotManager(pilotStorage.getPath());
    }

    /**
     * Reads the pilot manager from the given file path.
     *
     * @param filePath the file path to read from
     * @return the pilot manager
     * @throws DataConversionException if the file is not in the correct format.
     * @throws IOException             if there was any problem when reading from the file.
     */
    public Optional<? extends ReadOnlyIdentifiableManager<Pilot>> readPilotManager(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return pilotStorage.read(filePath);
    }

    @Override
    public void savePilotManager(ReadOnlyIdentifiableManager<Pilot> pilotManager) throws IOException {
        savePilotManager(pilotManager, pilotStorage.getPath());
    }

    /**
     * Saves the pilot manager to the given file path.
     *
     * @param pilotManager the pilot manager to save
     * @param filePath     the file path to save to
     * @throws IOException if there was any problem writing to the file.
     */
    public void savePilotManager(ReadOnlyIdentifiableManager<Pilot> pilotManager, Path filePath) throws IOException {
        logger.fine("Attempting to saving pilots to data file: " + filePath);
        pilotStorage.save(pilotManager, filePath);
    }


    // ================ Location methods ==============================

    @Override
    public Path getLocationManagerFilePath() {
        return locationStorage.getPath();
    }

    @Override
    public Optional<? extends ReadOnlyIdentifiableManager<Location>> readLocationManager()
            throws DataConversionException, IOException {
        return readLocationManager(locationStorage.getPath());
    }

    /**
     * Reads the location manager from the given file path.
     *
     * @param filePath the file path to read from
     * @return the location manager
     * @throws DataConversionException if the file is not in the correct format.
     * @throws IOException             if there was any problem when reading from the file.
     */
    public Optional<? extends ReadOnlyIdentifiableManager<Location>> readLocationManager(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read location manager from file: " + filePath);
        return locationStorage.read(filePath);
    }

    @Override
    public void saveLocationManager(ReadOnlyIdentifiableManager<Location> locationManager) throws IOException {
        saveLocationManager(locationManager, locationStorage.getPath());
    }

    /**
     * Saves the location manager to the given file path.
     *
     * @param locationManager the location manager to save
     * @param filePath the file path to save to
     * @throws IOException when there are errors writing to the file
     */
    public void saveLocationManager(ReadOnlyIdentifiableManager<Location> locationManager, Path filePath)
            throws IOException {
        logger.fine("Attempting to saving locations to data file: " + filePath);
        locationStorage.save(locationManager, filePath);
    }


    // ================ Crew methods ==============================

    @Override
    public Path getCrewManagerFilePath() {
        return crewStorage.getPath();
    }

    @Override
    public Optional<? extends ReadOnlyIdentifiableManager<Crew>> readCrewManager()
            throws DataConversionException, IOException {
        return readCrewManager(crewStorage.getPath());
    }

    /**
     * Reads the crew manager from the given file path.
     *
     * @param filePath the file path to read from
     * @return the crew manager
     * @throws DataConversionException if the file is not in the correct format.
     * @throws IOException             if there was any problem when reading from the file.
     */
    public Optional<? extends ReadOnlyIdentifiableManager<Crew>> readCrewManager(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return crewStorage.read(filePath);
    }

    @Override
    public void saveCrewManager(ReadOnlyIdentifiableManager<Crew> crewManager) throws IOException {
        saveCrewManager(crewManager, crewStorage.getPath());
    }

    /**
     * Saves the crew manager to the given file path.
     *
     * @param crewManager the pilot manager to save
     * @param filePath     the file path to save to
     * @throws IOException if there was any problem writing to the file.
     */
    public void saveCrewManager(ReadOnlyIdentifiableManager<Crew> crewManager, Path filePath) throws IOException {
        logger.fine("Attempting to saving crews to data file: " + filePath);
        crewStorage.save(crewManager, filePath);
    }


    // ================ Plane methods ==============================

    @Override
    public Path getPlaneManagerFilePath() {
        return planeStorage.getPath();
    }

    @Override
    public Optional<? extends ReadOnlyIdentifiableManager<Plane>> readPlaneManager()
            throws DataConversionException, IOException {
        return readPlaneManager(planeStorage.getPath());
    }

    /**
     * Reads the plane manager from the given file path.
     *
     * @param filePath                 the file path to read from.
     * @return                         the plane manager.
     * @throws DataConversionException if the file is not in the correct format.
     * @throws IOException             if there was any problem when reading from the file.
     */
    public Optional<? extends ReadOnlyIdentifiableManager<Plane>> readPlaneManager(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return planeStorage.read(filePath);
    }

    @Override
    public void savePlaneManager(ReadOnlyIdentifiableManager<Plane> planeManager) throws IOException {
        savePlaneManager(planeManager, planeStorage.getPath());
    }

    /**
     * Saves the plane manager to the given file path.
     *
     * @param planeManager the location manager to save
     * @param filePath     the file path to save to
     * @throws IOException when there are errors writing to the file
     */
    public void savePlaneManager(ReadOnlyIdentifiableManager<Plane> planeManager, Path filePath) throws IOException {
        logger.fine("Attempting to saving pilots to data file: " + filePath);
        planeStorage.save(planeManager, filePath);
    }


    // ================ Flight methods ==============================

    @Override
    public Path getFlightManagerFilePath() {
        return flightStorage.getPath();
    }

    @Override
    public Optional<? extends ReadOnlyIdentifiableManager<Flight>> readFlightManager()
            throws DataConversionException, IOException {
        return readFlightManager(flightStorage.getPath());
    }

    /**
     * Reads the flight manager from the given file path.
     *
     * @param filePath the file path to read from
     * @return the flight manager
     * @throws DataConversionException if the file is not in the correct format.
     * @throws IOException             if there was any problem when reading from the file.
     */
    public Optional<? extends ReadOnlyIdentifiableManager<Flight>> readFlightManager(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return flightStorage.read(filePath);
    }

    @Override
    public void saveFlightManager(ReadOnlyIdentifiableManager<Flight> flightManager) throws IOException {
        saveFlightManager(flightManager, flightStorage.getPath());
    }

    /**
     * Saves the flight manager to the given file path.
     *
     * @param flightManager the flight manager to save
     * @param filePath     the file path to save to
     * @throws IOException if there was any problem writing to the file.
     */
    public void saveFlightManager(ReadOnlyIdentifiableManager<Flight> flightManager, Path filePath) throws IOException {
        logger.fine("Attempting to saving flights to data file: " + filePath);
        flightStorage.save(flightManager, filePath);
    }
}
