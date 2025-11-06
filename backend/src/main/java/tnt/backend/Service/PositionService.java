package tnt.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.PositionDTO;
import tnt.backend.Entity.Position;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.PositionRepositoy;

import java.time.LocalDate;

@Service
public class PositionService {
    @Autowired
    private PositionRepositoy positionRepositoy;

    public Page<PositionDTO> getPositions(Pageable pageable) throws BadRequestException {
        try {
            Page<PositionDTO> lstPositions = positionRepositoy.findAll(pageable).map(Position->new PositionDTO(Position));
            return lstPositions;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }

    public PositionDTO getPositionById(Integer id) throws BadRequestException {
        try{
            PositionDTO position = positionRepositoy.findById(id).map(Position->new PositionDTO(Position)).orElseThrow(()->new NotFoundException("Position not found"));
            return position;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean deletePositionById(Integer id) throws BadRequestException {
        try{
            Position position = positionRepositoy.findById(id).orElseThrow(()->new NotFoundException("Position not found"));
            positionRepositoy.delete(position);
            return true;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean savePosition(PositionDTO positionDTO) throws BadRequestException {
        try{
            String positionCode;
            do {
                positionCode = generateCodeActor(positionDTO.getPositionName());
            }while (positionRepositoy.existsByPositionCode(positionCode));
            Position position = new Position(positionCode,positionDTO.getPositionName(), LocalDate.now(),null,positionDTO.isStatus());
            positionRepositoy.save(position);
            return true;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public boolean updatePosition(PositionDTO positionDTO, Integer id) throws BadRequestException {
        try{
            Position position = positionRepositoy.findById(id).orElseThrow(()->new NotFoundException("Position not found"));
            position.setPositionName(positionDTO.getPositionName());
            position.setPositionCode(positionDTO.getPositionCode());
            position.setModifiedDate(LocalDate.now());
            position.setStatus(positionDTO.isStatus());
            positionRepositoy.save(position);
            return true;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }

    static String generateCodeActor(String positionName) {
        String pathName[] = positionName.trim().split("\\s+");
        String firtLetter = pathName[0].substring(0, 1).toUpperCase();
        String lastLetter = pathName[pathName.length - 1].substring(0, 1).toUpperCase();
        String initials = firtLetter + lastLetter;
        int numberRandom = (int) (Math.random() * 10000);
        String code = initials + numberRandom;
        return code;
    }}
