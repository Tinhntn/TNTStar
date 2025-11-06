package tnt.backend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tnt.backend.DTO.request.CustomerDTO;
import tnt.backend.Entity.Customer;
import tnt.backend.Entity.Voucher;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.CustomerRepository;
import tnt.backend.Repository.VoucherRepository;

import java.time.LocalDate;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private ModelMapper modelMapper;
    // Service methods to handle customer-related operations
    //Crud operations, business logic, etc.
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) throws BadRequestException {
        // Implementation for fetching paginated customers
        try{
            Page<CustomerDTO> lstCustomer = customerRepository.findAll(pageable).map(c -> new CustomerDTO(c));
            return lstCustomer;
        }catch (Exception e){
            throw new BadRequestException("Get all customers failed"+e.getMessage());
        }

    }

    public CustomerDTO findById(Integer id) throws BadRequestException {
        try{
            return customerRepository.findById(id).map(c -> new CustomerDTO(c)).orElseThrow(() -> new NotFoundException("Customer not found"));
        }catch (Exception e){
            throw new BadRequestException("Customer not found");
        }
    }

    public boolean save(CustomerDTO customerDTO) throws BadRequestException {
        try{
            Voucher voucher = voucherRepository.findById(customerDTO.getIdVoucher()).orElseThrow(() -> new NotFoundException("Position not found"));
            Customer customer = new Customer(voucher,customerDTO);
            customer.setCreatedDate(LocalDate.now());
            //generate Employee code
            String customerCode;
            do {
                customerCode = CommonUtils.generateCode(customerDTO.getFirstName()+" "+customerDTO.getLastName());
            }while (customerRepository.existsByCustomerCode(customerCode));
            customer.setCustomerCode(customerCode);
            customerRepository.save(customer);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Create falied employee"+e.getMessage());
        }
    }

    public boolean delete(Integer id) throws BadRequestException {
        try{
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
            customerRepository.delete(customer);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Customer not found"+e.getMessage());
        }
    }

    public boolean update(CustomerDTO customerDTO, Integer id) throws BadRequestException {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
            Voucher voucher = voucherRepository.findById(customerDTO.getIdVoucher()).orElseThrow(() -> new NotFoundException("Position not found"));
            modelMapper.map(customerDTO,customer);
            customer.setVoucher(voucher);
            customer.setModifiedDate(LocalDate.now());
            customerRepository.save(customer);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Employee not found"+e.getMessage());
        }
    }
}
