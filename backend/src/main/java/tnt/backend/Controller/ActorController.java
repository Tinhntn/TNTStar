package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.ActorDTO;
import tnt.backend.Entity.Actor;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.ActorService;

import java.util.Optional;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    ActorService actorService;

    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam (defaultValue = "0") int page,
                                     @RequestParam (defaultValue = "5") int size) throws BadRequestException {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(actorService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        return  ResponseEntity.ok(actorService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> create( @Valid @RequestBody  ActorDTO actorCreationRequest) {

        actorService.saveActor(actorCreationRequest);
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ActorDTO actor,@PathVariable("id")  Integer id) {

        return ResponseEntity.ok( actorService.updateActor(id,actor));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws BadRequestException {
        actorService.deteletActor(id);
        return ResponseEntity.noContent().build();
    }
}
