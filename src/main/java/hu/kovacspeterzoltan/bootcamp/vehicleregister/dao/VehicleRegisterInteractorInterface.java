package hu.kovacspeterzoltan.bootcamp.vehicleregister.dao;

import org.json.JSONException;

public interface VehicleRegisterInteractorInterface {
    void saveVehicle(String vehicleJsonString);
    void findVehicleByRegistrationNumber(String registrationNumber) throws JSONException;
}