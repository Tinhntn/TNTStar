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
@Table(name = "format")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Format {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "format_code")
    private String formatCode;

    @Column(name = "format_name")
    private String formatName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "format")
    private List<MovieFormat> movieFormats  = new ArrayList<>();
}
