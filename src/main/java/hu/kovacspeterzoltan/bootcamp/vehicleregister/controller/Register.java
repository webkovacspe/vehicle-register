package hu.kovacspeterzoltan.bootcamp.vehicleregister.controller;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.VehicleParser;

public class Register implements RegisterInterface {
    VehicleStorageInterface storage;
    @Override
    public void saveVehicle(String vehicleJsonString) {
        VehicleParser parser = new VehicleParser();

        VehicleEntity vehicle = parser.jsonStringToVehicleEntity(vehicleJsonString);
        storage.saveVehicle(vehicle);
    }

    @Override
    public void getVehicleByRegisterNumber(String registerNumber) {

    }
}
