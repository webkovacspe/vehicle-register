package hu.kovacspeterzoltan.bootcamp.vehicleregister.parser;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.dto.FindRequestDTO;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.dto.FindRequestPlugInDTO;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.validator.InvalidJsonStringException;
import org.json.JSONException;
import org.json.JSONObject;

public class VehicleParser {
    public static final String REGISTRATION_NUMBER = "registrationNumber";
    public static final String ERROR_MESSAGE = "errorMessage";
    JSONObject jsonObject;
    public VehicleEntity jsonStringToVehicleEntity(String vehicleJsonString) {
        stringToJSONObject(vehicleJsonString);
        return getVehicleEntity();
    }
    public FindRequestDTO jsonStringToRequestDTO(String jsonString) {
        FindRequestDTO dto = new FindRequestDTO();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            dto.registrationNumber = jsonObject.getString(REGISTRATION_NUMBER).toUpperCase();
        } catch (JSONException e) {
            throw new InvalidJsonStringException();
        }
        return dto;
    }
    public FindRequestPlugInDTO jsonStringToRequestPlugInDTO(String jsonString) {
        FindRequestPlugInDTO dto = new FindRequestPlugInDTO();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            dto.registrationNumber = jsonObject.getString(REGISTRATION_NUMBER).toUpperCase();
        } catch (JSONException e) {
            throw new InvalidJsonStringException();
        }
        return dto;
    }
    public JSONObject jsonStringToRegistrationNumber(String vehicleJsonString) {
        stringToJSONObject(vehicleJsonString);
        return jsonObject;
    }
    public String vehicleEntityToJsonString(VehicleEntity vehicle) {
        vehicleEntityToJson(vehicle);
        return jsonObject.toString();
    }

    public String vehicleEntityToResponsePlugInJsonString(VehicleEntity vehicleEntity) {
        String responseJsonString;
        if (vehicleEntity == null) {
            responseJsonString = getErrorMessageJsonString("A keresett rendszám nincs rögzítve");
        } else {
            responseJsonString = vehicleEntityToJsonString(vehicleEntity);
        }
        return responseJsonString;
    }
    public String getErrorMessageJsonString(String errorMessage) {
        JSONObject j = new JSONObject();
        try {
            j.put(ERROR_MESSAGE, errorMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j.toString();
    }
    private void vehicleEntityToJson(VehicleEntity vehicle) {
        try {
            jsonObject = new JSONObject();
            jsonObject.put(REGISTRATION_NUMBER, vehicle.registrationNumber);
            jsonObject.put("vehicleRegister", vehicle.vehicleRegister);
            jsonObject.put("make", vehicle.make);
            jsonObject.put("model", vehicle.model);
            jsonObject.put("numberOfSeats", vehicle.numberOfSeats);
            jsonObject.put("vehicleType", vehicle.vehicleType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void stringToJSONObject(String vehicleJsonString) {
        try {
            jsonObject = new JSONObject(vehicleJsonString);
        } catch (JSONException e) {
            throw new InvalidJsonStringException();
        }
    }
    private VehicleEntity getVehicleEntity() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.registrationNumber = getStringValue(REGISTRATION_NUMBER);
        vehicle.vehicleRegister = getStringValue("vehicleRegister");
        vehicle.make = getStringValue("make");
        vehicle.model = getStringValue("model");
        vehicle.numberOfSeats = getIntValue("numberOfSeats");
        vehicle.vehicleType = getStringValue("vehicleType");
        vehicle.registrationNumber = vehicle.registrationNumber.toUpperCase();
        return vehicle;
    }
    private String getStringValue(String key) {
        String value = null;
        try {
            if (jsonObject.has(key)) {
                value = jsonObject.getString(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }
    private int getIntValue(String key) {
        int value = 0;
        try {
            if (jsonObject.has(key)) {
                value = jsonObject.getInt(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }
}