package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.EmployeeDTO;
import tnt.backend.Entity.Employee;
import tnt.backend.Service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) throws Exception{
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(employeeService.getEmployees(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) throws Exception{
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody EmployeeDTO employee) throws Exception{

        return  ResponseEntity.ok(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EmployeeDTO employee, @PathVariable int id) throws Exception{

        return ResponseEntity.ok(employeeService.update(employee, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception{
        return ResponseEntity.ok(employeeService.delete(id));
    }
}
