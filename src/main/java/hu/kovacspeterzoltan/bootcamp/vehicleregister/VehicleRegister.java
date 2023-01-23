package hu.kovacspeterzoltan.bootcamp.vehicleregister;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterAPI;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenter;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStorage;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.VehicleParser;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.validator.VehicleRegisterValidator;
import org.json.JSONException;
import org.json.JSONObject;

public class VehicleRegister implements VehicleRegisterAPI {
    private VehicleRegisterStorage storage;
    private VehicleRegisterPresenter presenter;
    private final VehicleParser parser;
    private final VehicleRegisterValidator newVehicleValidator;
    public VehicleRegister() {
        parser = new VehicleParser();
        newVehicleValidator = new VehicleRegisterValidator();
    }
    public void setStorageImp(VehicleRegisterStorage storageImp) {
        storage = storageImp;
    }
    public void setPresenterImp(VehicleRegisterPresenter presenterImp) {
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
    }
}