package hu.kovacspeterzoltan.bootcamp.vehicleregister.interactor;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterInteractorInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterPresenterInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dao.VehicleRegisterStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.VehicleParser;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.validator.NewVehicleRegistrationValidator;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterInteractor implements VehicleRegisterInteractorInterface {
    private VehicleRegisterStorageInterface storage;
    private VehicleRegisterPresenterInterface presenter;
    private final VehicleParser parser;
    private final NewVehicleRegistrationValidator newVehicleValidator;
    public RegisterInteractor() {
        parser = new VehicleParser();
        newVehicleValidator = new NewVehicleRegistrationValidator();
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
    public void findVehicleByRegistrationNumber(String vehicleJsonString) throws JSONException {
        JSONObject jsonObject = parser.jsonStringToRegistrationNumber(vehicleJsonString);
        VehicleEntity vehicle = storage.getVehicle(jsonObject.getString("registrationNumber"));
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
    }
}