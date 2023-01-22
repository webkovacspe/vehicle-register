package hu.kovacspeterzoltan.bootcamp.vehicleregister.controller;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterInteractorInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterPresenterInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.VehicleParser;

public class RegisterInteractor implements VehicleRegisterInteractorInterface {
    private VehicleRegisterStorageInterface storage;
    private VehicleRegisterPresenterInterface presenter;
    public void setStorageImp(VehicleRegisterStorageInterface storageImp) {
        storage = storageImp;
    }
    public void setPresenterImp(VehicleRegisterPresenterInterface presenterImp) {
        presenter = presenterImp;
    }
    @Override
    public void saveVehicle(String vehicleJsonString) {
        VehicleParser parser = new VehicleParser();

        VehicleEntity vehicle = parser.jsonStringToVehicleEntity(vehicleJsonString);
        storage.saveVehicle(vehicle);

        presenter.displaySaveMessage("Sikeres mentés");
    }

    @Override
    public void getVehicleByRegisterNumber(String registrationNumber) {

    }
}
