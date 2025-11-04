package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
