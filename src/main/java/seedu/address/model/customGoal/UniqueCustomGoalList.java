package seedu.address.model.customGoal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.customGoal.exceptions.DuplicateCustomGoalException;
import seedu.address.model.customGoal.exceptions.NegativeProgressException;

/**
 * A list of unique non-null CustomGoals which supports basic list operations.
 */
public class UniqueCustomGoalList implements Iterable<CustomGoal> {

    private final ObservableList<CustomGoal> internalList = FXCollections.observableArrayList();
    private final ObservableList<CustomGoal> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains a custom goal equal to the one specified in the argument.
     * @see CustomGoal#equals(java.lang.Object)
     */
    public boolean contains(CustomGoal toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a new unique CustomGoal to the list.
     */
    public void add(CustomGoal toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCustomGoalException();
        }
        internalList.add(toAdd);
    }

    /**
     * Increases the progress of the <code>CustomGoal</code> corresponding to the specified <code>Index</code> by the
     * specified value.
     * @param goalToUpdate The <code>Index</code> of the desired <code>CustomGoal</code>.
     * @param valueToUpdateBy The value to update the progress of the specified <code>CustomGoal</code> by.
     */
    public void update(Index goalToUpdate, float valueToUpdateBy) {
        requireNonNull(goalToUpdate);
        CustomGoal oldGoal = internalList.get(goalToUpdate.getZeroBased());
        float oldProgress = oldGoal.getProgress();
        CustomGoal updatedGoal = oldGoal.updateProgress(valueToUpdateBy);
        internalList.remove(goalToUpdate.getZeroBased());
        internalList.add(goalToUpdate.getZeroBased(), updatedGoal);
        if (oldProgress + valueToUpdateBy < 0) {
            throw new NegativeProgressException();
        }
    }

    /**
     * Deletes the <code>CustomGoal</code> corresponding to the specified <code>Index</code> from the list.
     * @param goalToDelete The <code>Index</code> of the desired <code>CustomGoal</code> to be deleted (1-based).
     */
    public void delete(Index goalToDelete) {
        requireNonNull(goalToDelete);
        internalList.remove(goalToDelete.getZeroBased());
    }

    /**
     * Replaces the contents of this list with {@code customGoals}.
     * {@code customGoals} must not contain duplicates.
     */
    public void setCustomGoals(List<CustomGoal> customGoals) {
        requireAllNonNull(customGoals);
        if (!customGoalsAreUnique(customGoals)) {
            throw new DuplicateCustomGoalException();
        }

        internalList.setAll(customGoals);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<CustomGoal> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<CustomGoal> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCustomGoalList // instanceof handles nulls
                        && internalList.equals(((UniqueCustomGoalList) other).internalList));
    }

    /**
     * Returns true if {@code customGoals} contains only unique customGoals.
     */
    private boolean customGoalsAreUnique(List<CustomGoal> customGoals) {
        for (int i = 0; i < customGoals.size() - 1; i++) {
            for (int j = i + 1; j < customGoals.size(); j++) {
                if (customGoals.get(i).equals(customGoals.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
