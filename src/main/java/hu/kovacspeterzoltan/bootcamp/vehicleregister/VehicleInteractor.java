package hu.kovacspeterzoltan.bootcamp.vehicleregister;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterAPI;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenterInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.VehicleParser;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.validator.VehicleRegisterValidator;
import org.json.JSONException;
import org.json.JSONObject;

public class VehicleInteractor implements VehicleRegisterAPI {
    private VehicleRegisterStorageInterface storage;
    private VehicleRegisterPresenterInterface presenter;
    private final VehicleParser parser;
    private final VehicleRegisterValidator newVehicleValidator;
    public VehicleInteractor() {
        parser = new VehicleParser();
        newVehicleValidator = new VehicleRegisterValidator();
    }
    public void setStorageImp(VehicleRegisterStorageInterface storageImp) {
        storage = storageImp;
    }
    public void setPresenterImp(VehicleRegisterPresenterInterface presenterImp) {
        presenter = presenterImp;
    }
    @Override
    public void saveVehicle(String vehicleJsonString) {
        newVehicleValidator.jsonValidator(vehicleJsonString);

        VehicleEntity vehicle = parser.jsonStringToVehicleEntity(vehicleJsonString);
        storage.saveVehicle(vehicle);

        presenter.displayMessage("Sikeres mentés");
    }
    @Override
    public void findVehicleByRegistrationNumber(String vehicleJsonString) {
        try {
            JSONObject jsonObject = parser.jsonStringToRegistrationNumber(vehicleJsonString);
            VehicleEntity vehicle = storage.findVehicle(jsonObject.getString("registrationNumber").toUpperCase());
            String request;
            if (vehicle == null) {
//            presenter.displayMessage("A keresett rendszám nincs rögzítve");
                JSONObject error = new JSONObject();
                error.put("errorMessage", "A keresett rendszám nincs rögzítve");
                request = error.toString();
            } else {
                request = parser.vehicleEntityToJsonString(vehicle);
            }
            presenter.displayJsonRequest(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}