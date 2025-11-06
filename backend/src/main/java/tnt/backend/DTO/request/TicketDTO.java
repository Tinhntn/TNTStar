package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Chairs;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Ticket;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    @NotEmpty(message = "Ticket code can't be empty")
    @Size(min = 1, max = 50, message = "Ticket code exceed the allowed number of characters")
    private  String ticketCode;

    @NotEmpty(message = "Chair can't be empty")
    @Min(value = 0, message = "Invalid Chair id!")
    private int idChairs;

    @Min(value = 0, message = "Invalid price")
    private double price;

    @NotEmpty(message = "Creator can't be empty")
    @Min(value = 0, message = "Invalid creator id!")
    private int idCreator;

    @Min(value = 0, message = "Invalid creator id!")
    private int idEditor;

    private boolean status;

    public TicketDTO(Ticket ticket) {
        this.ticketCode = ticket.getTicketCode();
        this.idChairs = ticket.getChairs().getId();
        this.price = ticket.getPrice();
        this.idCreator= ticket.getCreator().getId();
        this.idEditor= ticket.getEditor().getId();
        this.status = ticket.isStatus();
    }
}
