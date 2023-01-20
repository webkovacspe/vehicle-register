package hu.kovacspeterzoltan.bootcamp.vehicleregister;

public interface VehicleStorageInterface {
    void saveVehicle(VehicleEntity vehicle);

    VehicleEntity getVehicle(String registrationNumber);
}