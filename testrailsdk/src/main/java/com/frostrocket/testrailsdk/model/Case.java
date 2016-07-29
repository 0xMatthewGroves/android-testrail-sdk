package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class Case {
    private static final String TAG = Case.class.getSimpleName();

    private final Integer createdBy;
    private final String createdOn;
    private final String estimate;
    private final String estimateForecast;
    private final Integer id;
    private final Integer milestoneId;
    private final Integer priorityId;
    private final String refs;
    private final Integer sectionId;
    private final Integer suiteId;
    private final Integer templateId;
    private final String title;
    private final Integer typeId;
    private final Integer updatedBy;
    private final String updatedOn;

    private Case(Builder builder) {
        this.createdBy = builder.createdBy;
        this.createdOn = builder.createdOn;
        this.estimate = builder.estimate;
        this.estimateForecast = builder.estimateForecast;
        this.id = builder.id;
        this.milestoneId = builder.milestoneId;
        this.priorityId = builder.priorityId;
        this.refs = builder.refs;
        this.sectionId = builder.sectionId;
        this.suiteId = builder.suiteId;
        this.templateId = builder.templateId;
        this.title = builder.title;
        this.typeId = builder.typeId;
        this.updatedBy = builder.updatedBy;
        this.updatedOn = builder.updatedOn;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
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

    public Integer getSectionId() {
        return sectionId;
    }

    public Integer getSuiteId() {
        return suiteId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public String getUpdatedOn() {
        return updatedOn;
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

    public static Case parse(JSONObject jsonCase) {
        return new Builder()
                .createdBy(jsonCase.get("created_by"))
                .createdOn(jsonCase.get("created_on"))
                .estimate(jsonCase.get("estimate"))
                .estimateForecast(jsonCase.get("estimate_forecast"))
                .id(jsonCase.get("id"))
                .milestoneId(jsonCase.get("milestone_id"))
                .priorityId(jsonCase.get("priority_id"))
                .refs(jsonCase.get("refs"))
                .sectionId(jsonCase.get("section_id"))
                .suiteId(jsonCase.get("suite_id"))
                .templateId(jsonCase.get("template_id"))
                .title(jsonCase.get("title"))
                .typeId(jsonCase.get("type_id"))
                .updatedBy(jsonCase.get("updated_by"))
                .updatedOn(jsonCase.get("updated_on"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("created_by", getCreatedBy());
        bundle.put("created_on", getCreatedOn());
        bundle.put("estimate", getEstimate());
        bundle.put("estimate_forecast", getEstimateForecast());
        bundle.put("id", getId());
        bundle.put("milestone_id", getMilestoneId());
        bundle.put("priority_id", getPriorityId());
        bundle.put("refs", getRefs());
        bundle.put("section_id", getSectionId());
        bundle.put("suite_id", getSuiteId());
        bundle.put("template_id", getTemplateId());
        bundle.put("title", getTitle());
        bundle.put("type_id", getTypeId());
        bundle.put("updated_by", getUpdatedBy());
        bundle.put("updated_on", getUpdatedOn());

        Log.d(TAG, Case.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private Integer createdBy;
        private String createdOn;
        private String estimate;
        private String estimateForecast;
        private Integer id;
        private Integer milestoneId;
        private Integer priorityId;
        private String refs;
        private Integer sectionId;
        private Integer suiteId;
        private Integer templateId;
        private String title;
        private Integer typeId;
        private Integer updatedBy;
        private String updatedOn;

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

        public Builder sectionId(int sectionId) {
            this.sectionId = sectionId;
            return this;
        }

        protected Builder sectionId(Object sectionId) {
            if(sectionId != null) {
                this.sectionId = Integer.parseInt(sectionId.toString());
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

        public Builder templateId(int templateId) {
            this.templateId = templateId;
            return this;
        }

        protected Builder templateId(Object templateId) {
            if(templateId != null) {
                this.templateId = Integer.parseInt(templateId.toString());
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

        public Builder updatedBy(int updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        protected Builder updatedBy(Object updatedBy) {
            if(updatedBy != null) {
                this.updatedBy = Integer.parseInt(updatedBy.toString());
            }
            return this;
        }

        public Builder updatedOn(String updatedOn) {
            this.updatedOn = updatedOn;
            return this;
        }

        protected Builder updatedOn(Object updatedOn) {
            if(updatedOn != null) {
                this.updatedOn = updatedOn.toString();
            }
            return this;
        }

        public Case build() {
            return new Case(this);
        }
    }
}