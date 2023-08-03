package Ecom.ServiceImpl;

import org.springframework.stereotype.Service;

import Ecom.Exception.ShippingException;
import Ecom.Model.Orders;
import Ecom.Model.Shipper;
import Ecom.Model.ShippingDetails;
import Ecom.ModelDTO.ShippingDTO;
import Ecom.Repository.OrderRepository;
import Ecom.Repository.ShipperRepository;
import Ecom.Repository.ShippingRepository;
import Ecom.Service.ShippingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

	private final ShippingRepository shippingRepository;

	private final OrderRepository orderRepository;

	private final ShipperRepository shipperRepository;

	@Override
	public ShippingDetails setShippingDetails(Integer orderId, Integer shipperId, ShippingDetails shippingDetails)
	        throws ShippingException {
	    if (shippingDetails == null)
	        throw new ShippingException("ShippingDetails cannot be null");

	    Orders existingOrder = orderRepository.findById(orderId)
	            .orElseThrow(() -> new ShippingException("Order not found"));

	    Shipper existingShipper = shipperRepository.findById(shipperId)
	            .orElseThrow(() -> new ShippingException("Shipper not found"));

	    // Save the ShippingDetails entity first
	    shippingDetails.setOrders(existingOrder);
	    shippingDetails.setShipper(existingShipper);
	    shippingDetails = shippingRepository.save(shippingDetails);

	    // Now update the Orders entity with the saved ShippingDetails
	    existingOrder.setShippingDetails(shippingDetails);
	    orderRepository.save(existingOrder);

	    return shippingDetails;
	}


	@Override
	public ShippingDetails updateShippingAddress(Integer shippingId, ShippingDTO shippingDTO) throws ShippingException {
		ShippingDetails existing = shippingRepository.findById(shippingId)
				.orElseThrow(() -> new ShippingException("Shipping detail not found"));

		existing.setState(shippingDTO.getState());
		existing.setAddress(shippingDTO.getAddress());
		existing.setCity(shippingDTO.getCity());
		existing.setPostalCode(shippingDTO.getPostalCode());
		return shippingRepository.save(existing);
	}

	@Override
	public void deleteShippingDetails(Integer shippingId) throws ShippingException {
		ShippingDetails existing = shippingRepository.findById(shippingId)
				.orElseThrow(() -> new ShippingException("Shipping detail not found"));

		shippingRepository.delete(existing);

	}

}
