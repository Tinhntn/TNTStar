package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.ActorDTO;
import tnt.backend.DTO.request.ChairsDTO;
import tnt.backend.Service.ChairService;

@RestController
@RequestMapping("/chair")
public class ChairController {
    @Autowired
    private ChairService chairService;

    @GetMapping()
    public ResponseEntity<?> getChair(
                                      @RequestParam(defaultValue = "0")int page,
                                      @RequestParam(defaultValue = "5")int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChairsDTO> chairsDTOS = chairService.findAllByStatus(pageable);
        return ResponseEntity.ok(chairsDTOS);
    }

    @PostMapping()
    public ResponseEntity<?> addChair(@Valid @RequestBody ChairsDTO chairsDTO) {
        try {
            chairService.addChairs(chairsDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Added chair");
    }
}
