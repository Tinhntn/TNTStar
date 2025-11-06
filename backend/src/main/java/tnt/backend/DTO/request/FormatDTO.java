package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Format;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormatDTO {
    @NotEmpty(message = "Format code can't be empty")
    @Size(min = 1, max = 255, message = "Food code exceed the allowed number of characters")
    private String formatCode;

    @NotEmpty(message = "Format name can't be empty")
    @Size(min = 1, max = 255, message = "Format name exceed the allowed number of characters")
    private String formatName;

    private boolean status;

    public FormatDTO(Format format) {
        this.formatCode = format.getFormatCode();
        this.formatName = format.getFormatName();
        this.status = format.isStatus();
    }
}
