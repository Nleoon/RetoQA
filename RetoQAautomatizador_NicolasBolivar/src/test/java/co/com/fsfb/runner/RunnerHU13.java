package co.com.fsfb.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

// INDICAR QUE LA PRUEBA SE VA A EJECUTAR CON SERENITY
@RunWith(CucumberWithSerenity.class)

// CONFIGURACIÓN
@CucumberOptions(
        features = "src/test/resources/features/reto_automatizacion.feature", // UBICACION DE LOS FEATURES
        glue = "co.com.fsfb/stepDefinitions", // UBICACION DE LOS STEPS
        tags = "@RetoAutomatizacion", // SCENARIO QUE SE QUIERE PROBAR
        snippets = SnippetType.CAMELCASE // LECTURA DE PASOS EN JAVA
)

public class RunnerHU13 {
}
