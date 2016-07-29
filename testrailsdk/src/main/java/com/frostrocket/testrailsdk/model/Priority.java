package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class Priority {
    private static final String TAG = Priority.class.getSimpleName();

    private final Integer id;
    private final Boolean isDefault;
    private final String name;
    private final Integer priority;
    private final String shortName;

    private Priority(Builder builder) {
        this.id = builder.id;
        this.isDefault = builder.isDefault;
        this.name = builder.name;
        this.priority = builder.priority;
        this.shortName = builder.shortName;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public String getName() {
        return name;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getShortName() {
        return shortName;
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

        for (Field field : fields) {
            try {
                if (field.get(this) != null && !field.getName().equals("TAG")) {
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

    public static Priority parse(JSONObject jsonPriority) {
        return new Builder()
                .id(jsonPriority.get("id"))
                .isDefault(jsonPriority.get("is_default"))
                .name(jsonPriority.get("name"))
                .priority(jsonPriority.get("priority"))
                .shortName(jsonPriority.get("short_name"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("id", getId());
        bundle.put("is_default", getDefault());
        bundle.put("name", getName());
        bundle.put("priority", getPriority());
        bundle.put("short_name", getShortName());

        Log.d(TAG, Priority.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private Integer id;
        private Boolean isDefault;
        private String name;
        private Integer priority;
        private String shortName;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        protected Builder id(Object id) {
            if (id != null) {
                this.id = Integer.parseInt(id.toString());
            }
            return this;
        }

        public Builder isDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        protected Builder isDefault(Object isDefault) {
            if (isDefault != null) {
                this.isDefault = Boolean.parseBoolean(isDefault.toString());
            }
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        protected Builder name(Object name) {
            if (name != null) {
                this.name = name.toString();
            }
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        protected Builder priority(Object priority) {
            if (priority != null) {
                this.priority = Integer.parseInt(priority.toString());
            }
            return this;
        }

        public Builder shortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        protected Builder shortName(Object shortName) {
            if (shortName != null) {
                this.shortName = shortName.toString();
            }
            return this;
        }

        public Priority build() {
            return new Priority(this);
        }
    }
}
