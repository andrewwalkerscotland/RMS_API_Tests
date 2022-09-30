package com.andrewwalkerscotland;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = "pretty",
    features = {"src/test/resources/features/getAllMedia.feature"},
    glue = {"com.andrewwalkerscotland"},
    tags = "@validateMedia"
)

public class CucumberTestRunner {
}
