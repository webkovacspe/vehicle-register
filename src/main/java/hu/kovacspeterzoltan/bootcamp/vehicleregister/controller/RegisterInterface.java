package hu.kovacspeterzoltan.bootcamp.vehicleregister.controller;

public interface RegisterInterface {
    void saveVehicle(String vehicle);
    void getVehicleByRegisterNumber(String registerNumber);
}