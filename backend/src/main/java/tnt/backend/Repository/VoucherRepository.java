package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
}
