package co.com.fsfb.questions;

import co.com.fsfb.utils.MainClass;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Enabled;
import net.serenitybdd.screenplay.questions.TextContent;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AssertsEthika {

    public static Question<Boolean> assertElementoVisibleEnPantalla(Target locator){
        return actor -> Enabled.of(locator).viewedBy(actor).asBoolean();
    }

    public static Question<String> ExtraccionTexto(Target localizador){
        return actor -> TextContent.of(localizador).viewedBy(actor).asString().trim();
    }

    public static Question<Boolean> LocalizadorHabilitado(Target localizador){
        return actor -> Enabled.of(localizador).viewedBy(actor).asBoolean();
    }

    public static Question<Boolean> LocalizadorVisible(Target localizador){
        return actor -> Visibility.of(localizador).viewedBy(actor).asBoolean();
    }

}
