package hu.kovacspeterzoltan.bootcamp.vehicleregister.dao;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;

public interface VehicleRegisterStorageInterface {
    void saveVehicle(VehicleEntity vehicle);
    VehicleEntity getVehicle(String registrationNumber);
}