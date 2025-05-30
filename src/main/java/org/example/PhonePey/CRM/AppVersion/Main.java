package org.example.PhonePey.CRM.AppVersion;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        AppVersionManagementSystem system = new AppVersionManagementSystem();

        // Step 1: Register Devices
        Device device1 = new Device("device-001", "Android", "13.0");
        Device device2 = new Device("device-002", "Android", "12.0");
        Device device3 = new Device("device-003", "Android", "11.0");
        system.registerDevice(device1);
        system.registerDevice(device2);
        system.registerDevice(device3);

        // Step 2: Upload App Versions
        system.uploadNewVersion("MyApp", "1.0.0", "Android", "11.0", "fileContent_v1");
        system.uploadNewVersion("MyApp", "1.1.0", "Android", "12.0", "fileContent_v2");
        //system.uploadNewVersion("MyApp", "2.0.0", "Android", "13.0", "fileContent_v3");

        // Step 3: Install App on Devices
        system.installAppOnDevice("MyApp", "device-001");
        system.installAppOnDevice("MyApp", "device-002");
        system.installAppOnDevice("MyApp", "device-003");

        // Step 4: Create Patch from 1.0.0 to 1.1.0
        system.createUpdatePatch("MyApp", "1.0.0", "1.1.0");

        // Step 5: Release 1.1.0 to 100% of devices
        RolloutStrategy fullRollout = system.getRolloutStrategy("PERCENTAGE", null, 100);
        system.releaseVersion("MyApp", "1.1.0", fullRollout);

        // Step 6: Check Updates
        System.out.println("Device 1 needs update: " + system.checkForUpdates("MyApp", device1));
        System.out.println("Device 2 needs update: " + system.checkForUpdates("MyApp", device2));
        System.out.println("Device 3 needs update: " + system.checkForUpdates("MyApp", device3));

        // Step 7: Rollback 1.1.0 to 1.0.0 only for device-002
        Set<String> rollbackSet = new HashSet<>();
        rollbackSet.add("device-002");
        RolloutStrategy betaRollback = system.getRolloutStrategy("BETA", rollbackSet, 0);
        system.rollbackVersion("MyApp", "1.0.0", betaRollback);
    }
}
