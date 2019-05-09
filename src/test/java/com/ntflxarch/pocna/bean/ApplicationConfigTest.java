package com.ntflxarch.pocna.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationConfigTest {
    @Autowired
    private ApplicationConfig appConfig;

    @Test
    public void shouldRetrieveThePropertyByKey() {
        String property = appConfig.getStringProperty("hello.world.message", "default message");

        assertThat(property, is("Hello Archaius World!"));
    }

    @Test
    public void whenUpdatedShouldRetrieveThePropertyNewValue() {
        String property = appConfig.getStringProperty("hello.world.message", "default message");

        assertThat(property, is("Hello Archaius World!"));
        appConfig.setStringProperty("hello.world.message", "Hello Archaius World!666");

        assertThat(property, is("Hello Archaius World!666"));
    }

    @Test
    public void shouldRetrieveDefaultValueWhenKeyIsNotPresent() {
        String property = appConfig.getStringProperty("some.key", "default message");

        assertThat(property, is("default message"));
    }
}