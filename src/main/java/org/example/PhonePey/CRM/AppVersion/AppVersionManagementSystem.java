package org.example.PhonePey.CRM.AppVersion;

import java.util.*;

public class AppVersionManagementSystem {
    private final Map<String, App> apps = new HashMap<>();
    private final Map<String, Device> devices = new HashMap<>();

    // Factory Method
    public RolloutStrategy getRolloutStrategy(String type, Set<String> betaDevices, int percentage) {
         switch (type.toUpperCase()) {
             case "BETA":
                  return new BetaRolloutStrategy(betaDevices);
             case "PERCENTAGE" :
                 return new PercentageRolloutStrategy(percentage);
             default:
                 throw new IllegalArgumentException("Invalid strategy");
        }
    }

    public void uploadNewVersion(String appName, String versionId, String osType,
                                 String minOsVersion, String fileContent) {
        apps.putIfAbsent(appName, new App(appName));
        App app = apps.get(appName);
        String fileUrl = uploadFile(fileContent);
        AppVersion newVersion = new AppVersion.Builder()
                .setVersionId(versionId)
                .setOsType(osType)
                .setMinOsVersion(minOsVersion)
                .setFileUrl(fileUrl)
                .build();
        app.addVersion(newVersion);
    }

    public void createUpdatePatch(String appName, String fromVersion, String toVersion) {
        App app = apps.get(appName);
        AppVersion from = app.getVersion(fromVersion);
        AppVersion to = app.getVersion(toVersion);
        String diff = createDiffPack(getFile(from.getFileUrl()), getFile(to.getFileUrl()));
        String patchUrl = uploadFile(diff);
        app.addPatch(new UpdatePatch(fromVersion, toVersion, patchUrl));
    }

    public void releaseVersion(String appName, String versionId, RolloutStrategy strategy) {
        App app = apps.get(appName);
        AppVersion version = app.getVersion(versionId);
        for (Device device : devices.values()) {
            if (strategy.shouldRolloutTo(device, version)) {
                executeTask(new UpdateCommand(app, device, version));
            }
        }
    }

    public void installAppOnDevice(String appName, String deviceId) {
        Device device = devices.get(deviceId);
        App app = apps.get(appName);

        if (device == null || app == null) return;

        AppVersion latest = app.getLatestVersion();
        if (latest != null && isAppVersionSupported(appName, latest.getVersionId(), device)) {
            executeTask(new InstallCommand(app, device));
        } else {
            System.out.println("Device " + deviceId + " does not support the latest version of " + appName);
        }
    }

    public void rollbackVersion(String appName, String targetVersionId, RolloutStrategy strategy) {
        App app = apps.get(appName);
        AppVersion targetVersion = app.getVersion(targetVersionId);

        for (Device device : devices.values()) {
            if (!targetVersion.getVersionId().equals(device.getInstalledAppVersion(appName)) &&
                    strategy.shouldRolloutTo(device, targetVersion) &&
                    isAppVersionSupported(appName, targetVersionId, device)) {

                executeTask(new UpdateCommand(app, device, targetVersion));
            }
        }
    }


    public boolean isAppVersionSupported(String appName, String versionId, Device device) {
        AppVersion version = apps.get(appName).getVersion(versionId);
        return version.getOsType().equals(device.getOsType())
                && version.getMinOsVersion().compareTo(device.getOsVersion()) <= 0;
    }

//    public boolean checkForInstall(String appName, Device device) {
//        App app = apps.get(appName);
//        return app.getLatestVersion().getMinOsVersion().compareTo(device.getOsVersion()) <= 0;
//    }

    public boolean checkForUpdates(String appName, Device device) {
        App app = apps.get(appName);
        return device.getInstalledAppVersion(appName) == null ||
                !device.getInstalledAppVersion(appName).equals(app.getLatestVersion().getVersionId());
    }

    public void executeTask(TaskCommand taskCommand) {
        taskCommand.execute();
    }

    public void registerDevice(Device device) {
        devices.put(device.getDeviceId(), device);
    }

    // Dummy external methods
    private String uploadFile(String fileContent) {
        return UUID.randomUUID() + "_file_url";
    }

    private String getFile(String url) {
        return "fileContentOf_" + url;
    }

    private String createDiffPack(String from, String to) {
        return "diff_of_" + from + "_and_" + to;
    }
}
