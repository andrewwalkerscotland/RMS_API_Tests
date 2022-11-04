package com.andrewwalkerscotland;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty",
    features = {"src/test/resources/features/getAllMedia.feature"},
    glue = {"com.andrewwalkerscotland"},
    tags = "@simpleCheck or @checkContents"
)

public class CucumberTestRunner {
}
