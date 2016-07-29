package com.frostrocket.testrailsdk;

import android.util.Log;

import com.frostrocket.testrailsdk.http.HTTPClient;
import com.frostrocket.testrailsdk.model.Case;
import com.frostrocket.testrailsdk.model.CaseType;
import com.frostrocket.testrailsdk.model.Configuration;
import com.frostrocket.testrailsdk.model.ConfigurationGroup;
import com.frostrocket.testrailsdk.model.Entry;
import com.frostrocket.testrailsdk.model.Plan;
import com.frostrocket.testrailsdk.model.Priority;
import com.frostrocket.testrailsdk.model.Result;
import com.frostrocket.testrailsdk.model.Run;
import com.frostrocket.testrailsdk.model.Suite;
import com.frostrocket.testrailsdk.model.Test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestRail {
    private static final String TAG = TestRail.class.getSimpleName();

    private HTTPClient client;

    public TestRail(String url, String user, String password) {
        client = new HTTPClient(url, user, password);
    }

    public Case getCase(int caseId) {
        Case aCase = new Case.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendGet("get_case/" + caseId);
            Log.d(TAG, "getCase response: " + response.toString());

            aCase = Case.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return aCase;
    }

    public List<Case> getCases(int projectId, int suiteId) {
        return getCases(projectId, suiteId, -1);
    }

    public List<Case> getCases(int projectId, int suiteId, int sectionId) {
        List<Case> cases = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_cases/" + projectId + "&suite_id=" + suiteId);

            if(sectionId >= 0) {
                response = (JSONArray) client.sendGet("get_cases/" + projectId + "&suite_id=" +
                        suiteId + "&section_id=" + sectionId);
            }

            Log.d(TAG, "getCases response: " + response.toString());

            for (int i = 0; i < response.size(); i++) {
                cases.add(Case.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return cases;
    }

    public Case addCase(int sectionId, Case c) {
        Case aCase = new Case.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("add_case/" + sectionId, c.serialize());
            Log.d(TAG, "addCase response: " + response.toString());

            aCase = Case.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return aCase;
    }

    public Case updateCase(int caseId, Case c) {
        Case aCase = new Case.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("update_case/" + caseId, c.serialize());
            Log.d(TAG, "updateCase response: " + response.toString());

            aCase = Case.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return aCase;
    }

    public void deleteCase(int caseId) {
        try {
            JSONObject response = (JSONObject) client.sendPost("delete_case/" + caseId, new HashMap());
            Log.d(TAG, "deleteCase response: " + response.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public List<CaseType> getCaseTypes() {
        List<CaseType> caseTypes = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_case_types");
            Log.d(TAG, "getCaseTypes response: " + response.toString());

            for (int i = 0; i < response.size(); i++) {
                caseTypes.add(CaseType.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return caseTypes;
    }

    public List<ConfigurationGroup> getConfigs(int projectId) {
        List<ConfigurationGroup> configurationGroups = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_configs/" + projectId);
            Log.d(TAG, "getConfigs response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                configurationGroups.add(ConfigurationGroup.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return configurationGroups;
    }

    public ConfigurationGroup addConfigGroup(int projectId, ConfigurationGroup cg) {
        ConfigurationGroup configurationGroup = new ConfigurationGroup.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("add_config_group/" + projectId, cg.serialize());
            Log.d(TAG, "addConfigGroup response: " + response.toString());

            configurationGroup = ConfigurationGroup.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return configurationGroup;
    }

    public Configuration addConfig(int configGroupId, Configuration c) {
        Configuration configuration = new Configuration.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("add_config/" + configGroupId, c.serialize());
            Log.d(TAG, "addConfig response: " + response.toString());

            configuration = Configuration.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return configuration;
    }

    public ConfigurationGroup updateConfigGroup(int configGroupId, ConfigurationGroup cg) {
        ConfigurationGroup configurationGroup = new ConfigurationGroup.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("update_config_group/" + configGroupId, cg.serialize());
            Log.d(TAG, "updateConfigGroup response: " + response.toString());

            configurationGroup = ConfigurationGroup.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return configurationGroup;
    }

    public Configuration updateConfig(int configId, Configuration c) {
        Configuration configuration = new Configuration.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("update_config/" + configId, c.serialize());
            Log.d(TAG, "updateconfig response: " + response.toString());

            configuration = Configuration.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return configuration;
    }

    public void deleteConfigGroup(int configGroupId) {
        try {
            JSONObject response = (JSONObject) client.sendPost("delete_config_group/" + configGroupId, new HashMap());
            Log.d(TAG, "deleteConfigGroup response: " + response.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void deleteConfig(int configId) {
        try {
            JSONObject response = (JSONObject) client.sendPost("delete_config/" + configId, new HashMap());
            Log.d(TAG, "deleteConfig response: " + response.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Plan getPlan(int planId) {
        Plan plan = new Plan.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendGet("get_plan/" + planId);
            Log.d(TAG, "getPlan response: " + response.toString());

            plan = Plan.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return plan;
    }

    public List<Plan> getPlans(int projectId) {
        List<Plan> plans = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_plans/" + projectId);
            Log.d(TAG, "getPlans response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                plans.add(Plan.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return plans;
    }

    public Plan addPlan(int projectId, Plan p) {
        Plan plan = new Plan.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("add_plan/" + projectId, p.serialize());
            Log.d(TAG, "addPlan response: " + response.toString());

            plan = Plan.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return plan;
    }

    public Entry addPlanEntry(int planId, Entry e) {
        Entry entry = new Entry.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("add_plan_entry/" + planId, e.serialize());
            Log.d(TAG, "addPlanEntry response: " + response.toString());

            entry = Entry.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return entry;
    }

    public Plan updatePlan(int planId, Plan p) {
        Plan plan = new Plan.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("update_plan/" + planId, p.serialize());
            Log.d(TAG, "updatePlan response: " + response.toString());

            plan = Plan.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return plan;
    }

    public Entry updatePlanEntry(int planId, String entryId, Entry e) {
        Entry entry = new Entry.Builder().build();
        try {
            JSONObject response = (JSONObject) client.sendPost("update_plan_entry/" + planId + "/" + entryId, e.serialize());
            Log.d(TAG, "updatePlanEntry response: " + response.toString());

            entry = Entry.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return entry;
    }

    public Plan closePlan(int planId) {
        Plan plan = new Plan.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("close_plan/" + planId, new HashMap());
            Log.d(TAG, "closePlan response: " + response.toString());

            plan = Plan.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return plan;
    }

    public void deletePlanEntry(int planId, String entryId) {
        try {
            JSONObject response = (JSONObject) client.sendPost("delete_plan_entry/" + planId + "/" + entryId, new HashMap());
            Log.d(TAG, "deletePlanEntry response: " + response.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void deletePlan(int planId) {
        try {
            JSONObject response = (JSONObject) client.sendPost("delete_plan/" + planId, new HashMap());
            Log.d(TAG, "deletePlan response: " + response.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public List<Priority> getPriorities() {
        List<Priority> priorities = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_priorities");
            Log.d(TAG, "getPriorites response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                priorities.add(Priority.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return priorities;
    }

    public List<Result> getResults(int testId) {
        List<Result> results = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_results/" + testId);
            Log.d(TAG, "getResults response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                results.add(Result.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return results;
    }

    public List<Result> getResultsForCase(int runId, int caseId) {
        List<Result> results = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_results_for_case/" + runId + "/" + caseId);
            Log.d(TAG, "getResultsForCase response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                results.add(Result.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return results;
    }

    public List<Result> getResultsForRun(int runId) {
        List<Result> results = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_results_for_run/" + runId);
            Log.d(TAG, "getResultsForRun response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                results.add(Result.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return results;
    }

    public Result addResult(int testId, Result r) {
        Result result = new Result.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("add_result/" + testId, r.serialize());
            Log.d(TAG, "addResult response: " + response.toString());

            result = Result.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return result;
    }

    public Result addResultForCase(int runId, int caseId, Result r) {
        Result result = new Result.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("add_result_for_case/" + runId + "/" + caseId, r.serialize());
            Log.d(TAG, "addResultForCase response: " + response.toString());

            result = Result.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return result;
    }

    public List<Result> addResults(int runId, List<Result> rs) {
        List<Result> results = new ArrayList<>();
        List<HashMap> bundle = new ArrayList<>();

        for(Result result : rs) {
            bundle.add(result.serialize());
        }

        try {
            JSONObject response = (JSONObject) client.sendPost("add_results/" + runId, bundle);
            Log.d(TAG, "addResults response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                results.add(Result.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return results;
    }

    public List<Result> addResultsForCases(int runId, List<Result> rs) {
        List<Result> results = new ArrayList<>();
        List<HashMap> bundle = new ArrayList<>();

        for(Result result : rs) {
            bundle.add(result.serialize());
        }

        try {
            JSONObject response = (JSONObject) client.sendPost("add_results_for_cases/" + runId, bundle);
            Log.d(TAG, "addResultsForCases response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                results.add(Result.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return results;
    }

    public Run getRun(int runId) {
        Run run = new Run.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendGet("get_run/" + runId);
            Log.d(TAG, "getRun response: " + response.toString());

            run = Run.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return run;
    }

    public List<Run> getRuns(int projectId) {
        List<Run> runs = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_runs/" + projectId);
            Log.d(TAG, "getRuns response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                runs.add(Run.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return runs;
    }

    public Run addRun(int projectId, Run r) {
        Run run = new Run.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("add_run/" + projectId, r.serialize());
            Log.d(TAG, "addRun response: " + response.toString());

            run = Run.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return run;
    }

    public Run updateRun(int runId, Run r) {
        Run run = new Run.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("update_run/" + runId, r.serialize());
            Log.d(TAG, "updateRun response: " + response.toString());

            run = Run.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return run;
    }

    public Run closeRun(int runId) {
        Run run = new Run.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("close_run/" + runId, new HashMap());
            Log.d(TAG, "closeRun response: " + response.toString());

            run = Run.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return run;
    }

    public void deleteRun(int runId) {
        try {
            JSONObject response = (JSONObject) client.sendPost("delete_run/" + runId, new HashMap());
            Log.d(TAG, "deleteRun response: " + response.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Suite getSuite(int suiteId) {
        Suite suite = new Suite.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendGet("get_suite/" + suiteId);
            Log.d(TAG, "getSuite response: " + response.toString());

            suite = Suite.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return suite;
    }

    public List<Suite> getSuites(int projectId) {
        List<Suite> suites = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_suites/" + projectId);
            Log.d(TAG, "getSuites response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                suites.add(Suite.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return suites;
    }

    public Suite addSuite(int projectId, Suite s) {
        Suite suite = new Suite.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("add_suite/" + projectId, s.serialize());
            Log.d(TAG, "addSuite response: " + response.toString());

            suite = Suite.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return suite;
    }

    public Suite updateSuite(int suiteId, Suite s) {
        Suite suite = new Suite.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendPost("update_suite/" + suiteId, s.serialize());
            Log.d(TAG, "updateSuite response: " + response.toString());

            suite = Suite.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return suite;
    }

    public void deleteSuite(int suiteId) {
        try {
            JSONObject response = (JSONObject) client.sendPost("delete_suite/" + suiteId, new HashMap());
            Log.d(TAG, "deleteSuite response: " + response.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Test getTest(int testId) {
        Test test = new Test.Builder().build();

        try {
            JSONObject response = (JSONObject) client.sendGet("get_test/" + testId);
            Log.d(TAG, "getTest response: " + response.toString());

            test = Test.parse(response);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return test;
    }

    public List<Test> getTests(int runId) {
        List<Test> tests = new ArrayList<>();

        try {
            JSONArray response = (JSONArray) client.sendGet("get_tests/" + runId);
            Log.d(TAG, "getTests response: " + response.toString());

            for(int i = 0; i < response.size(); i++) {
                tests.add(Test.parse((JSONObject) response.get(i)));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return tests;
    }
}
