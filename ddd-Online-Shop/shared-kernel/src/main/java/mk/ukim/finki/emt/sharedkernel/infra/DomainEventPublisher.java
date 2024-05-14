package mk.ukim.finki.emt.sharedkernel.infra;

import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    static void publish(DomainEvent event);

}
