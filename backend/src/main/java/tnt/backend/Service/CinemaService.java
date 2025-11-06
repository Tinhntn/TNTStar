package tnt.backend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.CinemaDTO;
import tnt.backend.Entity.Cinema;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Voucher;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.CinemaRepository;
import tnt.backend.Repository.EmployeeRepository;

import java.time.LocalDate;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<CinemaDTO> findAll(Pageable pageable) throws BadRequestException {

        try {
            Page<CinemaDTO> lstCinema = cinemaRepository.findAll(pageable).map(c -> new CinemaDTO(c));
            return lstCinema;
        }catch (Exception e){
            throw new BadRequestException("Not found Cinema "+e.getMessage());
        }
    }

    public CinemaDTO findById(Integer id) throws BadRequestException {
        try{
            CinemaDTO cinemaDTO = cinemaRepository.findById(id).map(c -> new CinemaDTO(c)).orElseThrow(() -> new NotFoundException("Cinema not found"));
            return cinemaDTO;
        }catch (Exception e){
            throw new BadRequestException("Not found Cinema "+e.getMessage());
        }
    }

    public boolean save(CinemaDTO cinemaDTO) throws BadRequestException {
        try{
            modelMapper.typeMap(CinemaDTO.class, Cinema.class).addMappings(mapper -> {
                mapper.skip(Cinema::setId);
                mapper.skip(Cinema::setCreator);
                mapper.skip(Cinema::setEditor);
            });
            Employee employee = employeeRepository.findById(cinemaDTO.getIdCreator()).orElseThrow(() -> new NotFoundException("Employee not found"));
            Cinema cinema = modelMapper.map(cinemaDTO, Cinema.class);
            String cinemaCode ;
            do {
                cinemaCode = CommonUtils.generateCode(cinemaDTO.getCinemaName());
            }while (cinemaRepository.existsByCinemaCode(cinemaCode));
            cinema.setCreatedDate(LocalDate.now());
            cinema.setCreator(employee);
            cinemaRepository.save(cinema);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Create cinema is failed "+e.getMessage());
        }
    }

    public boolean update(CinemaDTO cinemaDTO, Integer id) throws BadRequestException {
        try {
            Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new NotFoundException("Cinema not found"));
            modelMapper.typeMap(CinemaDTO.class, Cinema.class).addMappings(mapper -> {
                mapper.skip(Cinema::setId);
                mapper.skip(Cinema::setCreator);
                mapper.skip(Cinema::setEditor);
            });
            modelMapper.map(cinemaDTO, cinema);
            Employee employee = employeeRepository.findById(cinemaDTO.getIdEditor()).orElseThrow(() -> new NotFoundException("Employee not found"));
            cinema.setModifiedDate(LocalDate.now());
            cinema.setEditor(employee);
            cinemaRepository.save(cinema);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Update cinema is failed "+e.getMessage());
        }
    }
    public boolean delete(Integer id) throws NotFoundException {
        try {
            Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new NotFoundException("Cinema not found"));
            cinemaRepository.delete(cinema);
            return true;
        }catch (Exception e){
            throw new NotFoundException("Delete cinema is failed "+e.getMessage());
        }
    }
}
