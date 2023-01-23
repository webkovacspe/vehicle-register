package hu.kovacspeterzoltan.bootcamp.vehicleregister.storage;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;

public interface VehicleRegisterStorageInterface {
    void saveVehicle(VehicleEntity vehicle);
    VehicleEntity findVehicle(String registrationNumber);
}