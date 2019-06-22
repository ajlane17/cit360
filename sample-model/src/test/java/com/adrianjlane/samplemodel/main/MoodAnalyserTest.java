package com.adrianjlane.samplemodel.main;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class MoodAnalyserTest {
    @Test
    public void testMoodAnalysis() {
        MoodAnalyser moodAnalyser = new MoodAnalyser();

        String mood = moodAnalyser.analyseMood("This is a sad message");

        Assert.assertThat(mood, CoreMatchers.is("SAD"));
    }

    @Test
    public void HappyMoods() {
        MoodAnalyser moodAnalyser = new MoodAnalyser();

        String mood = moodAnalyser.analyseMood("This is a happy message");

        Assert.assertThat(mood, CoreMatchers.is("HAPPY"));
    }
}
