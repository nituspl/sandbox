package com.nts.drools.drools;

import java.util.Arrays;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration2 {

    private static final String DROOLS_DIRECTORY_PATH = "drools/";

    private static final List<String> RESOURCES = Arrays.asList("drools.drl");

    @Bean
    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieFileSystem kieFileSystem(KieServices kieServices) {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        RESOURCES.forEach(resource -> kieFileSystem.write(ResourceFactory.newClassPathResource(DROOLS_DIRECTORY_PATH + resource, "UTF-8")));
        return kieFileSystem;
    }

    @Bean
    public KieBuilder kieBuilder(KieServices kieServices, KieFileSystem kieFileSystem) {
        return kieServices.newKieBuilder(kieFileSystem);
    }

    @Bean
    public KieContainer getKieContainer(KieServices kieServices, KieBuilder kb) {
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
