package hu.kovacspeterzoltan.bootcamp.vehicleregister.controller;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterInteractorInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.VehicleParser;

public class RegisterInteractor implements VehicleRegisterInteractorInterface {
    VehicleRegisterStorageInterface storage;
    public void setStorageImp(VehicleRegisterStorageInterface storageImp) {
        storage = storageImp;
    }
    @Override
    public void saveVehicle(String vehicleJsonString) {
        VehicleParser parser = new VehicleParser();

        VehicleEntity vehicle = parser.jsonStringToVehicleEntity(vehicleJsonString);
        storage.saveVehicle(vehicle);
    }

    @Override
    public void getVehicleByRegisterNumber(String registrationNumber) {

    }
}
