package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.InvoiceDTO;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping()
    public ResponseEntity<?> getInvoices(@RequestParam (defaultValue = "0")int page,
                                         @RequestParam (defaultValue = "5")int size) throws BadRequestException {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(invoiceService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoice(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(invoiceService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) throws BadRequestException {
        return ResponseEntity.ok(invoiceService.save(invoiceDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO, @PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(invoiceService.update(invoiceDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(invoiceService.delete(id));
    }

}
