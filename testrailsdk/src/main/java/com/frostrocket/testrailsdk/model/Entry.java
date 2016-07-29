package com.frostrocket.testrailsdk.model;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class Entry {
    private static final String TAG = Entry.class.getSimpleName();

    private final Integer assignedToId;
    private final List<Integer> caseIds;
    private final List<Integer> configIds;
    private final String description;
    private final String id;
    private final Boolean includeAll;
    private final String name;
    private final List<Run> runs;
    private final Integer suiteId;

    private Entry(Builder builder) {
        this.assignedToId = builder.assignedToId;
        this.caseIds = builder.caseIds;
        this.configIds = builder.configIds;
        this.description = builder.description;
        this.id = builder.id;
        this.includeAll = builder.includeAll;
        this.name = builder.name;
        this.runs = builder.runs;
        this.suiteId = builder.suiteId;
    }

    public Integer getAssignedToId() {
        return assignedToId;
    }

    public List<Integer> getCaseIds() {
        return caseIds;
    }

    public List<Integer> getConfigIds() {
        return configIds;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public Boolean isIncludeAll() {
        return includeAll;
    }

    public String getName() {
        return name;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public Integer getSuiteId() {
        return suiteId;
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

    public static Entry parse(JSONObject jsonEntry) {
        return new Entry.Builder()
                .assignedToId(jsonEntry.get("assignedto_id"))
                .configIds(jsonEntry.get("config_ids"))
                .description(jsonEntry.get("description"))
                .id(jsonEntry.get("id"))
                .includeAll(jsonEntry.get("include_all"))
                .name(jsonEntry.get("name"))
                .runs(jsonEntry.get("runs"))
                .suiteId(jsonEntry.get("suite_id"))
                .build();
    }

    public HashMap serialize() {
        HashMap<String, Object> bundle = new HashMap<>();

        bundle.put("assignedto_id", getAssignedToId());
        bundle.put("case_ids", getCaseIds());
        bundle.put("config_ids", getConfigIds());
        bundle.put("description", getDescription());
        bundle.put("id", getId());
        bundle.put("include_all", isIncludeAll());
        bundle.put("name", getName());

        JSONArray jsonRuns = new JSONArray();

        if(getRuns() != null) {
            for (Run r : getRuns()) {
                jsonRuns.add(r.serialize());
            }
        }

        bundle.put("runs", jsonRuns);
        bundle.put("suite_id", getSuiteId());

        Log.d(TAG, Entry.class.getSimpleName() + " bundle: " + bundle.toString());

        return bundle;
    }

    public static class Builder {
        private Integer assignedToId;
        private List<Integer> caseIds;
        private List<Integer> configIds;
        private String description;
        private String id;
        private Boolean includeAll;
        private String name;
        private List<Run> runs;
        private Integer suiteId;

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

        public Builder caseIds(List<Integer> caseIds) {
            this.caseIds = caseIds;
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

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        protected Builder id(Object id) {
            if(id != null) {
                this.id = id.toString();
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

        public Builder runs(List<Run> runs) {
            this.runs = runs;
            return this;
        }

        protected Builder runs(Object runs) {
            if(runs != null) {
                JSONArray jsonRuns = (JSONArray) runs;
                List<Run> r = new ArrayList<>();

                for(int i = 0; i < jsonRuns.size(); i++) {
                    r.add(Run.parse((JSONObject) jsonRuns.get(i)));
                }

                this.runs = r;
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

        public Entry build() {
            return new Entry(this);
        }
    }
}