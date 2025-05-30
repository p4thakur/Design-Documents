package org.example.PhonePey.CRM.AppVersion;

public class InstallCommand implements TaskCommand {
    private final App app;
    private final Device device;

    public InstallCommand(App app, Device device) {
        this.app = app;
        this.device = device;
    }

    @Override
    public void execute() {
        AppVersion latest = app.getLatestVersion();
        device.installApp(app.getName(), latest.getVersionId());
        System.out.println("Installed version " + latest.getVersionId() + " on device " + device.getDeviceId());
    }
}