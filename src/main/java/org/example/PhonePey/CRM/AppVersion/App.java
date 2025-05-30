package org.example.PhonePey.CRM.AppVersion;

import lombok.Getter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class App {
    @Getter
    private final String name;
    private final Map<String, AppVersion> versions = new HashMap<>();
    private final Map<String, UpdatePatch> patches = new HashMap<>();

    public App(String name) {
        this.name = name;
    }

    public void addVersion(AppVersion version) {
        versions.put(version.getVersionId(), version);
    }

    public AppVersion getVersion(String versionId) {
        return versions.get(versionId);
    }

    public AppVersion getLatestVersion() {
        return versions.values().stream().max(Comparator.comparing(AppVersion::getVersionId)).orElse(null);
    }

    public void addPatch(UpdatePatch patch) {
        patches.put(patch.getFromVersion() + "_" + patch.getToVersion(), patch);
    }

    public UpdatePatch getPatch(String from, String to) {
        return patches.get(from + "_" + to);
    }
}
