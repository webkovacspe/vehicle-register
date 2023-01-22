package hu.kovacspeterzoltan.bootcamp.vehicleregister.dao;

import org.json.JSONException;

public interface VehicleRegisterInteractorInterface {
    void saveVehicle(String vehicleJsonString);
    void getVehicleByRegisterNumber(String registrationNumber) throws JSONException;
}