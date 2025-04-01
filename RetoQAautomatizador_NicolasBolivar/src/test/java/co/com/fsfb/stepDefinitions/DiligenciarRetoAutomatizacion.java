package co.com.fsfb.stepDefinitions;

import co.com.fsfb.taks.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class DiligenciarRetoAutomatizacion {

    //CREAR AVRIABLES DE NAVEGADOR Y ACTOR
    @Managed(driver= "chrome")
    WebDriver driver;

    @Before
    public void setThestage(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Usuario");
    }

    @Given("^el usuario (.*),(.*) ingresa en el sistema$")
    public void el_usuario_ingresa_en_el_sistema(String correo, String password){
        theActorInTheSpotlight().attemptsTo(DiligenciarReto.diligenciarReto(correo,password));
    }

    @When("^el usuario realiza el diligenciamiento de las preguntas$")
    public void el_realiza_el_diligenciamiento() {
    }

    @Then("^completa todos los ciclos y finaliza$")
    public void finaliza() throws Exception {
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
