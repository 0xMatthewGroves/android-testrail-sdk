package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class Run {
    private static final String TAG = Run.class.getSimpleName();

    private final Integer assignedToId;
    private final Integer blockedCount;
    private final List<Integer> caseIds;
    private final String completedOn;
    private final String config;
    private final List<Integer> configIds;
    private final Integer createdBy;
    private final String createdOn;
    private final Integer customStatusCount;
    private final String description;
    private final Integer failedCount;
    private final Integer id;
    private final Boolean includeAll;
    private final Boolean isCompleted;
    private final Integer milestoneId;
    private final Integer planId;
    private final String name;
    private final Integer passedCount;
    private final Integer projectId;
    private final Integer retestCount;
    private final Integer suiteId;
    private final Integer untestedCount;
    private final String url;

    private Run(Builder builder) {
        this.assignedToId = builder.assignedToId;
        this.blockedCount = builder.blockedCount;
        this.caseIds = builder.caseIds;
        this.completedOn = builder.completedOn;
        this.config = builder.config;
        this.configIds = builder.configIds;
        this.createdBy = builder.createdBy;
        this.createdOn = builder.createdOn;
        this.customStatusCount = builder.customStatusCount;
        this.description = builder.description;
        this.failedCount = builder.failedCount;
        this.id = builder.id;
        this.includeAll = builder.includeAll;
        this.isCompleted = builder.isCompleted;
        this.milestoneId = builder.milestoneId;
        this.planId = builder.planId;
        this.name = builder.name;
        this.passedCount = builder.passedCount;
        this.projectId = builder.projectId;
        this.retestCount = builder.retestCount;
        this.suiteId = builder.suiteId;
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

    public String getConfig() {
        return config;
    }

    public List<Integer> getConfigIds() {
        return configIds;
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

    public Integer getFailedCount() {
        return failedCount;
    }

    public Integer getId() {
        return id;
    }

    public Boolean isIncludeAll() {
        return includeAll;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }

    public Integer getMilestoneId() {
        return milestoneId;
    }

    public Integer getPlanId() {
        return planId;
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

    public Integer getSuiteId() {
        return suiteId;
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

    public static Run parse(JSONObject jsonRun) {
        return new Run.Builder()
                .assignedToId(jsonRun.get("assignedto_id"))
                .blockedCount(jsonRun.get("blocked_count"))
                .completedOn(jsonRun.get("completed_on"))
                .config(jsonRun.get("config"))
                .configIds(jsonRun.get("config_ids"))
                .createdBy(jsonRun.get("created_by"))
                .createdOn(jsonRun.get("created_on"))
                .customStatusCount(jsonRun.get("custom_status_count"))
                .description(jsonRun.get("description"))
                .failedCount(jsonRun.get("failed_count"))
                .id(jsonRun.get("id"))
                .includeAll(jsonRun.get("include_all"))
                .isCompleted(jsonRun.get("is_completed"))
                .milestoneId(jsonRun.get("milestone_id"))
                .planId(jsonRun.get("plan_id"))
                .name(jsonRun.get("name"))
                .passedCount(jsonRun.get("passed_count"))
                .projectId(jsonRun.get("project_id"))
                .retestCount(jsonRun.get("retest_count"))
                .suiteId(jsonRun.get("suite_id"))
                .untestedCount(jsonRun.get("untested_count"))
                .url(jsonRun.get("url"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("assignedto_id", getAssignedToId());
        bundle.put("blocked_count", getBlockedCount());
        bundle.put("case_ids", getCaseIds());
        bundle.put("completed_on", getCompletedOn());
        bundle.put("config", getConfig());
        bundle.put("config_ids", getConfigIds());
        bundle.put("created_by", getCreatedBy());
        bundle.put("created_on", getCreatedOn());
        bundle.put("custom_status_count", getCustomStatusCount());
        bundle.put("description", getDescription());
        bundle.put("failed_count", getFailedCount());
        bundle.put("id", getId());
        bundle.put("include_all", isIncludeAll());
        bundle.put("is_completed", isCompleted());
        bundle.put("milestone_id", getMilestoneId());
        bundle.put("plan_id", getPlanId());
        bundle.put("name", getName());
        bundle.put("passed_count", getPassedCount());
        bundle.put("project_id", getProjectId());
        bundle.put("retest_count", getRetestCount());
        bundle.put("suite_id", getSuiteId());
        bundle.put("untested_count", getUntestedCount());
        bundle.put("url", getUrl());

        Log.d(TAG, Run.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private Integer assignedToId;
        private Integer blockedCount;
        private List<Integer> caseIds;
        private String completedOn;
        private String config;
        private List<Integer> configIds;
        private Integer createdBy;
        private String createdOn;
        private Integer customStatusCount;
        private String description;
        private Integer failedCount;
        private Integer id;
        private Boolean includeAll;
        private Boolean isCompleted;
        private Integer milestoneId;
        private Integer planId;
        private String name;
        private Integer passedCount;
        private Integer projectId;
        private Integer retestCount;
        private Integer suiteId;
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

        public Builder config(String config) {
            this.config = config;
            return this;
        }

        protected Builder config(Object config) {
            if(config != null) {
                this.config = config.toString();
            }
            return this;
        }

        public Builder configIds(List<Integer> configIds) {
            this.configIds = configIds;
            return this;
        }

        protected Builder configIds(Object configIds) {
            if(configIds != null) {
                this.configIds = new ArrayList<>();
                this.configIds.addAll((JSONArray) configIds);
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

        public Builder includeAll(boolean includeAll) {
            this.includeAll = includeAll;
            return this;
        }

        protected Builder includeAll(Object includeAll) {
            if(includeAll != null) {
                this.includeAll = Boolean.parseBoolean(includeAll.toString());
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

        public Builder planId(int planId) {
            this.planId = planId;
            return this;
        }

        protected Builder planId(Object planId) {
            if(planId != null) {
                this.planId = Integer.parseInt(planId.toString());
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

        public Builder suiteId(int suiteId) {
            this.suiteId = suiteId;
            return this;
        }

        protected Builder suiteId(Object suiteId) {
            if(suiteId != null) {
                this.suiteId = Integer.parseInt(suiteId.toString());
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

        public Run build() {
            return new Run(this);
        }
    }
}