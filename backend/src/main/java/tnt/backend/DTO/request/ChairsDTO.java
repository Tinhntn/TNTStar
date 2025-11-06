package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Chairs;
import tnt.backend.Entity.Room;
import tnt.backend.Entity.Row;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChairsDTO {

    @NotEmpty(message = "Room can't be empty")
    private int idRoom;

    @NotEmpty(message = "Row can't be empty")
    private int idRow;

    @NotEmpty(message = "Chair name can't empty")
    @Size(min=1, max = 20,message = "Chair name exceed the allowed number of characters")
    private String chairName;

    @NotEmpty(message = "Creator can't be empty")
    @Min(value = 0,message = "Invalid id creator!")
    private int idCreator;

    @Min(value = 0,message = "Invalide id editor")
    private int idEditor;

    private boolean status;

    public ChairsDTO(Chairs chairs) {
        this.idRoom = chairs.getRoom().getId();
        this.idRow = chairs.getRow().getId();
        this.chairName = chairs.getChairName();
        this.idCreator = chairs.getCreator().getId();
        this.idEditor = chairs.getEditor().getId();
        this.status = chairs.isStatus();
    }
    public ChairsDTO loadChairDTO(Chairs chairs) {
        ChairsDTO chairsDTO = new ChairsDTO();
        chairsDTO.setIdRoom(chairs.getId());
        chairsDTO.setIdRow(chairs.getId());
        chairsDTO.setChairName(chairs.getChairName());
        chairsDTO.setStatus(chairs.isStatus());
        return chairsDTO;
    }

}
