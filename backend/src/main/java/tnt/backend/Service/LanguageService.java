package tnt.backend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.LanguageDTO;
import tnt.backend.Entity.Language;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.LanguageRepository;

import java.time.LocalDate;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<LanguageDTO> getLanguages(Pageable pageable) throws BadRequestException {
        try{
            Page<LanguageDTO> lst= languageRepository.findAll(pageable).map(l->modelMapper.map(l,LanguageDTO.class));
            return lst;
        }catch (Exception e){
            throw new BadRequestException("Get languages is error "+e.getMessage());
        }
    }

    public LanguageDTO getLanguageById(int id) throws BadRequestException {
        try{
            Language l = languageRepository.findById(id).orElseThrow(()-> new NotFoundException("Language not found"));
            return modelMapper.map(l,LanguageDTO.class);
        }catch (Exception e){
            throw new BadRequestException("Get language is error "+e.getMessage());
        }
    }

    public boolean saveLanguage(LanguageDTO languageDTO) throws BadRequestException {
        try{
            Language l = modelMapper.map(languageDTO,Language.class);

            String languageCode;
            do {
                languageCode = CommonUtils.generateCode(languageDTO.getLanguageName());
            }while(languageRepository.existsByLanguageCode(languageCode));
            l.setLanguageCode(languageCode);
            l.setCreatedDate(LocalDate.now());
            languageRepository.save(l);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Save language is error "+e.getMessage());
        }
    }

    public boolean updateLanguage(LanguageDTO languageDTO, Integer id) throws BadRequestException {
        try{
            Language l = languageRepository.findById(id).orElseThrow(()-> new NotFoundException("Language not found"));
            modelMapper.map(languageDTO,l);
            l.setModifiedDate(LocalDate.now());
            languageRepository.save(l);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Update language is error "+e.getMessage());
        }
    }

    public boolean deleteLanguage(Integer id) throws BadRequestException {
        try{
            Language l =  languageRepository.findById(id).orElseThrow(()-> new NotFoundException("Language not found"));
            languageRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Delete language is error "+e.getMessage());
        }
    }
}
