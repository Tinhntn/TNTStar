package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.CinemaDTO;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.CinemaService;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam (defaultValue = "0")int page,
                                    @RequestParam (defaultValue = "5")int size) throws BadRequestException {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(cinemaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(cinemaService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CinemaDTO cinemaDTO) throws BadRequestException {
        return ResponseEntity.ok(cinemaService.save(cinemaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@Valid @RequestBody CinemaDTO cinemaDTO) throws BadRequestException {
        return ResponseEntity.ok(cinemaService.update(cinemaDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(cinemaService.delete(id));
    }

}
