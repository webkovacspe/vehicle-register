package hu.kovacspeterzoltan.bootcamp.vehicleregister.api;

import org.json.JSONException;

public interface VehicleRegisterAPI {
    void saveVehicle(String vehicleJsonString);
    void findVehicleByRegistrationNumber(String registrationNumber);
}