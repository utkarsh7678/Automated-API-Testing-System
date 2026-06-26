package com.myapp.service;

import com.myapp.engine.ApiExecutor;
import com.myapp.engine.ApiResponse;
import com.myapp.model.TestCase;
import com.myapp.model.TestResult;
import com.myapp.parser.JsonTestParser;
import com.myapp.validator.ResponseValidator;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestExecutionService {

    private final JsonTestParser parser = new JsonTestParser();
    private final ApiExecutor executor = new ApiExecutor();
    private final ResponseValidator validator = new ResponseValidator();

    public List<TestResult> runTests(File jsonFile) {
        List<TestCase> testCases = parser.parse(jsonFile);
        return executeAll(testCases);
    }

    public List<TestResult> runTests(InputStream inputStream) {
        List<TestCase> testCases = parser.parse(inputStream);
        return executeAll(testCases);
    }

    private List<TestResult> executeAll(List<TestCase> testCases) {
        List<TestResult> results = new ArrayList<>();

        for (TestCase testCase : testCases) {
            ApiResponse response = executor.execute(testCase);
            TestResult result = validator.validate(testCase, response);
            results.add(result);
        }

        return results;
    }
}
