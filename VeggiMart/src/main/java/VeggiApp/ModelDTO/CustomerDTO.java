package VeggiApp.ModelDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotNull(message = "Password is mandatory")
    private String oldpassword;
    @NotNull(message = "Password is mandatory")
    private String password;


}
