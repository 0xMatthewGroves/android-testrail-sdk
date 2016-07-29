package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Test {
    private static final String TAG = Test.class.getSimpleName();

    private final Integer assignedToId;
    private final Integer caseId;
    private final String estimate;
    private final String estimateForecast;
    private final Integer id;
    private final Integer milestoneId;
    private final Integer priorityId;
    private final String refs;
    private final Integer runId;
    private final Integer statusId;
    private final String title;
    private final Integer typeId;

    private Test(Builder builder) {
        this.assignedToId = builder.assignedToId;
        this.caseId = builder.caseId;
        this.estimate = builder.estimate;
        this.estimateForecast = builder.estimateForecast;
        this.id = builder.id;
        this.milestoneId = builder.milestoneId;
        this.priorityId = builder.priorityId;
        this.refs = builder.refs;
        this.runId = builder.runId;
        this.statusId = builder.statusId;
        this.title = builder.title;
        this.typeId = builder.typeId;
    }

    public Integer getAssignedToId() {
        return assignedToId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public String getEstimate() {
        return estimate;
    }

    public String getEstimateForecast() {
        return estimateForecast;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMilestoneId() {
        return milestoneId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public String getRefs() {
        return refs;
    }

    public Integer getRunId() {
        return runId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTypeId() {
        return typeId;
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

    public static Test parse(JSONObject jsonTest) {
        return new Test.Builder()
                .assignedToId(jsonTest.get("assignedto_id"))
                .caseId(jsonTest.get("case_id"))
                .estimate(jsonTest.get("estimate"))
                .estimateForecast(jsonTest.get("estimate_forecast"))
                .id(jsonTest.get("id"))
                .milestoneId(jsonTest.get("milestone_id"))
                .priorityId(jsonTest.get("priority_id"))
                .refs(jsonTest.get("refs"))
                .runId(jsonTest.get("run_id"))
                .statusId(jsonTest.get("status_id"))
                .title(jsonTest.get("title"))
                .typeId(jsonTest.get("type_id"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("assignedto_id", getAssignedToId());
        bundle.put("case_id", getCaseId());
        bundle.put("estimate", getEstimate());
        bundle.put("estimate_forecast", getEstimateForecast());
        bundle.put("id", getId());
        bundle.put("milestone_id", getMilestoneId());
        bundle.put("priority_id", getPriorityId());
        bundle.put("refs", getRefs());
        bundle.put("run_id", getRunId());
        bundle.put("status_id", getStatusId());
        bundle.put("title", getTitle());
        bundle.put("type_id", getTypeId());

        Log.d(TAG, Test.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private Integer assignedToId;
        private Integer caseId;
        private String estimate;
        private String estimateForecast;
        private Integer id;
        private Integer milestoneId;
        private Integer priorityId;
        private String refs;
        private Integer runId;
        private Integer statusId;
        private String title;
        private Integer typeId;

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

        public Builder caseId(int caseId) {
            this.caseId = caseId;
            return this;
        }

        protected Builder caseId(Object caseId) {
            if(caseId != null) {
                this.caseId = Integer.parseInt(caseId.toString());
            }
            return this;
        }

        public Builder estimate(String estimate) {
            this.estimate = estimate;
            return this;
        }

        protected Builder estimate(Object estimate) {
            if(estimate != null) {
                this.estimate = estimate.toString();
            }
            return this;
        }

        public Builder estimateForecast(String estimateForecast) {
            this.estimateForecast = estimateForecast;
            return this;
        }

        protected Builder estimateForecast(Object estimateForecast) {
            if(estimateForecast != null) {
                this.estimateForecast = estimateForecast.toString();
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

        public Builder milestoneId(int milestoneId) {
            this.milestoneId = milestoneId;
            return this;
        }

        protected Builder milestoneId(Object milestoneId) {
            if(milestoneId != null) {
                this.milestoneId = Integer.parseInt(milestoneId.toString());
            }
            return this;
        }

        public Builder priorityId(int priorityId) {
            this.priorityId = priorityId;
            return this;
        }

        protected Builder priorityId(Object priorityId) {
            if(priorityId != null) {
                this.priorityId = Integer.parseInt(priorityId.toString());
            }
            return this;
        }

        public Builder refs(String refs) {
            this.refs = refs;
            return this;
        }

        protected Builder refs(Object refs) {
            if(refs != null) {
                this.refs = refs.toString();
            }
            return this;
        }

        public Builder runId(int runId) {
            this.runId = runId;
            return this;
        }

        protected Builder runId(Object runId) {
            if(runId != null) {
                this.runId = Integer.parseInt(runId.toString());
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

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        protected Builder title(Object title) {
            if(title != null) {
                this.title = title.toString();
            }
            return this;
        }

        public Builder typeId(int typeId) {
            this.typeId = typeId;
            return this;
        }

        protected Builder typeId(Object typeId) {
            if(typeId != null) {
                this.typeId = Integer.parseInt(typeId.toString());
            }
            return this;
        }

        public Test build() {
            return new Test(this);
        }
    }
}