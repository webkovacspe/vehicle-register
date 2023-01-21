package hu.kovacspeterzoltan.bootcamp.vehicleregister.parser;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import org.json.JSONException;
import org.json.JSONObject;

public class VehicleParser {
    JSONObject jsonObject;
    public VehicleEntity jsonStringToVehicleEntity(String vehicleJsonString) {
        stringToJSONObject(vehicleJsonString);
        return getVehicleEntity();
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
        vehicle.vehicle = getStringValue("vehicle");
        vehicle.make = getStringValue("make");
        vehicle.model = getStringValue("model");
        vehicle.numberOfSeats = getIntValue("numberOfSeats");
        vehicle.vehicleType = getStringValue("vehicleType");
        return vehicle;
    }
    private String getStringValue(String key) {
        String value = null;
        try {
            if (jsonObject.has(key)) {
                value = jsonObject.getString(key);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
        return value;
    }
}