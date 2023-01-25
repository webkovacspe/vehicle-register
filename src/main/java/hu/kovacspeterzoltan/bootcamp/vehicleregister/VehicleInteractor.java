package hu.kovacspeterzoltan.bootcamp.vehicleregister;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.*;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dto.FindRequestDTO;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dto.FindRequestPlugInDTO;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStorageInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.VehicleParser;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.storage.VehicleRegisterStoragePlugInInterface;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.validator.VehicleRegisterValidator;

public class VehicleInteractor implements VehicleRegisterSaveVehicleAPI, VehicleRegisterFindAPI, VehicleRegisterFindPlugInAPI {
    private VehicleRegisterStorageInterface storage;
    private VehicleRegisterStoragePlugInInterface storagePlugIn;
    private VehicleRegisterPresenterInterface presenter;
    private VehicleRegisterPresenterPlugInInterface presenterPlugIn;
    private final VehicleParser parser;
    private final VehicleRegisterValidator newVehicleValidator;
    public VehicleInteractor() {
        parser = new VehicleParser();
        newVehicleValidator = new VehicleRegisterValidator();
    }
    public void setStorageImp(VehicleRegisterStorageInterface storageImp) {
        storage = storageImp;
    }
    public void setStoragePlugInImp(VehicleRegisterStoragePlugInInterface storagePlugInImp) {
        storagePlugIn = storagePlugInImp;
    }
    public void setPresenterImp(VehicleRegisterPresenterInterface presenterImp) {
        presenter = presenterImp;
    }
    public void setPresenterPlugInImp(VehicleRegisterPresenterPlugInInterface presenterPlugInImp) {
        presenterPlugIn = presenterPlugInImp;
    }
    @Override
    public void saveVehicle(String vehicleJsonString) {
        newVehicleValidator.jsonValidator(vehicleJsonString);

        VehicleEntity vehicle = parser.jsonStringToVehicleEntity(vehicleJsonString);
        storage.saveVehicle(vehicle);

        presenter.displayMessage("Sikeres mentés");
    }
    @Override
    public void findVehicleByRegistrationNumber(String requestJsonString) {
        newVehicleValidator.jsonValidator(requestJsonString);
        FindRequestDTO requestDTO = parser.jsonStringToRequestDTO(requestJsonString);
        VehicleEntity vehicleEntity = storage.findVehicle(requestDTO.registrationNumber);
        String request = getResponseJsonString(vehicleEntity);
        presenter.displayJsonResponse(request);
    }
    private String getResponseJsonString(VehicleEntity vehicleEntity) {
        String responseJsonString;
        if (vehicleEntity == null) {
            responseJsonString = parser.getErrorMessageJsonString("A keresett rendszám nincs rögzítve");
        } else {
            responseJsonString = parser.vehicleEntityToJsonString(vehicleEntity);
        }
        return responseJsonString;
    }
    @Override
    public void findVehicleByRegistrationNumberPlugIn(String requestJsonString) {
        newVehicleValidator.jsonValidator(requestJsonString);
        FindRequestPlugInDTO requestDTO = parser.jsonStringToRequestPlugInDTO(requestJsonString);
        VehicleEntity vehicleEntity = storagePlugIn.findVehiclePlugIn(requestDTO.registrationNumber);
        String response = parser.vehicleEntityToResponsePlugInJsonString(vehicleEntity);
        presenterPlugIn.displayJsonResponsePlugIn(response);
    }
}