package seedu.address.model.customGoal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.customGoal.exceptions.DuplicateCustomGoalException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/** TODO update this
 * A list of customGoals that enforces uniqueness between its elements and does not allow nulls.
 * A customGoal is considered unique by comparing using {@code Todo#equals(Object)}. As such, adding and updating of
 * customGoals uses Todo#equals(Object) for equality so as to ensure that the customGoal being added or updated is
 * unique in the UniqueTodoList. Also, the removal of a customGoal uses Todo#equals(Object) so
 * as to ensure that the customGoal with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueCustomGoalList implements Iterable<CustomGoal> {

    private final ObservableList<CustomGoal> internalList = FXCollections.observableArrayList();
    private final ObservableList<CustomGoal> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent custom goal as the given argument.
     */
    public boolean contains(CustomGoal toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a CustomGoal to the list.
     * The CustomGoal must not already exist in the list.
     */
    public void add(CustomGoal toAdd)  {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCustomGoalException(); // TODO
        }
        internalList.add(toAdd);
    }

    public void update(Index goalToUpdate, float valueToUpdateBy) {
        requireNonNull(goalToUpdate);
        CustomGoal updatedGoal = internalList.get(goalToUpdate.getZeroBased()).updateProgress(valueToUpdateBy);
        internalList.remove(goalToUpdate.getZeroBased());
        internalList.add(goalToUpdate.getZeroBased(), updatedGoal);
    }

    /**
     * Removes the equivalent goal from the list.
     * The goal must exist in the list.
     */
//    public void remove(CustomGoal toRemove) {
//        requireNonNull(toRemove);
//        if (!internalList.remove(toRemove)) {
//            throw new CustomGoalNotFoundException(); // TODO
//        }
//    }

    public void setCustomGoals(UniqueCustomGoalList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code customGoals}.
     * {@code customGoals} must not contain duplicate customGoals.
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
//    @Override
//    public int hashCode() {
//        return internalList.hashCode();

//    }

    /**
     * Returns true if {@code customGoals} contains only unique customGoals.
     * @param customGoals
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
