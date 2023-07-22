package Ecom.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Exception.ShipperException;
import Ecom.Model.Shipper;
import Ecom.Repository.ShipperRepository;
import Ecom.Repository.ShippingRepository;
import Ecom.Service.ShipperService;

@Service
public class ShipperServiceImpl implements ShipperService {

	private final ShipperRepository shipperRepository;
	
	@Autowired
	private ShippingRepository shippingRepository;

	@Autowired
	public ShipperServiceImpl(ShipperRepository shipperRepository) {
		this.shipperRepository = shipperRepository;
	}

	@Override
	public Shipper saveShipper(Shipper shipper) throws ShipperException {
		if (shipper == null) {
			throw new ShipperException("Shipper object cannot be null.");
		}
		return shipperRepository.save(shipper);
	}

	@Override
	public void deleteShipperById(Integer id) throws ShipperException {
		Optional<Shipper> optionalShipper = shipperRepository.findById(id);
		if (optionalShipper.isPresent()) {
			shipperRepository.deleteById(id);
		} else {
			throw new ShipperException("Shipper with ID " + id + " not found.");
		}
	}

	@Override
	public Shipper getShipperById(Integer id) throws ShipperException {
		return shipperRepository.findById(id)
				.orElseThrow(() -> new ShipperException("Shipper with ID " + id + " not found."));
	}

	@Override
	public List<Shipper> getAllShippers() throws ShipperException {
		List<Shipper> shippers = shipperRepository.findAll();
		if (shippers.isEmpty()) {
			throw new ShipperException("No shippers found.");
		}
		return shippers;
	}
}
