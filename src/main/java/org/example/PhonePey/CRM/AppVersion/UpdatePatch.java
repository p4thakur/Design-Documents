package org.example.PhonePey.CRM.AppVersion;

public class UpdatePatch {
    private final String fromVersion;
    private final String toVersion;
    private final String patchUrl;

    public UpdatePatch(String fromVersion, String toVersion, String patchUrl) {
        this.fromVersion = fromVersion;
        this.toVersion = toVersion;
        this.patchUrl = patchUrl;
    }

    public String getFromVersion() { return fromVersion; }
    public String getToVersion() { return toVersion; }
    public String getPatchUrl() { return patchUrl; }
}