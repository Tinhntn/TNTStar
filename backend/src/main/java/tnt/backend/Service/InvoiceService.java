package tnt.backend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.InvoiceDTO;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Invoice;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.EmployeeRepository;
import tnt.backend.Repository.InvoiceRepository;

import java.time.LocalDate;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<InvoiceDTO> getAll(Pageable pageable) throws BadRequestException{

        try {
            Page<InvoiceDTO> lst = invoiceRepository.findAll(pageable).map(invoice -> new InvoiceDTO(invoice));
            return lst;
        }catch (Exception e){
            throw new BadRequestException("Search invoices failed "+e.getMessage());
        }
    }

    public InvoiceDTO findById(Integer id) throws NotFoundException {
        try{
            Invoice invoice = invoiceRepository.findById(id).orElseThrow(()->new NotFoundException("Invoice not found"));
            InvoiceDTO invoiceDTO = new InvoiceDTO(invoice);
            return invoiceDTO;
        }catch (Exception e){
            throw new NotFoundException("Invoice not found "+e.getMessage());
        }
    }
    public boolean save(InvoiceDTO invoiceDTO) throws BadRequestException {
        try{
            modelMapper.typeMap(InvoiceDTO.class,Invoice.class).addMappings(mapper -> {
                mapper.skip(Invoice::setId);
                mapper.skip(Invoice::setCreator);
                mapper.skip(Invoice::setEditor);
            });

            Employee employee = employeeRepository.findById(invoiceDTO.getIdCreator()).orElseThrow(() -> new NotFoundException("Employee not found"));

            Invoice invoice = modelMapper.map(invoiceDTO,Invoice.class);
            String invoiceCode;
            do {
                invoiceCode= CommonUtils.generateCode("Hoa don");
            }while(invoiceRepository.existsByInvoiceCode(invoiceCode));
            invoice.setInvoiceCode(invoiceCode);
            invoice.setCreatedDate(LocalDate.now());
            invoice.setCreator(employee);
            invoiceRepository.save(invoice);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Save invoices failed "+e.getMessage());
        }
    }

    public boolean update(InvoiceDTO invoiceDTO, Integer id) throws BadRequestException {
        try{
            Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new NotFoundException("Invoice not found"));
            modelMapper.typeMap(InvoiceDTO.class,Invoice.class).addMappings(mapper -> {
                mapper.skip(Invoice::setId);
                mapper.skip(Invoice::setCreator);
                mapper.skip(Invoice::setEditor);
            });

            Employee employee = employeeRepository.findById(invoiceDTO.getIdEditor()).orElseThrow(() -> new NotFoundException("Employee not found"));
            modelMapper.map(invoiceDTO,invoice);
            invoice.setEditor(employee);
            invoice.setModifiedDate(LocalDate.now());
            invoiceRepository.save(invoice);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Update invoices failed "+e.getMessage());
        }
    }

    public boolean delete(Integer id) throws BadRequestException {
        try{
            Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new NotFoundException("Invoice not found"));
            invoiceRepository.delete(invoice);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Delete invoices failed "+e.getMessage());
        }
    }
}
