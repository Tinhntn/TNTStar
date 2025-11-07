package tnt.backend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.FormatDTO;
import tnt.backend.Entity.Food;
import tnt.backend.Entity.Format;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.FormatRepository;

import java.time.LocalDate;

@Service
public class FormatService {
    @Autowired
    private FormatRepository formatRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Page<FormatDTO> getAll(Pageable pageable) throws BadRequestException {
        try{

            Page<FormatDTO> lst = formatRepository.findAll(pageable).map(f -> new FormatDTO(f));
            return lst;
        }catch (Exception e){
            throw new BadRequestException("Format is not found "+e.getMessage());
        }

    }

    public  FormatDTO getById(int id) throws BadRequestException{
        try{
            FormatDTO formatDTO = formatRepository.findById(id).map(f -> new FormatDTO(f)).orElseThrow(() ->new NotFoundException("Format is not found"));
            return formatDTO;
        }catch (Exception e){
            throw new BadRequestException("Format is not found "+e.getMessage());
        }
    }

    public boolean save(FormatDTO dto) throws BadRequestException{
        try{
            Format format = modelMapper.map(dto, Format.class);
            format.setCreatedDate(LocalDate.now());
            formatRepository.save(format);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Create format is failed"+e.getMessage());
        }
    }

    public boolean update(FormatDTO dto, Integer id) throws BadRequestException{
        try{
            Format format = formatRepository.findById(id).orElseThrow(() ->new NotFoundException("Format is not found"));
            modelMapper.map(dto, format);
            format.setModifiedDate(LocalDate.now());
            formatRepository.save(format);
            return true;

        }catch (Exception e){
            throw new BadRequestException("Update format is failed"+e.getMessage());
        }

    }
    public boolean delete(Integer id) throws BadRequestException{
        try{
            Format format = formatRepository.findById(id).orElseThrow(() ->new NotFoundException("Format is not found"));
            formatRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Delete format is failed"+e.getMessage());
        }
    }
}
