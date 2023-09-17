package Ecom.ModelDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "New password can not be Blank..")
    @NotNull(message = "New password Can not be Null..")
    @Size(min = 5,max = 10)
    private String newPassword;

}
