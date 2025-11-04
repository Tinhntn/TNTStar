package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.MovieLanguage;

@Repository
public interface MovieLanguageRepository extends JpaRepository<MovieLanguage, Integer> {
}
