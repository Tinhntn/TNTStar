package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
