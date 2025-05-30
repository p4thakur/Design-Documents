package org.example.PhonePey.CRM.AppVersion;

public class UpdateCommand implements TaskCommand {
    private final App app;
    private final Device device;
    private final AppVersion newVersion;

    public UpdateCommand(App app, Device device, AppVersion newVersion) {
        this.app = app;
        this.device = device;
        this.newVersion = newVersion;
    }

    @Override
    public void execute() {
        String fromVersion = device.getInstalledAppVersion(app.getName());
        UpdatePatch patch = app.getPatch(fromVersion, newVersion.getVersionId());
        if (patch != null) {
            System.out.println("Updating device " + device.getDeviceId() + " from " + fromVersion + " to " + newVersion.getVersionId());
            device.installApp(app.getName(), newVersion.getVersionId());
        } else {
            System.out.println("Patch not found. Installing directly.");
            device.installApp(app.getName(), newVersion.getVersionId());
        }
    }
}