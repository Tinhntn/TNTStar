package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.DetailedInvoice;

@Repository
public interface DetailedInvoiceRepository extends JpaRepository<DetailedInvoice, Integer> {
}
