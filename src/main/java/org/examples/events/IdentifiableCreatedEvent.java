package org.examples.events;

import org.examples.model.Employee;
import org.examples.model.Identifiable;

public class IdentifiableCreatedEvent {
    private Identifiable identifiable;

    public IdentifiableCreatedEvent(Identifiable identifiable) {
        this.identifiable = identifiable;
    }

    public Identifiable getIdentifiable() {
        return identifiable;
    }
}
