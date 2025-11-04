package tnt.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tnt.backend.Service.ActorService;
import tnt.backend.Service.PositionService;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping()
    public ResponseEntity<?> getAllPositions() {
        return null;
    }
}
