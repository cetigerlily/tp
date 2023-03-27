package seedu.address.logic.toplevel.delete;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.exception.IndexOutOfBoundException;
import seedu.address.model.item.Item;

/**
 * The command factory that produces commands that deletes the item.
 *
 * @param <T> the item.
 */
public class DeleteCommandFactory<T extends Item> implements CommandFactory<DeleteCommand<T>> {
    /**
     * The command word for deletion.
     */
    private static final String COMMAND_WORD = "del";
    /**
     * The function that gets the manager.
     */
    private final GetManagerFunction<T> getManagerFunction;
    /**
     * The function that deletes the item.
     */
    private final DeleteFunction<T> deleteFunction;

    /**
     * The command factory that deletes things.
     *
     * @param getManagerFunction the function that gets the manager.
     * @param deleteFunction     the function that deletes the object.
     */
    public DeleteCommandFactory(
            GetManagerFunction<T> getManagerFunction,
            DeleteFunction<T> deleteFunction
    ) {
        this.getManagerFunction = getManagerFunction;
        this.deleteFunction = deleteFunction;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.empty();
    }

    @Override
    public DeleteCommand<T> createCommand(CommandParam param) throws ParseException {
        int index = param.getUnnamedIntOrThrow() - 1;
        try {
            return new DeleteCommand<>(
                    index,
                    getManagerFunction,
                    deleteFunction
            );
        } catch (IndexOutOfBoundException e) {
            throw new ParseException(String.format(
                    "Please enter a valid index: %s",
                    e.getMessage()
            ));
        }
    }
}