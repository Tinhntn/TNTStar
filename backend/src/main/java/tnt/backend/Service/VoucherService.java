package tnt.backend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.VoucherDTO;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Voucher;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.CustomerRepository;
import tnt.backend.Repository.EmployeeRepository;
import tnt.backend.Repository.VoucherRepository;

import java.time.LocalDate;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<VoucherDTO> findAllVouchers(Pageable pageable) throws BadRequestException {
        try {
            Page<VoucherDTO> lst = voucherRepository.findAll(pageable).map(Voucher -> new VoucherDTO(Voucher));
            return lst;
        } catch (Exception e) {
            throw new BadRequestException("Voucher not found"+e.getMessage());
        }
    }

    public VoucherDTO findById(Integer id) throws BadRequestException {
        try {
            VoucherDTO voucherDTO = voucherRepository.findById(id).map(voucher -> new VoucherDTO(voucher)).orElseThrow(() -> new NotFoundException("Voucher not found"));
            return voucherDTO;
        } catch (Exception e) {
            throw new BadRequestException("Voucher not found");
        }
    }

    public boolean save(VoucherDTO voucherDTO) throws BadRequestException {
        try {
            modelMapper.typeMap(VoucherDTO.class, Voucher.class).addMappings(mapper->{
                mapper.skip(Voucher::setId);
                mapper.skip(Voucher::setCreator);
                mapper.skip(Voucher::setEditor);
            });
            Voucher voucher = modelMapper.map(voucherDTO, Voucher.class);
            Employee employee = employeeRepository.findById(voucherDTO.getIdCreator()).orElseThrow(() -> new NotFoundException("Employee not found"));
            voucher.setCreatedDate(LocalDate.now());
            voucher.setCreator(employee);
            String voucherCode;
            do {
                voucherCode = CommonUtils.generateCode(voucherDTO.getVoucherName());
            } while (voucherRepository.existsByVoucherCode(voucherCode));
            voucher.setVoucherCode(voucherCode);
            voucherRepository.save(voucher);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("Create Voucher failed"+e.getMessage());
        }
    }

    public boolean delete(Integer id) throws BadRequestException {
        try {
            Voucher voucher = voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Voucher not found"));
            voucherRepository.delete(voucher);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("Delete Voucher failed");
        }
    }

    public boolean update(VoucherDTO voucherDTO, Integer id) throws BadRequestException {
        try {
            modelMapper.typeMap(VoucherDTO.class, Voucher.class).addMappings(mapper->{
                mapper.skip(Voucher::setId);
                mapper.skip(Voucher::setCreator);
                mapper.skip(Voucher::setEditor);
            });

            Voucher voucher = voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Voucher not found"));
            Employee employee = employeeRepository.findById(voucherDTO.getIdEditor()).orElseThrow(() -> new NotFoundException("Editor is not found"));

            modelMapper.map(voucherDTO, voucher);
            voucher.setEditor(employee);
            voucher.setModifiedDate(LocalDate.now());
            voucherRepository.save(voucher);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("Update Voucher failed");
        }
    }


}
