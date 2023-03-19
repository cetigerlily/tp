package seedu.address.logic.crew.linkcrew;

import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.crew.Crew;
import seedu.address.model.crew.FlightCrewType;
import seedu.address.model.flight.Flight;
import seedu.address.model.link.exceptions.LinkException;

/**
 * The command that links a crew to a flight
 */
public class LinkCrewCommand implements Command {
    private static final String FLIGHT_NOT_FOUND_EXCEPTION =
            "Flight with id %s is not found.";
    private static final String CREW_NOT_FOUND_EXCEPTION =
            "Crew with id %s is not found.";
    private static final String DISPLAY_MESSAGE =
            "Linked %s to flight %s.";

    /**
     * The id of the crews
     */
    private final Map<FlightCrewType, Crew> crews;

    /**
     * The id of the flight
     */
    private final Flight flight;

    /**
     * Creates a new link command.
     *
     * @param crews the id of the crews.
     * @param flight the id of the flight.
     */
    public LinkCrewCommand(Map<FlightCrewType, Crew> crews, Flight flight) {
        this.crews = crews;
        this.flight = flight;
    }

    @Override
    public String toString() {
        String result = crews.entrySet()
                .stream()
                .map((entry) -> String.format(
                        "%s: %s",
                        entry.getKey(),
                        entry.getValue().getName()))
                .collect(Collectors.joining(","));
        return String.format(DISPLAY_MESSAGE, result, flight.getCode());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<FlightCrewType, Crew> entry : crews.entrySet()) {
                flight.crewLink.putRevolve(entry.getKey(), entry.getValue());
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(this.toString());
    }

}
