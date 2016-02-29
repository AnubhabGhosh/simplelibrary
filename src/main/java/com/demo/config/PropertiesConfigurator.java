package com.demo.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PropertiesConfigurator {

    public static final String CONFIGURATION_DIR = "config";
    public static final String APPLICATION_PROPERTIES_FILENAME = "application.properties";
    public static final String MACHINE_SPECIFIC_PROPERTIES_DIR = "properties.dir";
    public static final String MACHINE_SPECIFIC_PROPERTIES_FILES = "properties.files";

    private PropertiesConfigurator(){}

    private static Resource[] resourceLocations;

    public static Resource[] getResourceLocations(){
        return resourceLocations != null ? resourceLocations : loadResources();
    }

    // privates
    private static Resource[] loadResources() {
        final List<Resource> resources = new ArrayList<Resource>();
        final String propertiesDir = System.getProperty(MACHINE_SPECIFIC_PROPERTIES_DIR);

        resources.add(new ClassPathResource(CONFIGURATION_DIR + '/' + APPLICATION_PROPERTIES_FILENAME));
        for(String envFileName : getEnvFileNames()){
            FileSystemResource fileSystemResource = new FileSystemResource(propertiesDir + "/" + envFileName);
            if(fileSystemResource.exists()){
                resources.add(fileSystemResource);
            }
        }

        return resources.toArray(new Resource[0]);
    }

    private static List<String> getEnvFileNames(){
        final String envSpecificPropsFiles = System.getProperty(MACHINE_SPECIFIC_PROPERTIES_FILES);
        return StringUtils.isNotEmpty(envSpecificPropsFiles) ? Arrays.asList(envSpecificPropsFiles.split(",")) : new ArrayList<String>();
    }
}