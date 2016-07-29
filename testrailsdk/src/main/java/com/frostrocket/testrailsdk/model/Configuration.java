package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class Configuration {
    private static final String TAG = Configuration.class.getSimpleName();

    private final Integer groupId;
    private final Integer id;
    private final String name;

    private Configuration(Builder builder) {
        this.groupId = builder.groupId;
        this.id = builder.id;
        this.name = builder.name;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public static Configuration parse(JSONObject jsonConfiguration) {
        return new Configuration.Builder()
                .groupId(jsonConfiguration.get("group_id"))
                .id(jsonConfiguration.get("id"))
                .name(jsonConfiguration.get("name"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("group_id", getGroupId());
        bundle.put("id", getId());
        bundle.put("name", getName());

        Log.d(TAG, Configuration.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private Integer groupId;
        private Integer id;
        private String name;

        public Builder groupId(int groupId) {
            this.groupId = groupId;
            return this;
        }

        protected Builder groupId(Object groupId) {
            if(groupId != null) {
                this.groupId = Integer.parseInt(groupId.toString());
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

        public Configuration build() {
            return new Configuration(this);
        }
    }
}