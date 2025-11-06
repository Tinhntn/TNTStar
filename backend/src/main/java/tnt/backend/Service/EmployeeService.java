package tnt.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.EmployeeDTO;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Position;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.EmployeeRepository;
import tnt.backend.Repository.PositionRepositoy;

import java.time.LocalDate;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionRepositoy positionRepositoy;
    public Page<EmployeeDTO> getEmployees(Pageable pageable) throws BadRequestException {
        try{
            Page<EmployeeDTO> employeeDTOPage = employeeRepository.findAll(pageable).map(employee -> new EmployeeDTO(employee));
            return employeeDTOPage;
        }catch(Exception e){
            throw new BadRequestException("Employee not found "+e.getMessage());
        }

    }

    public EmployeeDTO findById(Integer id) throws BadRequestException {
        try{
            return employeeRepository.findById(id).map(employee -> new EmployeeDTO(employee)).orElseThrow(() -> new NotFoundException("Employee not found"));
        }catch (Exception e){
            throw new BadRequestException("Employee not found");
        }
    }

    public boolean save(EmployeeDTO employeeDTO) throws BadRequestException {
        try{
            Position position = positionRepositoy.findById(employeeDTO.getIdPosition()).orElseThrow(() -> new NotFoundException("Position not found"));
            Employee employee = new Employee(employeeDTO,position);
            employee.setCreatedDate(LocalDate.now());
            //generate Employee code
            String staffCode;
            do {
                staffCode = CommonUtils.generateCode(employeeDTO.getFirstName()+" "+employee.getLastName());
            }while (employeeRepository.existsEmployeeByStaffCode(staffCode));
            employee.setStaffCode(staffCode);
            employeeRepository.save(employee);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Create falied employee"+e.getMessage());
        }
    }

    public boolean delete(Integer id) throws BadRequestException {
        try{
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
            employeeRepository.delete(employee);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Employee not found"+e.getMessage());
        }
    }

    public boolean update(EmployeeDTO employeeDTO, Integer id) throws BadRequestException {
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
            Position position = positionRepositoy.findById(employeeDTO.getIdPosition()).orElseThrow(() -> new NotFoundException("Position not found"));
            employee.setPosition(position);
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setDateOfBirth(employeeDTO.getDateOfBirth());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPassword(employeeDTO.getPassword());
            employee.setModifiedDate(LocalDate.now());
            employee.setStaffCode(employee.getStaffCode());
            employeeRepository.save(employee);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Employee not found"+e.getMessage());
        }
    }
}
