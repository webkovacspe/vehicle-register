package hu.kovacspeterzoltan.bootcamp.vehicleregister.controller;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterInteractorInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterPresenterInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.VehicleParser;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterInteractor implements VehicleRegisterInteractorInterface {
    private VehicleRegisterStorageInterface storage;
    private VehicleRegisterPresenterInterface presenter;
    private final VehicleParser parser;
    public RegisterInteractor() {
        parser = new VehicleParser();
    }
    public void setStorageImp(VehicleRegisterStorageInterface storageImp) {
        storage = storageImp;
    }
    public void setPresenterImp(VehicleRegisterPresenterInterface presenterImp) {
        presenter = presenterImp;
    }
    @Override
    public void saveVehicle(String vehicleJsonString) {
        VehicleEntity vehicle = parser.jsonStringToVehicleEntity(vehicleJsonString);
        storage.saveVehicle(vehicle);

        presenter.displayMessage("Sikeres mentés");
    }
    @Override
    public void getVehicleByRegisterNumber(String vehicleJsonString) throws JSONException {
        JSONObject jsonObject = parser.jsonStringToRegistrationNumber(vehicleJsonString);
        VehicleEntity vehicle = storage.getVehicle(jsonObject.getString("registrationNumber"));
        if (vehicle == null) {
            presenter.displayMessage("A keresett rendszám nincs rögzítve");
        } else {
            presenter.displayJsonRequest(parser.vehicleEntityToJsonString(vehicle));
        }
    }
}