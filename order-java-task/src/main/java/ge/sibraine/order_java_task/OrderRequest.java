package ge.sibraine.order_java_task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    @NotNull(message = "User id cannot be null.")
    private Long userId;

    @NotBlank(message = "Product name cannot be blank.")
    private String product;

    @Positive(message = "Product quantity must be positive.")
    private Integer quantity;

    @Positive(message = "Product price must be positive.")
    private Long price;

}
