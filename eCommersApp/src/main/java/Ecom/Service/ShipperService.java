package Ecom.Service;

import java.util.List;

import Ecom.Exception.ShipperException;
import Ecom.Model.Shipper;

public interface ShipperService {

	public void deleteShipperById(Integer id) throws ShipperException;

	public Shipper saveShipper(Shipper shipper) throws ShipperException;

	public Shipper getShipperById(Integer id) throws ShipperException;

	public List<Shipper> getAllShippers() throws ShipperException;

}
