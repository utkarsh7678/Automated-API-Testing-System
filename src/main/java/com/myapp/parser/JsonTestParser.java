package com.myapp.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.myapp.model.TestCase;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonTestParser {

    private final Gson gson = new Gson();
    private final Type listType = new TypeToken<List<TestCase>>() {}.getType();

    public List<TestCase> parse(File jsonFile) {
        try (FileReader reader = new FileReader(jsonFile)) {
            return parse(reader);
        } catch (Exception e) {
            throw new RuntimeException(
                "Failed to parse JSON test file: " + e.getMessage(), e
            );
        }
    }

    public List<TestCase> parse(InputStream inputStream) {
        try (InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8")) {
            return parse(reader);
        } catch (Exception e) {
            throw new RuntimeException(
                "Failed to parse JSON test file: " + e.getMessage(), e
            );
        }
    }

    private List<TestCase> parse(Reader reader) {
        JsonElement root = JsonParser.parseReader(reader);
        JsonArray testArray;

        if (root.isJsonArray()) {
            testArray = root.getAsJsonArray();
        } else if (root.isJsonObject() && root.getAsJsonObject().has("tests")) {
            testArray = root.getAsJsonObject().getAsJsonArray("tests");
        } else {
            throw new RuntimeException(
                "JSON must be an array of tests or an object with a \"tests\" array"
            );
        }

        List<TestCase> testCases = gson.fromJson(testArray, listType);

        if (testCases == null || testCases.isEmpty()) {
            throw new RuntimeException("JSON file contains no test cases");
        }

        return testCases;
    }
}
