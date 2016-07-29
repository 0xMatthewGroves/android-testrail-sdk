package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class ConfigurationGroup {
    private static final String TAG = ConfigurationGroup.class.getSimpleName();

    private final List<Configuration> configs;
    private final Integer id;
    private final String name;
    private final Integer projectId;

    private ConfigurationGroup(Builder builder) {
        this.configs = builder.configs;
        this.id = builder.id;
        this.name = builder.name;
        this.projectId = builder.projectId;
    }

    public List<Configuration> getConfigs() {
        return configs;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(newLine);
        result.append(newLine);
        result.append("--- ").append(this.getClass().getSimpleName()).append(" ---");
        result.append(newLine);
        result.append(newLine);

        Field[] fields = this.getClass().getDeclaredFields();

        for(Field field : fields) {
            try {
                if(field.get(this) != null && !field.getName().equals("TAG")) {
                    result.append(field.getName()).append(": ").append(field.get(this));
                    result.append(newLine);
                }
            } catch (IllegalAccessException iae) {
                iae.printStackTrace();
            }
        }

        result.append(newLine);

        return result.toString();
    }

    public static ConfigurationGroup parse(JSONObject jsonConfigurationGroup) {
        return new ConfigurationGroup.Builder()
                .configs(jsonConfigurationGroup.get("configs"))
                .id(jsonConfigurationGroup.get("id"))
                .name(jsonConfigurationGroup.get("name"))
                .projectId(jsonConfigurationGroup.get("project_id"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        JSONArray jsonConfigs = new JSONArray();

        if(getConfigs() != null) {
            for (Configuration c : getConfigs()) {
                jsonConfigs.add(c.serialize());
            }
        }

        bundle.put("configs", jsonConfigs);
        bundle.put("id", getId());
        bundle.put("name", getName());
        bundle.put("project_id", getProjectId());

        Log.d(TAG, ConfigurationGroup.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private List<Configuration> configs;
        private Integer id;
        private String name;
        private Integer projectId;

        public Builder configs(List<Configuration> configs) {
            this.configs = configs;
            return this;
        }

        protected Builder configs(Object configs) {
            if(configs != null) {
                JSONArray jsonConfigs = (JSONArray) configs;
                List<Configuration> configurations = new ArrayList<>();

                for(int i = 0; i < jsonConfigs.size(); i++) {
                    JSONObject jsonConfig = (JSONObject) jsonConfigs.get(i);

                    configurations.add(new Configuration.Builder()
                            .groupId(jsonConfig.get("group_id"))
                            .id(jsonConfig.get("id"))
                            .name(jsonConfig.get("name"))
                            .build());
                }

                this.configs = configurations;
            }
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        protected Builder id(Object id) {
            if(id != null) {
                this.id = Integer.parseInt(id.toString());
            }
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        protected Builder name(Object name) {
            if(name != null) {
                this.name = name.toString();
            }
            return this;
        }

        public Builder projectId(int projectId) {
            this.projectId = projectId;
            return this;
        }

        protected Builder projectId(Object projectId) {
            if(projectId != null) {
                this.projectId = Integer.parseInt(projectId.toString());
            }
            return this;
        }

        public ConfigurationGroup build() {
            return new ConfigurationGroup(this);
        }
    }
}