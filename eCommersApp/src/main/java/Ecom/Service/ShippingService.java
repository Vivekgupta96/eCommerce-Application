package Ecom.Service;

import Ecom.Exception.ShippingException;
import Ecom.Model.ShippingDetails;
import Ecom.ModelDTO.ShippingDTO;

public interface ShippingService {
	
  public ShippingDetails setShippingDetails(Integer orderId,Integer shipperId,ShippingDetails shippingDetails) throws ShippingException;
  
  public ShippingDetails updateShippingAddress(Integer shippingId,ShippingDTO shippingDTO)throws ShippingException;
  
  public void deleteShippingDetails(Integer shippingId)throws ShippingException;
  
  
}
