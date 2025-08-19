package com.example.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    public static Properties load() {
        Properties props = new Properties();
        try (InputStream is = PropertyLoader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
        return props;
    }
}
