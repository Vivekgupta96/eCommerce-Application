package Ecom.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class ShippingServiceImpl implements ShippingService {

	@Autowired
	private ShippingRepository shippingRepository;

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ShipperRepository shipperRepository;
	
	@Override
	public ShippingDetails setShippingDetails(Integer orderId, Integer shipperId,ShippingDetails shippingDetails)
			throws ShippingException {
		if (shippingDetails == null)
			throw new ShippingException("Can be Null");
		
		Orders existingOrder = orderRepository.findById(orderId)
				.orElseThrow(() -> new ShippingException("Shipping detail not found"));
		
		Shipper existingShipper = shipperRepository.findById(orderId)
				.orElseThrow(() -> new ShippingException("Shipper detail not found"));
		
		existingShipper.getShippingDetails().add(shippingDetails);
		existingOrder.setShippingDetails(shippingDetails);
		shippingDetails.setOrders(existingOrder);
		orderRepository.save(existingOrder);
		return shippingRepository.save(shippingDetails);
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
