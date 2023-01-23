package hu.kovacspeterzoltan.bootcamp.vehicleregister.parser;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.validator.InvalidJsonStringException;
import org.json.JSONException;
import org.json.JSONObject;

public class VehicleParser {
    JSONObject jsonObject;
    public VehicleEntity jsonStringToVehicleEntity(String vehicleJsonString) {
        stringToJSONObject(vehicleJsonString);
        return getVehicleEntity();
    }
    public JSONObject jsonStringToRegistrationNumber(String vehicleJsonString) {
        stringToJSONObject(vehicleJsonString);
        return jsonObject;
    }
    public String vehicleEntityToJsonString(VehicleEntity vehicle) {
        vehicleEntityToJson(vehicle);
        return jsonObject.toString();
    }
    private void vehicleEntityToJson(VehicleEntity vehicle) {
        try {
            jsonObject = new JSONObject();
            jsonObject.put("registrationNumber", vehicle.registrationNumber);
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
        vehicle.registrationNumber = getStringValue("registrationNumber");
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