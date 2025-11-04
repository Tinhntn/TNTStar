package tnt.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_director")
    private Director director;

    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre;

    @Column(name = "movie_code")
    private String movieCode;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "duration")
    private String duration;

    @Column(name = "release_time")
    private LocalDate releaseTime;

    @Column(name = "background_image")
    private String backgroundImage;

    @Column(name = "image")
    private String image;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "descibe")
    private String depcription;

    @Column(name = "age_limit")
    private Integer ageLimit;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "movie")
    private List<Shows> shows  = new ArrayList<>();
}
