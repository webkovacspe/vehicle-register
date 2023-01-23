package hu.kovacspeterzoltan.bootcamp.vehicleregister.validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewVehicleRegistrationValidator {
    private static final String REGISTRATIONNUMBER = "registrationNumber";
    JSONObject jsonObject;
    public void jsonValidator(String jsonString) {
        stringIsValidJson(jsonString);
        structureIsValid();
    }
    private void stringIsValidJson(String jsonString) {
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            throw new InvalidJsonStringException();
        }
    }
    private void structureIsValid() {
        if (!registrationNumberIsValid()) {
            throw new InvalidJsonStructureException();
        }
    }
    private boolean registrationNumberIsValid() {
        try {
            Pattern pattern = Pattern.compile("^[a-zA-Z]{2}:[a-zA-Z]{2}-[0-9]{3}$");
            return (jsonObject.has(REGISTRATIONNUMBER) &&
                    !jsonObject.getString(REGISTRATIONNUMBER).equals("") &&
                    pattern.matcher(jsonObject.getString(REGISTRATIONNUMBER)).find());
        } catch (JSONException e) {
            throw new InvalidJsonStructureException();
        }
    }
}