package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.PositionDTO;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.ActorService;
import tnt.backend.Service.PositionService;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping()
    public ResponseEntity<?> getAllPositions(@RequestParam(defaultValue = "5")int size,
                                             @RequestParam(defaultValue = "0")int page) throws BadRequestException {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(positionService.getPositions(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPositionById(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(positionService.getPositionById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createPosition( @Valid @RequestBody PositionDTO positionDTO) throws BadRequestException {
        Boolean save = positionService.savePosition(positionDTO);
        if (save) {
            return ResponseEntity.ok("Position created");
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePosition(@PathVariable Integer id,@Valid @RequestBody PositionDTO positionDTO) throws BadRequestException {
        positionService.updatePosition(positionDTO, id);
        return ResponseEntity.ok("Updated position");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePosition(@PathVariable Integer id) throws BadRequestException {
        positionService.deletePositionById(id);
        return ResponseEntity.noContent().build();
    }
}
