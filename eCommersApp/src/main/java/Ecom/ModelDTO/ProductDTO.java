package Ecom.ModelDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    @NotNull(message = "Product name is Mandatory ,can Not Be Null")
    @NotBlank(message = "Product name is Mandatory")
    private String name;

    @NotNull(message = "Product Image is Mandatory ,can Not Be Null")
    @NotBlank(message = "Product Image is Mandatory")
    private String imageUrl;

    @NotNull(message = "Product description is Mandatory ,can Not Be Null")
    @NotBlank(message = "Product description is Mandatory")
    @Size(min = 10, max = 50)
    private String description;

    @NotNull(message = "Product price is Mandatory ,can Not Be Null")
    private Double price;

    @NotNull(message = "Product category_name is Mandatory ,can Not Be Null")
    @NotBlank(message = "Product category_name is Mandatory")
    private String category;

}
