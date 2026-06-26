package com.myapp.test;

import com.myapp.model.TestResult;
import com.myapp.service.TestExecutionService;

import java.io.File;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {

        String jsonPath = "src/main/resources/sample.json";

        TestExecutionService service = new TestExecutionService();
        List<TestResult> results = service.runTests(new File(jsonPath));

        for (TestResult result : results) {
            System.out.println("---------------");
            System.out.println("Test Name : " + result.getTestName());
            System.out.println("Status    : " + (result.isPassed() ? "PASS" : "FAIL"));
            System.out.println("Reason    : " + (result.getFailureReason() != null ? result.getFailureReason() : "All validations passed"));
        }
    }
}
