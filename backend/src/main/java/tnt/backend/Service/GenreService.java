package tnt.backend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.GenreDTO;
import tnt.backend.Entity.Genre;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.GenreRepository;

import java.time.LocalDate;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<GenreDTO> getAll(Pageable pageable) throws BadRequestException {
        try{
            Page<GenreDTO> lst = genreRepository.findAll(pageable).map(genre -> new GenreDTO(genre));
            return lst;
        }catch (Exception e){
            throw new BadRequestException("Get all Genre is failed"+e.getMessage());
        }
    }

    public GenreDTO getById(int id) throws BadRequestException {
        try {

            Genre genre = genreRepository.findById(id).orElseThrow(() ->new NotFoundException("Genre is not found"));
            GenreDTO genreDTO = new GenreDTO(genre);
            return genreDTO;

        }catch (Exception e){
            throw new BadRequestException("Get Genre is failed"+e.getMessage());
        }
    }

    public boolean save(GenreDTO genreDTO) throws BadRequestException {
        try {
            Genre genre = modelMapper.map(genreDTO, Genre.class);
            //Generate genre code
            String genreCode;
            do {
                genreCode = CommonUtils.generateCode(genreDTO.getGenreName());
            }while(genreRepository.existsByGenreCode(genreCode));
            genre.setGenreCode(genreCode);
            genre.setCreatedDate(LocalDate.now());
            genreRepository.save(genre);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Save Genre is failed"+e.getMessage());
        }
    }

    public boolean update(GenreDTO genreDTO,int id) throws BadRequestException {
        try{
            Genre genre = genreRepository.findById(id).orElseThrow(() ->new NotFoundException("Genre is not found"));
            modelMapper.map(genreDTO,genre);
            genre.setModifiedDate(LocalDate.now());
            genreRepository.save(genre);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Update Genre is failed"+e.getMessage());
        }
    }

    public boolean delete(int id) throws BadRequestException {
        try{
            Genre genre =  genreRepository.findById(id).orElseThrow(() ->new NotFoundException("Genre is not found"));
            genreRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Delete Genre is failed"+e.getMessage());
        }
    }
}
