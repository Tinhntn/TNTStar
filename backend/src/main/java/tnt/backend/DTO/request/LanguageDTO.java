package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Language;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDTO {
    @NotEmpty(message = "Food code can't be empty")
    @Size(min = 1, max = 255, message = "Food code exceed the allowed number of characters")
    private String languageCode;

    @NotEmpty(message = "Food name can't be empty")
    @Size(min = 1, max = 255, message = "Food name exceed the allowed number of characters")
    private String languageName;

    @NotEmpty(message = "Creator can't be empty")
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    private boolean status;

    public LanguageDTO(Language language){
        this.languageCode = language.getLanguageCode();
        this.languageName = language.getLanguageName();
        this.createdDate = language.getCreatedDate();
        this.modifiedDate = language.getModifiedDate();
        this.status = language.isStatus();
    }
}
