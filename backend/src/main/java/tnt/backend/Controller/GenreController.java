package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.GenreDTO;
import tnt.backend.Entity.Genre;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.GenreService;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0")int page,
                                    @RequestParam(defaultValue = "5")int size) throws BadRequestException {
        return ResponseEntity.ok(genreService.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) throws BadRequestException {
        return ResponseEntity.ok(genreService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody GenreDTO genre) throws BadRequestException {
        return  ResponseEntity.ok(genreService.save(genre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody GenreDTO genre) throws BadRequestException {
        return ResponseEntity.ok(genreService.update(genre,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws BadRequestException {
        return ResponseEntity.ok(genreService.delete(id));
    }
}
