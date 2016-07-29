package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Suite {
    private static final String TAG = Suite.class.getSimpleName();

    private final String completedOn;
    private final String description;
    private final Integer id;
    private final Boolean isBaseline;
    private final Boolean isCompleted;
    private final Boolean isMaster;
    private final String name;
    private final Integer projectId;
    private final String url;

    private Suite(Builder builder) {
        this.completedOn = builder.completedOn;
        this.description = builder.description;
        this.id = builder.id;
        this.isBaseline = builder.isBaseline;
        this.isCompleted = builder.isCompleted;
        this.isMaster = builder.isMaster;
        this.name = builder.name;
        this.projectId = builder.projectId;
        this.url = builder.url;
    }

    public String getCompletedOn() {
        return completedOn;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public Boolean isBaseline() {
        return isBaseline;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }

    public Boolean isMaster() {
        return isMaster;
    }

    public String getName() {
        return name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getUrl() {
        return url;
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

    public static Suite parse(JSONObject jsonSuite) {
        return new Suite.Builder()
                .completedOn(jsonSuite.get("completed_on"))
                .description(jsonSuite.get("description"))
                .id(jsonSuite.get("id"))
                .isBaseline(jsonSuite.get("is_baseline"))
                .isCompleted(jsonSuite.get("is_completed"))
                .isMaster(jsonSuite.get("is_master"))
                .name(jsonSuite.get("name"))
                .projectId(jsonSuite.get("project_id"))
                .url(jsonSuite.get("url"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("completed_on", getCompletedOn());
        bundle.put("description", getDescription());
        bundle.put("id", getId());
        bundle.put("is_baseline", isBaseline());
        bundle.put("is_completed", isCompleted());
        bundle.put("is_master", isMaster());
        bundle.put("name", getName());
        bundle.put("project_id", getProjectId());
        bundle.put("url", getUrl());

        Log.d(TAG, Suite.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private String completedOn;
        private String description;
        private Integer id;
        private Boolean isBaseline;
        private Boolean isCompleted;
        private Boolean isMaster;
        private String name;
        private Integer projectId;
        private String url;

        public Builder completedOn(String completedOn) {
            this.completedOn = completedOn;
            return this;
        }

        protected Builder completedOn(Object completedOn) {
            if(completedOn != null) {
                this.completedOn = completedOn.toString();
            }
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        protected Builder description(Object description) {
            if(description != null) {
                this.description = description.toString();
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

        public Builder isBaseline(boolean isBaseline) {
            this.isBaseline = isBaseline;
            return this;
        }

        protected Builder isBaseline(Object isBaseline) {
            if(isBaseline != null) {
                this.isBaseline = Boolean.parseBoolean(isBaseline.toString());
            }
            return this;
        }

        public Builder isCompleted(boolean isCompleted) {
            this.isCompleted = isCompleted;
            return this;
        }

        protected Builder isCompleted(Object isCompleted) {
            if(isCompleted != null) {
                this.isCompleted = Boolean.parseBoolean(isCompleted.toString());
            }
            return this;
        }

        public Builder isMaster(boolean isMaster) {
            this.isMaster = isMaster;
            return this;
        }

        protected Builder isMaster(Object isMaster) {
            if(isMaster != null) {
                this.isMaster = Boolean.parseBoolean(isMaster.toString());
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

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        protected Builder url(Object url) {
            if(url != null) {
                this.url = url.toString();
            }
            return this;
        }

        public Suite build() {
            return new Suite(this);
        }
    }
}