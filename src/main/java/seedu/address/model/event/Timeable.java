package seedu.address.model.event;

abstract class Timeable {
    abstract String add(Event currTask);

    abstract String delete(Event currTask);

    abstract String view();
}
