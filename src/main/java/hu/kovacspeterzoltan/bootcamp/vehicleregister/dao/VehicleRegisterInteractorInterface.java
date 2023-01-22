package hu.kovacspeterzoltan.bootcamp.vehicleregister.dao;

public interface VehicleRegisterInteractorInterface {
    void saveVehicle(String vehicleJsonString);
    void getVehicleByRegisterNumber(String registrationNumber);
}