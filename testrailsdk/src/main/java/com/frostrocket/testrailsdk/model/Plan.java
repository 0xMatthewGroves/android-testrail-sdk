package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class Plan {
    private static final String TAG = Plan.class.getSimpleName();

    private final Integer assignedToId;
    private final Integer blockedCount;
    private final List<Integer> caseIds;
    private final String completedOn;
    private final Integer createdBy;
    private final String createdOn;
    private final Integer customStatusCount;
    private final String description;
    private final List<Entry> entries;
    private final Integer failedCount;
    private final Integer id;
    private final Boolean isCompleted;
    private final Integer milestoneId;
    private final String name;
    private final Integer passedCount;
    private final Integer projectId;
    private final Integer retestCount;
    private final Integer untestedCount;
    private final String url;

    private Plan(Builder builder) {
        this.assignedToId = builder.assignedToId;
        this.blockedCount = builder.blockedCount;
        this.caseIds = builder.caseIds;
        this.completedOn = builder.completedOn;
        this.createdBy = builder.createdBy;
        this.createdOn = builder.createdOn;
        this.customStatusCount = builder.customStatusCount;
        this.description = builder.description;
        this.entries = builder.entries;
        this.failedCount = builder.failedCount;
        this.id = builder.id;
        this.isCompleted = builder.isCompleted;
        this.milestoneId = builder.milestoneId;
        this.name = builder.name;
        this.passedCount = builder.passedCount;
        this.projectId = builder.projectId;
        this.retestCount = builder.retestCount;
        this.untestedCount = builder.untestedCount;
        this.url = builder.url;
    }

    public Integer getAssignedToId() {
        return assignedToId;
    }

    public Integer getBlockedCount() {
        return blockedCount;
    }

    public List<Integer> getCaseIds() {
        return caseIds;
    }

    public String getCompletedOn() {
        return completedOn;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public Integer getCustomStatusCount() {
        return customStatusCount;
    }

    public String getDescription() {
        return description;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public Integer getId() {
        return id;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }

    public Integer getMilestoneId() {
        return milestoneId;
    }

    public String getName() {
        return name;
    }

    public Integer getPassedCount() {
        return passedCount;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public Integer getRetestCount() {
        return retestCount;
    }

    public Integer getUntestedCount() {
        return untestedCount;
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

    public static Plan parse(JSONObject jsonPlan) {
        return new Builder()
                .assignedToId(jsonPlan.get("assignedto_id"))
                .blockedCount(jsonPlan.get("blocked_count"))
                .caseIds(jsonPlan.get("case_ids"))
                .completedOn(jsonPlan.get("completed_on"))
                .createdBy(jsonPlan.get("created_by"))
                .createdOn(jsonPlan.get("created_on"))
                .customStatusCount(jsonPlan.get("custom_status_count"))
                .description(jsonPlan.get("description"))
                .entries(jsonPlan.get("entries"))
                .failedCount(jsonPlan.get("failed_count"))
                .id(jsonPlan.get("id"))
                .isCompleted(jsonPlan.get("is_completed"))
                .milestoneId(jsonPlan.get("milestone_id"))
                .name(jsonPlan.get("name"))
                .passedCount(jsonPlan.get("passed_count"))
                .projectId(jsonPlan.get("project_id"))
                .retestCount(jsonPlan.get("retest_count"))
                .untestedCount(jsonPlan.get("untested_count"))
                .url(jsonPlan.get("url"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("assignedto_id", getAssignedToId());
        bundle.put("blocked_count", getBlockedCount());
        bundle.put("case_ids", getCaseIds());
        bundle.put("completed_on", getCompletedOn());
        bundle.put("created_by", getCreatedBy());
        bundle.put("created_on", getCreatedOn());
        bundle.put("custom_status_count", getCustomStatusCount());
        bundle.put("description", getDescription());

        JSONArray jsonEntries = new JSONArray();

        if(getEntries() != null) {
            for (Entry e : getEntries()) {
                jsonEntries.add(e.serialize());
            }
        }

        bundle.put("entries", jsonEntries);
        bundle.put("failed_count", getFailedCount());
        bundle.put("id", getId());
        bundle.put("is_completed", isCompleted());
        bundle.put("milestone_id", getMilestoneId());
        bundle.put("name", getName());
        bundle.put("passed_count", getPassedCount());
        bundle.put("project_id", getProjectId());
        bundle.put("retest_count", getRetestCount());
        bundle.put("untested_count", getUntestedCount());
        bundle.put("url", getUrl());

        Log.d(TAG, Plan.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private Integer assignedToId;
        private Integer blockedCount;
        private List<Integer> caseIds;
        private String completedOn;
        private Integer createdBy;
        private String createdOn;
        private Integer customStatusCount;
        private String description;
        private List<Entry> entries;
        private Integer failedCount;
        private Integer id;
        private Boolean isCompleted;
        private Integer milestoneId;
        private String name;
        private Integer passedCount;
        private Integer projectId;
        private Integer retestCount;
        private Integer untestedCount;
        private String url;

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

        public Builder blockedCount(int blockedCount) {
            this.blockedCount = blockedCount;
            return this;
        }

        protected Builder blockedCount(Object blockedCount) {
            if(blockedCount != null) {
                this.blockedCount = Integer.parseInt(blockedCount.toString());
            }
            return this;
        }

        public Builder caseIds(List<Integer> caseIds) {
            this.caseIds = caseIds;
            return this;
        }

        protected Builder caseIds(Object caseIds) {
            if(caseIds != null) {
                this.caseIds = new ArrayList<>();
                this.caseIds.addAll((JSONArray) caseIds);
            }
            return this;
        }

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

        public Builder customStatusCount(int customStatusCount) {
            this.customStatusCount = customStatusCount;
            return this;
        }

        protected Builder customStatusCount(Object customStatusCount) {
            if(customStatusCount != null) {
                this.customStatusCount = Integer.parseInt(customStatusCount.toString());
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

        public Builder entries(List<Entry> entries) {
            this.entries = entries;
            return this;
        }

        protected Builder entries(Object entries) {
            if(entries != null) {
                JSONArray jsonEntries = (JSONArray) entries;
                List<Entry> e = new ArrayList<>();

                for(int i = 0; i < jsonEntries.size(); i++) {
                    e.add(Entry.parse((JSONObject) jsonEntries.get(i)));
                }

                this.entries = e;
            }
            return this;
        }

        public Builder failedCount(int failedCount) {
            this.failedCount = failedCount;
            return this;
        }

        protected Builder failedCount(Object failedCount) {
            if(failedCount != null) {
                this.failedCount = Integer.parseInt(failedCount.toString());
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

        public Builder passedCount(int passedCount) {
            this.passedCount = passedCount;
            return this;
        }

        protected Builder passedCount(Object passedCount) {
            if(passedCount != null) {
                this.passedCount = Integer.parseInt(passedCount.toString());
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

        public Builder retestCount(int retestCount) {
            this.retestCount = retestCount;
            return this;
        }

        protected Builder retestCount(Object retestCount) {
            if(retestCount != null) {
                this.retestCount = Integer.parseInt(retestCount.toString());
            }
            return this;
        }

        public Builder untestedCount(int untestedCount) {
            this.untestedCount = untestedCount;
            return this;
        }

        protected Builder untestedCount(Object untestedCount) {
            if(untestedCount != null) {
                this.untestedCount = Integer.parseInt(untestedCount.toString());
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

        public Plan build() {
            return new Plan(this);
        }
    }
}