package co.com.fsfb.utils;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class ManagerWebDriver {
/*
    // Clase para la inicialización del navegador solo si es necesario (pero, en general, Serenity lo maneja)
    public static WebDriver inicializarNavegador(String tipoNavegador) throws IOException {
        switch (tipoNavegador.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup(); // Configura el driver de Chrome
                return new ChromeDriver(); // Inicializa ChromeDriver manualmente solo si no está siendo manejado por Serenity
            case "firefox":
                WebDriverManager.firefoxdriver().setup(); // Configura el driver de Firefox
                return new org.openqa.selenium.firefox.FirefoxDriver(); // Inicializa FirefoxDriver manualmente solo si no está siendo manejado por Serenity
            case "edge":
                WebDriverManager.edgedriver().setup(); // Configura el driver de Edge
                return new org.openqa.selenium.edge.EdgeDriver(); // Inicializa EdgeDriver manualmente solo si no está siendo manejado por Serenity
            default:
                throw new IllegalArgumentException("Navegador no soportado: " + tipoNavegador);
        }
    }
    */
/*
    public static WebDriver inicializarNavegador(String tipoNavegador) throws IOException {
        if (driver != null) {
            return driver;
        }

        if(tipoNavegador.equalsIgnoreCase("Chrome")) {
            //DRIVER PARA EL NAVEGADOR DE CHROME
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(tipoNavegador.equalsIgnoreCase("Firefox")) {
            //DRIVER PARA EL NAVEGADOR DE FIREFOX
            WebDriverManager.firefoxdriver().setup();

        }else if(tipoNavegador.equalsIgnoreCase("Edge")) {
            //DRIVER PARA EL NAVEGADOR DE EDGE
            WebDriverManager.edgedriver().setup();

        }
        return driver;
    }
 */
}
