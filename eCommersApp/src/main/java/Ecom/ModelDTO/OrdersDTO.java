package Ecom.ModelDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrdersDTO {

	
	private Integer userId;
    private Integer paymentId;
    private BigDecimal totalAmount;
    private Date orderDate;
    private List<OrderItemDTO> orderItems;
}
