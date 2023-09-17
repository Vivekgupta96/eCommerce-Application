package Ecom.ModelDTO;



import lombok.Data;

@Data
public class OrdersDTO {

    private Integer orderId;
    private String status;
    private String orderDate;
    private Double orderAmount;
    private String paymentStatus;

}
