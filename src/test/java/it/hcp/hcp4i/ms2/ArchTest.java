package it.hcp.hcp4i.ms2;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("it.hcp.hcp4i.ms2");

        noClasses()
            .that()
            .resideInAnyPackage("it.hcp.hcp4i.ms2.service..")
            .or()
            .resideInAnyPackage("it.hcp.hcp4i.ms2.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..it.hcp.hcp4i.ms2.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
