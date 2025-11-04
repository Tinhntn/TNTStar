package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
