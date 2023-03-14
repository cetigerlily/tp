package seedu.address.logic.core.exceptions;

/**
 * Represents a parse error encountered by a parser.
 */
public class DuplicatePrefixesException extends ParseException {
    private static final String ERROR_MESSAGE = "Duplicate prefixes detected, only have one input per field.";
    public DuplicatePrefixesException() {
        super(ERROR_MESSAGE);
    }
}
