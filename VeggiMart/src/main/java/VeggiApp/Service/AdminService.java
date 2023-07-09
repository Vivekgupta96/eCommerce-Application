package VeggiApp.Service;

import VeggiApp.Exception.AdminException;
import VeggiApp.Model.Admin;

public interface AdminService {
    public Admin registerAdmin(Admin admin) throws AdminException;


}
