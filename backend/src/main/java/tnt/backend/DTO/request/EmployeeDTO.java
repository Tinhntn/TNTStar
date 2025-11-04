package tnt.backend.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tnt.backend.Entity.Employee;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "Position can't be empty")
    @Min(value = 0, message = "Invalid Position id")
    private int idPosition;

    @NotEmpty(message = "Staff code can't be empty")
    @Size(min = 1, max = 50, message = "Cinema code exceed the allowed number of characters")
    private String staffCode;

    @NotEmpty(message = "Firstname can't be empty")
    @Size(min=0, max = 255, message = "Firstname exceed the allowed number of characters")
    private String firstName;

    @NotEmpty(message = "Lastname can't be empty")
    @Size(min=0, max = 255, message = "Lastname exceed the allowed number of characters")
    private String lastName;

    @NotNull(message = "Date of birth can't be empty")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Phone number can't be empty")
    @Pattern(regexp = "^(0[3,5,6,7,8,9])[0-9]{8}$",message = "Invalid phone number!")
    private String phoneNumber;

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Password can't be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$",
            message = "Invalid password, password must be at least 8 characters, including uppercase, lowercase," +
                    " number and special characters")
    private String password;

    @NotNull(message = " Create date can't be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    private boolean status;

    public EmployeeDTO(Employee employee) {
        this.idPosition = employee.getId();
        this.staffCode = employee.getStaffCode();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.dateOfBirth = employee.getDateOfBirth();
        this.phoneNumber = employee.getPhoneNumber();
        this.email = employee.getEmail();
        this.password = employee.getPassword();
        this.createdDate = employee.getCreatedDate();
        this.modifiedDate = employee.getModifiedDate();
        this.status = employee.isStatus();
    }
}
