package org.example.PhonePey.CRM.AppVersion;

public class AppVersion {
    private final String versionId;
    private final String osType;
    private final String minOsVersion;
    private final String fileUrl;

    private AppVersion(Builder builder) {
        this.versionId = builder.versionId;
        this.osType = builder.osType;
        this.minOsVersion = builder.minOsVersion;
        this.fileUrl = builder.fileUrl;
    }

    public String getVersionId() { return versionId; }
    public String getOsType() { return osType; }
    public String getMinOsVersion() { return minOsVersion; }
    public String getFileUrl() { return fileUrl; }

    public static class Builder {
        private String versionId;
        private String osType;
        private String minOsVersion;
        private String fileUrl;

        public Builder setVersionId(String versionId) { this.versionId = versionId; return this; }
        public Builder setOsType(String osType) { this.osType = osType; return this; }
        public Builder setMinOsVersion(String minOsVersion) { this.minOsVersion = minOsVersion; return this; }
        public Builder setFileUrl(String fileUrl) { this.fileUrl = fileUrl; return this; }
        public AppVersion build() { return new AppVersion(this); }
    }
}
