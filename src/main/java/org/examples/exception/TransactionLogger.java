package org.examples.exception;

import org.examples.events.IdentifiableCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TransactionLogger {
    private final Logger logger = LoggerFactory.getLogger(TransactionLogger.class);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(IdentifiableCreatedEvent event) {
        logger.info("Identifiable failed during creation with id : {}", event.getIdentifiable().getId());
    }
}
