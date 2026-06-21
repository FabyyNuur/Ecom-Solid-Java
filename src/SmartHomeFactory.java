

import commands.Command;
import commands.LockDoorsCommand;
import commands.SetTemperatureCommand;
import commands.TurnOffAllCommand;
import commands.TurnOnLightCommand;
import controllers.LightController;
import controllers.SecurityController;
import controllers.ThermostatController;
import devices.IDevice;
import devices.LightDevice;
import devices.SecurityAlarmDevice;
import devices.ThermostatDevice;

import java.util.Arrays;
import java.util.List;

public class SmartHomeFactory {
    public static List<Command> createCommands() {
        LightController lightController = new LightController();
        ThermostatController thermostatController = new ThermostatController();
        SecurityController securityController = new SecurityController();
        List<IDevice> devices = createDevices();

        return Arrays.asList(
                new TurnOnLightCommand(lightController),
                new SetTemperatureCommand(thermostatController),
                new LockDoorsCommand(securityController),
                new TurnOffAllCommand(devices)
        );
    }

    private static List<IDevice> createDevices() {
        return Arrays.asList(
                new LightDevice(),
                new ThermostatDevice(),
                new SecurityAlarmDevice()
        );
    }
}
