package tnt.backend.Service.Implement;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MovieActorId implements Serializable {
    @Column(name = "id_movie" )
    private Integer idMovie;

    @Column(name = "id_actor")
    private Integer idActor;
}
