package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class CaseType {
    private static final String TAG = CaseType.class.getSimpleName();

    private final Integer id;
    private final Boolean isDefault;
    private final String name;

    private CaseType(Builder builder) {
        this.id = builder.id;
        this.isDefault = builder.isDefault;
        this.name = builder.name;
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

    public static CaseType parse(JSONObject jsonCaseType) {
        return new Builder()
                .id(jsonCaseType.get("id"))
                .isDefault(jsonCaseType.get("is_default"))
                .name(jsonCaseType.get("name"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("id", getId());
        bundle.put("is_default", getDefault());
        bundle.put("name", getName());

        Log.d(TAG, CaseType.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {

        private Integer id;
        private Boolean isDefault;
        private String name;

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
            if(name != null) {
                this.name = name.toString();
            }
            return this;
        }

        public CaseType build() {
            return new CaseType(this);
        }
    }
}