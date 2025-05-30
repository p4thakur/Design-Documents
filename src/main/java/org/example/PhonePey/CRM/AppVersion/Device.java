package org.example.PhonePey.CRM.AppVersion;

import java.util.HashMap;
import java.util.Map;

public class Device {
    private final String deviceId;
    private final String osType;
    private final String osVersion;
    private final Map<String, String> installedApps = new HashMap<>();

    public Device(String deviceId, String osType, String osVersion) {
        this.deviceId = deviceId;
        this.osType = osType;
        this.osVersion = osVersion;
    }

    public String getDeviceId() { return deviceId; }
    public String getOsType() { return osType; }
    public String getOsVersion() { return osVersion; }
    public String getInstalledAppVersion(String appName) { return installedApps.get(appName); }
    public void installApp(String appName, String versionId) { installedApps.put(appName, versionId); }
}