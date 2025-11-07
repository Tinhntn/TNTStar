package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.LanguageDTO;
import tnt.backend.Entity.Language;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.LanguageService;

@RestController
@RequestMapping("/language")
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping()
    public ResponseEntity<?> getAllLanguages(@RequestParam(defaultValue = "0")int page,
                                             @RequestParam(defaultValue = "5")int size) throws BadRequestException {
        return ResponseEntity.ok(languageService.getLanguages(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLanguageById(@PathVariable int id) throws BadRequestException {
        return ResponseEntity.ok(languageService.getLanguageById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createLanguage(@Valid @RequestBody LanguageDTO language) throws BadRequestException {
        return ResponseEntity.ok(languageService.saveLanguage(language));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  updateLanguage(@PathVariable int id, @Valid @RequestBody LanguageDTO language) throws BadRequestException {
        return ResponseEntity.ok(languageService.updateLanguage(language,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable int id) throws BadRequestException {
        return ResponseEntity.ok(languageService.deleteLanguage(id));
    }
}
