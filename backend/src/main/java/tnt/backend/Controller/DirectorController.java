package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.DirectorDTO;
import tnt.backend.Entity.Director;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.DirectorService;

@RestController
@RequestMapping("/director")
public class DirectorController {
    @Autowired
    private DirectorService directorService;

    @GetMapping()
    public ResponseEntity<?> getDirectors(@RequestParam (defaultValue = "0")int page,
                                          @RequestParam (defaultValue = "5")int size) throws BadRequestException {
        return ResponseEntity.ok(directorService.getDirectors(PageRequest.of(page,size)));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDirectorById(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(directorService.getDirector(id));
    }

    @PostMapping()
    public ResponseEntity<?> createDirector(@Valid  @RequestBody DirectorDTO director) throws BadRequestException {
        return  ResponseEntity.ok(directorService.createDirector(director));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDirector(@PathVariable Integer id, @Valid  @RequestBody DirectorDTO director) throws BadRequestException {
        return ResponseEntity.ok(directorService.updateDirector(id,director));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(directorService.deleteDirector(id));
    }
}
