package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.CustomerDTO;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0")int page,
                                    @RequestParam(defaultValue = "5")int size) throws BadRequestException {
        return ResponseEntity.ok(customerService.getAllCustomers(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) throws BadRequestException{
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDTO customerDTO) throws BadRequestException {
        return ResponseEntity.ok(customerService.save(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( @Valid @RequestBody CustomerDTO customerDTO,@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(customerService.update(customerDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws BadRequestException {
        if(customerService.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
