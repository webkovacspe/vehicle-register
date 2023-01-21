import hu.kovacspeterzoltan.bootcamp.vehicleregister.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.InvalidJsonStringException;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.parser.VehicleParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParserTest {
    VehicleParser parser;
    @BeforeEach
    void setUp() {
        parser = new VehicleParser();
    }
    @Test
    void testInvalidJsonStringException() {
        InvalidJsonStringException exception = Assertions.assertThrows(InvalidJsonStringException.class, () -> {
            String jsonString = """
            {"alma": "dfdf}
            """;
            parser.jsonStringToVehicleEntity(jsonString);
        });
    }
    @Test
    void testJsonStringParseIfJsonStringValid() {
        String jsonString = """
            {
              "registrationNumber":"AA:BB-123",
              "vehicleRegister":"Alma",
              "vehicle":"vehicle",
              "make":"Opel",
              "model":"Astra",
              "numberOfSeats":5,
              "vehicleType":"m1"
            }
            """;
        VehicleEntity m1 = parser.jsonStringToVehicleEntity(jsonString);
        Assertions.assertEquals("AA:BB-123", m1.registrationNumber);
        Assertions.assertEquals("Alma", m1.vehicleRegister);
        Assertions.assertEquals("vehicle", m1.vehicle);
        Assertions.assertEquals("Opel", m1.make);
        Assertions.assertEquals("Astra", m1.model);
        Assertions.assertEquals(5, m1.numberOfSeats);
        Assertions.assertEquals("m1", m1.vehicleType);
    }
}