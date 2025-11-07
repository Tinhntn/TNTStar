package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.FormatDTO;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Service.FormatService;

@RestController
@RequestMapping("/format")
public class FormatController {
    @Autowired
    private FormatService formatService;

    @GetMapping()
    public ResponseEntity<?> getAllFormat(@RequestParam(defaultValue = "0")int page,
                                          @RequestParam(defaultValue = "5")int size) throws BadRequestException {
        return ResponseEntity.ok(formatService.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFormatById(@PathVariable int id) throws BadRequestException {
        return ResponseEntity.ok(formatService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createFormat(@Valid @RequestBody FormatDTO formatDTO) throws BadRequestException {
        return ResponseEntity.ok(formatService.save(formatDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFormat(@PathVariable int id, @Valid @RequestBody FormatDTO formatDTO) throws BadRequestException {
        return ResponseEntity.ok(formatService.update(formatDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFormat(@PathVariable int id) throws BadRequestException {
        return ResponseEntity.ok(formatService.delete(id));
    }
}
