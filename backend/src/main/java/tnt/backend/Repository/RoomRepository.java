package tnt.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tnt.backend.Entity.Room;
@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
}
