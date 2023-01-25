package hu.kovacspeterzoltan.bootcamp.vehicleregister.storage;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;

public interface VehicleRegisterStoragePlugInInterface {
    VehicleEntity findVehiclePlugIn(String registrationNumber);
}