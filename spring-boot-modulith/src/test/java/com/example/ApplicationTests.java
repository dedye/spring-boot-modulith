package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class ApplicationTests {

    ApplicationModules modules = ApplicationModules.of(Application.class);

    @Test
    void contextLoads() {
    }

    @Test
    void verifiesModularStructure() {
        modules.verify();
    }

    @Test
    void createApplicationModuleModel() {
        modules.forEach(System.out::println);
    }

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml();
    }
}