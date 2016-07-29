package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Result {
    private static final String TAG = Result.class.getSimpleName();

    private final Integer assignedToId;
    private final String comment;
    private final Integer createdBy;
    private final String createdOn;
    private final String defects;
    private final String elapsed;
    private final Integer id;
    private final Integer statusId;
    private final Integer testId;
    private final String version;

    private Result(Builder builder) {
        this.assignedToId = builder.assignedToId;
        this.comment = builder.comment;
        this.createdBy = builder.createdBy;
        this.createdOn = builder.createdOn;
        this.defects = builder.defects;
        this.elapsed = builder.elapsed;
        this.id = builder.id;
        this.statusId = builder.statusId;
        this.testId = builder.testId;
        this.version = builder.version;
    }

    public Integer getAssignedToId() {
        return assignedToId;
    }

    public String getComment() {
        return comment;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getDefects() {
        return defects;
    }

    public String getElapsed() {
        return elapsed;
    }

    public Integer getId() {
        return id;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public Integer getTestId() {
        return testId;
    }

    public String getVersion() {
        return version;
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

    public static Result parse(JSONObject jsonResult) {
        return new Builder()
                .assignedToId(jsonResult.get("assignedto_id"))
                .comment(jsonResult.get("comment"))
                .createdBy(jsonResult.get("created_by"))
                .createdOn(jsonResult.get("created_on"))
                .defects(jsonResult.get("defects"))
                .elapsed(jsonResult.get("elapsed"))
                .id(jsonResult.get("id"))
                .statusId(jsonResult.get("status_id"))
                .testId(jsonResult.get("test_id"))
                .version(jsonResult.get("version"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("assignedto_id", getAssignedToId());
        bundle.put("comment", getComment());
        bundle.put("created_by", getCreatedBy());
        bundle.put("created_on", getCreatedOn());
        bundle.put("defects", getDefects());
        bundle.put("elapsed", getElapsed());
        bundle.put("id", getId());
        bundle.put("status_id", getStatusId());
        bundle.put("test_id", getTestId());
        bundle.put("version", getVersion());

        Log.d(TAG, Result.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private Integer assignedToId;
        private String comment;
        private Integer createdBy;
        private String createdOn;
        private String defects;
        private String elapsed;
        private Integer id;
        private Integer statusId;
        private Integer testId;
        private String version;

        public Builder assignedToId(int assignedToId) {
            this.assignedToId = assignedToId;
            return this;
        }

        protected Builder assignedToId(Object assignedToId) {
            if(assignedToId != null) {
                this.assignedToId = Integer.parseInt(assignedToId.toString());
            }
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        protected Builder comment(Object comment) {
            if(comment != null) {
                this.comment = comment.toString();
            }
            return this;
        }

        public Builder createdBy(int createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        protected Builder createdBy(Object createdBy) {
            if(createdBy != null) {
                this.createdBy = Integer.parseInt(createdBy.toString());
            }
            return this;
        }

        public Builder createdOn(String createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        protected Builder createdOn(Object createdOn) {
            if(createdOn != null) {
                this.createdOn = createdOn.toString();
            }
            return this;
        }

        public Builder defects(String defects) {
            this.defects = defects;
            return this;
        }

        protected Builder defects(Object defects) {
            if(defects != null) {
                this.defects = defects.toString();
            }
            return this;
        }

        public Builder elapsed(String elapsed) {
            this.elapsed = elapsed;
            return this;
        }

        protected Builder elapsed(Object elapsed) {
            if(elapsed != null) {
                this.elapsed = elapsed.toString();
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

        public Builder statusId(int statusId) {
            this.statusId = statusId;
            return this;
        }

        protected Builder statusId(Object statusId) {
            if(statusId != null) {
                this.statusId = Integer.parseInt(statusId.toString());
            }
            return this;
        }

        public Builder testId(int testId) {
            this.testId = testId;
            return this;
        }

        protected Builder testId(Object testId) {
            if(testId != null) {
                this.testId = Integer.parseInt(testId.toString());
            }
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        protected Builder version(Object version) {
            if(version != null) {
                this.version = version.toString();
            }
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }
}