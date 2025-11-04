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

@Service
public class PositionService {
    @Autowired
    private PositionRepositoy positionRepositoy;

    public Page<PositionDTO> getPositions(Pageable pageable) throws BadRequestException {
        try {
            Page<PositionDTO> lstPositions = positionRepositoy.findAll(pageable).map(Position->new PositionDTO());
            return lstPositions;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }

    public PositionDTO getPositionById(Integer id) throws BadRequestException {
        try{
            PositionDTO position = positionRepositoy.findById(id).map(Position->new PositionDTO()).orElseThrow(()->new NotFoundException("Position not found"));
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
            Position position = new Position(positionDTO.getPositionName(),positionDTO.getPositionCode(),positionDTO.getCreatedDate(),positionDTO.getModifiedDate(),positionDTO.isStatus());
            positionRepositoy.save(position);
            return true;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
