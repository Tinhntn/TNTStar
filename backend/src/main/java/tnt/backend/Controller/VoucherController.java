package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.VoucherDTO;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.VoucherService;

import java.util.Optional;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping
    public ResponseEntity<?> findAllVouchers(@RequestParam(defaultValue = "0")int page,
                                                            @RequestParam(defaultValue = "5")int size) throws BadRequestException {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(voucherService.findAllVouchers(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(voucherService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) throws BadRequestException {
        voucherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<?> saveVoucher(@Valid @RequestBody VoucherDTO dto) throws BadRequestException {
        return ResponseEntity.ok(voucherService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVoucher(@Valid @RequestBody VoucherDTO dto, @PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(voucherService.update(dto, id));
    }
}
