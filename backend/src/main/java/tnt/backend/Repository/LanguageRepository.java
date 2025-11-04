package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Language;
@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
