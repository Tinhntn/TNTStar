package tnt.backend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.DirectorDTO;
import tnt.backend.Entity.Director;
import tnt.backend.Entity.Employee;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.DirectorRepository;
import tnt.backend.Repository.EmployeeRepository;

@Service
public class DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<DirectorDTO> getDirectors(Pageable pageable) throws BadRequestException {
        try {
            Page<DirectorDTO> lst = directorRepository.findAll(pageable).map(director -> new DirectorDTO(director));
            return lst;
        }catch (Exception e){
            throw new BadRequestException("Director not found"+e.getMessage());
        }
    }

    public DirectorDTO getDirector(Integer id) throws BadRequestException {
        try{
            Director director = directorRepository.findById(id).orElseThrow(()->new NotFoundException("Director not found"));
            DirectorDTO directorDTO = new DirectorDTO(director);
            return directorDTO;
        }catch (Exception e){
            throw new BadRequestException("Director not found"+e.getMessage());
        }
    }

    public boolean createDirector(DirectorDTO directorDTO) throws BadRequestException {
        try{
            Director director = modelMapper.map(directorDTO, Director.class);
            directorRepository.save(director);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Director not found"+e.getMessage());
        }
    }

    public boolean updateDirector(Integer id, DirectorDTO directorDTO) throws BadRequestException {
        try{
            Director director = directorRepository.findById(id).orElseThrow(()->new NotFoundException("Director not found"));
            modelMapper.map(directorDTO, director);
            directorRepository.save(director);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Director not found"+e.getMessage());
        }
    }

    public boolean deleteDirector(Integer id) throws BadRequestException {
        try{
            directorRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Director not found"+e.getMessage());
        }
    }
}
