package co.com.fsfb.taks;

import co.com.fsfb.interactions.TiempoEspera;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.questions.TextContent;
import org.openqa.selenium.By;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DiligenciarReto implements Task {

    private String username;
    private String password;

    public DiligenciarReto(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        // LOGIN DE LA PAGINA
       actor.attemptsTo(
               Open.url("https://tasks.evalartapp.com/automatization/"),
               SendKeys.of(username).into("//input[@placeholder='Usuario ']"),
               SendKeys.of(password).into("//input[@placeholder='Contraseña ']"),
               Click.on("//button[@type='submit']")
       );

       // IDENTIFICAR EN QUE CICLO SE ENCUENTRA
        String localizadorCiclo = TextContent.of("//p[@class='text-xl text-center text-green-500']").viewedBy(actor).asString().trim();
        String cicloActual = localizadorCiclo.split(" ")[5];
        int cicloNumero = Integer.parseInt(cicloActual);

        // SOLUCION DE OPERACION Y SENDKEY DE LA LETRA
        do {
            // IDENTIFICA EL CICLO
            System.out.println("Ejecutando el ciclo numero: " + cicloNumero);

            // IDENTIFICAR LA OPERACION MATEMATICA
            String operacionMatematica = TextContent.of("//p[@class='text-center text-xl font-bold']").viewedBy(actor).asString().trim();


            try {
                // RESOLVER LA OPERACION UTILIZANDO JS (METODO ABAJO)
                BigDecimal resultadoOperacion = resolverOperacion(operacionMatematica);
                System.out.println("El resultado de la operación es: " + resultadoOperacion);

                // SELECCIONAR LA OPCION DE RESPUESTA QUE CONCUERDA CON EL RESULTADO
                actor.attemptsTo(
                        SelectFromOptions.byVisibleText(String.valueOf(resultadoOperacion)).from(By.xpath("//select[@name='select']"))
                );
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }

            // IDENTIFICAR EL ENUNCIADO DE LAS LETRAS
            String enunciadoLetras = TextContent.of("//div[@class='flex flex-col p-4 w-2/5 bg-white rounded-md shadow-md my-5 justify-around']/p[@class='text-center text-xl']").viewedBy(actor).asString().trim();
            String[] enunciadoPorPalabras = enunciadoLetras.split(" ");
            int cantidadCaracteres = Integer.parseInt(enunciadoPorPalabras[1]);
            String letra = enunciadoPorPalabras[5].replace("\"", "");
            System.out.println("Se digitara la letra: " + letra + " " + cantidadCaracteres + " veces");

            // GENERAR EL BLOQUE DE TEXTO
            StringBuilder textoRepetido = new StringBuilder();
            for (int i = 0; i < cantidadCaracteres; i++) {
                textoRepetido.append(letra);
            }

            actor.attemptsTo(
                    SendKeys.of(textoRepetido.toString()).into(By.xpath("//textarea[@placeholder='Texto']"))
            );
/*
            // BUCLE PARA ENVIAR LA LETRA Y LA CANTIDAD DE VECES QUE INDICA EL ENUNCIADO
            for (int i = 0; i < cantidadCaracteres; i++) {
                actor.attemptsTo(
                        SendKeys.of(letra).into(By.xpath("//textarea[@placeholder='Texto']"))
                );
            }
*/
            // SCROLL Y CLICK EN ENVIAR
            actor.attemptsTo(
                    Scroll.to(By.xpath("//button[@type='submit']")).andAlignToBottom(),
                    Click.on("//button[@type='submit']")
            );

            // ACTUALIZAR EL CICLO PARA EVITAR BUCLE INFINITO
            localizadorCiclo = TextContent.of("//p[@class='text-xl text-center text-green-500']").viewedBy(actor).asString().trim();
            cicloActual = localizadorCiclo.split(" ")[5];
            cicloNumero = Integer.parseInt(cicloActual);

        } while (cicloNumero <= 10);
        System.out.println("SE EJECUTARON 10 CICLOS");
        actor.attemptsTo(TiempoEspera.unMomento(10));
    }

    // MÉTODO PARA RESOLVER LA OPERACIÓN MATEMÁTICA
    private static BigDecimal resolverOperacion(String operacion) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        Object result = engine.eval(operacion);
        return new BigDecimal(result.toString());
    }

    public static DiligenciarReto diligenciarReto(String username, String password){
        return instrumented(DiligenciarReto.class, username, password);
    }
}

