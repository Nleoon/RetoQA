package co.com.fsfb.utils;

import co.com.fsfb.interactions.TiempoEspera;
import co.com.fsfb.questions.AssertsEthika;
import com.github.javafaker.Faker;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.hamcrest.core.IsEqual.equalTo;

public class MainClass {

    // SE EXTRAE EL DRIVER QUE SE INICIALIZA EN EL STEPDEFINITION
    //WebDriver driver = OnStage.theActorInTheSpotlight().abilityTo(BrowseTheWeb.class).getDriver();

    // METODOS PRINCIPALES ->
    public void click(Actor actor, File rutaCarpeta, String tipoScroll, Target localizador, String steps) throws Exception {
        try{
            scroll(actor,rutaCarpeta,tipoScroll,localizador,steps);
            waitIsVisible(actor,rutaCarpeta,localizador,15,steps);
            actor.attemptsTo(Click.on(localizador));
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR EL CLICK: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void clear(Actor actor, File rutaCarpeta, String tipoScroll, Target localizador, String steps) throws Exception {
        try{
            scroll(actor,rutaCarpeta,tipoScroll,localizador,steps);
            waitIsVisible(actor,rutaCarpeta,localizador,5,steps);
            actor.attemptsTo(Clear.field(localizador));
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR EL CLICK: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void clearSinEvidencia(Actor actor, File rutaCarpeta, String tipoScroll, Target localizador, String steps) throws Exception {
        try{
            scroll(actor,rutaCarpeta,tipoScroll,localizador,steps);
            waitIsVisible(actor,rutaCarpeta,localizador,5,steps);
            actor.attemptsTo(Clear.field(localizador));
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR EL CLEAR: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void sendKeys(Actor actor, File rutaCarpeta, String tipoScroll, String textoParaDigitar, Target localizador, String steps) throws Exception {
        try{
            scroll(actor,rutaCarpeta,tipoScroll,localizador,steps);
            waitIsVisible(actor,rutaCarpeta,localizador,5,steps);
            actor.attemptsTo(SendKeys.of(textoParaDigitar).into(localizador));
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR EL SENDKEY: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void selectFromOptions(Actor actor, File rutaCarpeta, String tipoScroll, String opcionParaSeleccionar,Target localizador, String steps) throws Exception {
        try{
            scroll(actor,rutaCarpeta,tipoScroll,localizador,steps);
            waitIsVisible(actor,rutaCarpeta,localizador,5,steps);
            actor.attemptsTo(SelectFromOptions.byVisibleText(opcionParaSeleccionar).from(localizador));
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA SELECCIONAR: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void seleccionarOpcionAleatoria(Actor actor, File rutaCarpeta, String tipoScroll, Target localizador, Target localizadorOptions, String steps) throws Exception {

        try{
            // OBTENER TODAS LAS OPCIONES DEL LOCALIZADOR
            List<String> opciones = Text.of(localizadorOptions).viewedBy(actor).asList();

            if (!opciones.isEmpty()) {
                // SE SELECCIONA UNA OPCION ALEATORIA
                scroll(actor,rutaCarpeta,tipoScroll,localizador,steps);
                waitIsVisible(actor,rutaCarpeta,localizador,5,steps);
                String opcionAleatoria = opciones.get(new Random().nextInt(opciones.size() - 1) + 1);
                actor.remember("opcion_seleccionada", opcionAleatoria);
                actor.attemptsTo(SelectFromOptions.byVisibleText(opcionAleatoria).from(localizador));
                actor.attemptsTo(TiempoEspera.unMomento(1));
                ScreenShot(rutaCarpeta,steps);
            } else {
                throw new RuntimeException("No se encontraron opciones en el select dado: " + localizador.getName());
            }
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }

    }

    public void waitIsVisible(Actor actor, File rutaCarpeta, Target localizador, int timeForNoMoreThan, String steps) throws Exception {
        try{
            actor.attemptsTo(WaitUntil.the(localizador, isEnabled()).forNoMoreThan(timeForNoMoreThan).seconds());
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "SE ESPERO HASTA QUE FUERA VISIBLE EL ELEMENTO DURANTE " + timeForNoMoreThan + " SEGUNDOS, Y NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    // ESPERAR HASTA QUE SEA VISIBLE CON EVIDENCIA EN EL REPORTE
    public void waitIsVisibleConEvidencia(Actor actor, File rutaCarpeta, Target localizador, int timeForNoMoreThan, String steps) throws Exception {
        try{
            actor.attemptsTo(WaitUntil.the(localizador, isEnabled()).forNoMoreThan(timeForNoMoreThan).seconds());
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "SE ESPERO HASTA QUE FUERA VISIBLE EL ELEMENTO DURANTE " + timeForNoMoreThan + " SEGUNDOS, Y NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void scroll(Actor actor, File rutaCarpeta, String tipoScroll,Target localizador, String steps) throws Exception {
        try{
            switch (tipoScroll) {
                case "to":
                    actor.attemptsTo(Scroll.to(localizador));
                    break;
                case "top":
                    actor.attemptsTo(Scroll.to(localizador).andAlignToTop());
                    break;
                case "bottom":
                    actor.attemptsTo(Scroll.to(localizador).andAlignToBottom());
                    break;
                case "no scroll":
                    System.out.println("NO SE REALIZA SCROLL");
                    break;
                default:
                    System.out.println("Tipo de scroll no valido");
            }
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR EL SCROLL: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }
/*
    public void scrollHorizontal(Actor actor, Target localizador) {
        //JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        WebElement elemento = localizador.resolveFor(actor);
        jsExecutor.executeScript("arguments[0].scrollLeft += 1000;", elemento);
    }
*/
    public void wait(Actor actor, File rutaCarpeta, String tipoWait,Target localizador, int timeForNoMoreThan, String steps) throws Exception {
        try{
            switch (tipoWait) {
                case "Is visible":
                    actor.attemptsTo(WaitUntil.the(localizador, isVisible()).forNoMoreThan(timeForNoMoreThan).seconds());
                    break;
                case "Is not visible":
                    actor.attemptsTo(WaitUntil.the(localizador, isNotVisible()).forNoMoreThan(timeForNoMoreThan).seconds());
                    break;
                case "Is enabled":
                    actor.attemptsTo(WaitUntil.the(localizador, isEnabled()).forNoMoreThan(timeForNoMoreThan).seconds());
                    break;
                case "Is not enabled":
                    actor.attemptsTo(WaitUntil.the(localizador, isNotEnabled()).forNoMoreThan(timeForNoMoreThan).seconds());
                    break;
                default:
                    System.out.println("Tipo de wait no valido");
            }
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR LA ESPERA (wait): "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void uploadFile(Actor actor, File rutaCarpeta, String rutaArchivoParaCargar, Target localizador, String steps) throws Exception {
        try{
            waitIsVisible(actor,rutaCarpeta,localizador,20,steps);
            scroll(actor, rutaCarpeta,"no scroll",localizador,steps);
            actor.attemptsTo(Upload.theFile(new File (rutaArchivoParaCargar).toPath()).to(localizador));
            //actor.attemptsTo(uploadFile(new File(rutaArchivoParaCargar),localizador));
            actor.attemptsTo(TiempoEspera.unMomento(2));
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR EL CARGUE DEL ARCHIVO: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public static Performable waitIsVisible3 (Target locator, int timeForNoMoreThan){
        return WaitUntil.the(locator, isVisible()).forNoMoreThan(timeForNoMoreThan).seconds();
    }

/*
    public void abrirNuevaPestaña(Actor actor, File rutaCarpeta, String url, String steps) throws Exception {
        try{
            JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
            String newWindow = "window.open('"+url+"')";
            jsExecutor.executeScript(newWindow);
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            System.out.println("NO FUE POSIBLE ABRIR UNA NUEVA PESTAÑA, EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }
*/
    /*
    public void cambiarPestaña(Actor actor, File rutaCarpeta, int numeroPestaña, Target localizador, String steps) throws Exception {
        try{
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(numeroPestaña)); // Cambiar a la nueva pestaña (index 2)
            waitIsVisible(actor,rutaCarpeta,localizador,10,steps);
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE CAMBIAR A LA OTRA PESTAÑA: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }
*/


    // DILIGENCIMIENTO DE FORMULARIOS
    public void seleccionarLaMitadDeLasRespuestasDeUnFormulario(Actor actor, File rutaCarpeta, Target localizador, String steps) throws Exception {
        try{
            List<WebElementFacade> opciones = localizador.resolveAllFor(actor);

            if (opciones.isEmpty()) {
                System.out.println("NO SE ENCONTRARON OPCIONES PARA EL LOCALIZADOR");
                return;
            }

            int cantidadElementos = opciones.size();
            int mitad = Math.max(1, cantidadElementos / 2);

            for (int i = 0; i < mitad; i++) {
                waitIsVisible(actor,rutaCarpeta,localizador,10,steps);
                opciones.get(i).click(); // Solo hace clic en la mitad de los elementos
                ScreenShot(rutaCarpeta,steps);
            }
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE COMPLETAR EL METODO "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void seleccionarUnaSolaRespuestaParaTodoUnFormulario(Actor actor, File rutaCarpeta, Target localizador, String tipoScroll ,String steps) throws Exception {
        try{
            List<WebElementFacade> opciones = localizador.resolveAllFor(actor);
            for (WebElement opcion : opciones) {
                scroll(actor,rutaCarpeta,tipoScroll,localizador,steps);
                waitIsVisible(actor,rutaCarpeta,localizador,10,steps);
                opcion.click();
                ScreenShot(rutaCarpeta,steps);
            }
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE COMPLETAR EL METODO "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void sendkeyEnTodosLosCamposDeTextoDeUnFormulario(Actor actor, File rutaCarpeta, String textoParaDigitar, Target localizador, String tipoScroll, String steps) throws Exception {
        try{
            List<WebElementFacade> opciones = localizador.resolveAllFor(actor);
            for (WebElement opcion : opciones) {
                scroll(actor,rutaCarpeta,tipoScroll,localizador,steps);
                waitIsVisible(actor,rutaCarpeta,localizador,10,steps);
                opcion.sendKeys(textoParaDigitar);
                ScreenShot(rutaCarpeta,steps);
            }
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE COMPLETAR EL METODO "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }


/*
    // MANEJO DE IFRAMES
    public void cambiarIframe(String idIframe) throws Exception {
        try{
            driver.switchTo().frame(idIframe);
        } catch (Exception e){
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }
*/
    // MANEJO DE IFRAMES CON SERENITYBDD
    public void cambiarIframe2(Actor actor, Target Iframe) throws Exception {
        try{
            actor.attemptsTo(
                    Switch.toFrame(Iframe.resolveFor(actor))
            );
        } catch (Exception e){
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    /*
    public void volverAlIframePrincipal() throws Exception {
        try{
            driver.switchTo().defaultContent();
        } catch (Exception e){
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }
*/

    // ALEATORIOS
    public static int numeroAleatorioEntre(int numero_minimo, int numero_maximo){
        Faker faker = new Faker(new Locale("es"));
        int numero_aleatorio = faker.number().numberBetween(numero_minimo,numero_maximo);
        return numero_aleatorio;
    }

    public static String obtenerOpcionAleatoriaArray(List<String> array) {
        Random random = new Random();
        return array.get(random.nextInt(array.size()));
    }


    //
    public static void imprimirXPathDelElemento(Actor actor, Target elemento) {
            String xpath = elemento.getCssOrXPathSelector();
            System.out.println("XPath del elemento: " + xpath);
    }
/*
    public void tiempoEspera(long segundos) throws Exception {
        try{
            driver.manage().timeouts().implicitlyWait(segundos,SECONDS);
        } catch (Exception e){
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }
*/

    // ASSERTS CON EVIDENCIA ->
    public void assertLocalizadorHabilidatoConEvidencia(Actor actor, File rutaCarpeta, String descripcion_assert, Target localizador, Boolean operador,String steps) throws Exception {
        try{
            actor.should((seeThat(descripcion_assert, AssertsEthika.LocalizadorHabilitado(localizador),equalTo(operador))));
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR LA VALIDACION: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void assertLocalizadorVisibleConEvidencia(Actor actor, File rutaCarpeta, String descripcion_assert, Target localizador, Boolean operador,String steps) throws Exception {
        try{
            actor.should((seeThat(descripcion_assert, AssertsEthika.LocalizadorVisible(localizador),equalTo(operador))));
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR LA VALIDACION: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }

    public void assertExtracionTextoConEvidencia(Actor actor, File rutaCarpeta, String descripcion_assert, Target localizador, String texto_validacion,String steps) throws Exception {
        try{
            waitIsVisible(actor,rutaCarpeta,localizador,10,steps);
            actor.should((seeThat(descripcion_assert, AssertsEthika.ExtraccionTexto(localizador),equalTo(texto_validacion))));
            ScreenShot(rutaCarpeta,steps);
        } catch (Exception e){
            ScreenShotError(rutaCarpeta, localizador, steps, "NO FUE POSIBLE LOCALIZAR EL ELEMENTO: (" +localizador+ ") PARA REALIZAR EL CLICK: "+"\n"+"### ERROR COMPLETO: " + e.toString() + " ###" );
            System.out.println("EL ERROR CAPTURADO ES: " + e.toString());
            GenerarReportePDF.cerrarPlantilla2();
            throw new InterruptedException();
        }
    }



    // METODOS PARA LA GENERACION DEL PDF ->
    public void ScreenShot(File rutaCarpeta, String steps) throws Exception {
        /*
        String hora = HoraSistema();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(rutaCarpeta+"\\"+hora+".png"));
        String rutaImagen = new File(rutaCarpeta+"\\"+hora+".png").toString();

        // GENERAR PDF --->
        // INSTANCIAMOS LA CLASE DE GENERAR PDF
        GenerarReportePDF informePdf = new GenerarReportePDF();
        // SE PROCEDE A INSERTAR EL LOCALIZADOR DE LA IMAGEN DEL ENCABEZADO EN EL PDF
        informePdf.crearbody(steps, rutaImagen);
        // ELIMINAR LA IMAGEN CREADA
        eliminarArchivo(rutaImagen);

         */
    }


    public void ScreenShotError(File rutaCarpeta, Target localizador, String steps, String msnError) throws Exception {
            /*
            String hora = HoraSistema();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(rutaCarpeta + "\\" + hora + ".png"));
            String rutaImagen = new File(rutaCarpeta + "\\" + hora + ".png").toString();
            // INSTACIAMOS LA CLASE GENERAR PDF
            GenerarReportePDF informePdf = new GenerarReportePDF();
            // SE PROCEDE A INSERTAR LOCALIZADOR HE IMAGEN EN EL PDF
            informePdf.crearbodyError(localizador, rutaImagen, msnError, "");
            // ELIMINAR IMAGEN CREADA
            eliminarArchivo(rutaImagen);
             */
    }



    // METODO PARA CREAR LA CARPETA DONDE SE ALMACENAN LOS SCREEN'S
    public static File crearCarpeta(String nomScenario)  {
        String rutaEvidencia = "./output/PDF_ScreenRecording/";
        // ALMACENAMOS LA FECHA DEL SISTEMA
        String fecha = fechaHora();
        // CREAMOS EL NOMBRE DE LA CARPETA
        String nomCarpeta = nomScenario+" - "+fecha;
        // OBTENEMOS LA RETA DE ALOJAMIENTO DE SALIDA Y EL NOMBRE DEL TEST A EJECUTAR
        File directorio = new File(rutaEvidencia+nomCarpeta);
        // CREAMOS LA CARPETA
        directorio.mkdir();
        return directorio;
    }

    // METODOD PARA ELIMINAR LAS CAPTURAS DE PANTALLA
    public void eliminarArchivo(String rutaImagen) {
        File fichero = new File(rutaImagen);
        fichero.delete();
    }

    // METODO PARA GUARDAR LA FECHA DEL SISTEMA con formato DD/mm/aa
    public static String fechaSistema() {
        // TOMAMOS LA HORA DEL SISTEMA
        LocalDate fechaSistema = LocalDate.now();
        // DEFINIR FORMATO DE HORA
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // DAR FORMATO A LA HORA DEL SISTEMA
        String fechaConFormato = fecha.format(fechaSistema);
        return fechaConFormato;
    }

    // METODO PARA GUARDAR LA FECHA DEL SISTEMA con formato DD/mm/aa
    public static String fechaSistemaConOtroFormato(String formato) {
        // TOMAMOS LA HORA DEL SISTEMA
        LocalDate fechaSistema = LocalDate.now();
        // DEFINIR FORMATO DE HORA
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern(formato);
        // DAR FORMATO A LA HORA DEL SISTEMA
        String fechaConFormato = fecha.format(fechaSistema);
        return fechaConFormato;
    }

    public static String fechaSistemaConMasAños(int añosDeMas, String formatoFecha) {
        // Tomar la fecha del sistema
        LocalDate fechaSistema = LocalDate.now();

        // Sumar 1 año
        fechaSistema = fechaSistema.plusYears(añosDeMas);

        // Definir el formato
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(formatoFecha);

        // Formatear la fecha
        return fechaSistema.format(formato);
    }

    // METODO PARA GUARDAR LA HORA DEL SISTEMA
    public static String HoraSistema() {
        // TOMAMOS LA HORA DEL SISTEMA
        LocalTime horaSistema = LocalTime.now();
        // DEFINIR FORMATO DE HORA
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("HHmmss");
        // DAR FORMATO A LA HORA DEL SISTEMA
        String hora = fecha.format(horaSistema);
        return hora;
    }

    // METODOD PARA GUARDAR LA FECHA DEL SISTEMA
    public static String fechaHora() {
        // TOMAMOS LA FECHA DEL SISTEMA
        LocalDateTime fechaSistema = LocalDateTime.now();
        // DEFINIR FORMATO FECHA
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        // DAR FORMATO A LA FECHA DEL SISTEMA
        String formatFecha = fecha.format(fechaSistema);
        return formatFecha;
    }

    // METODOD PARA GUARDAR LA FECHA DEL SISTEMA (CON OTRA ESTRUCTURA)
    public static String fechaHora2() {
        // TOMAMOS LA FECHA DEL SISTEMA
        LocalDateTime fechaSistema = LocalDateTime.now();
        // DEFINIR FORMATO FECHA
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd- HH:mm:ss");
        // DAR FORMATO A LA FECHA DEL SISTEMA
        String formatFecha = fecha.format(fechaSistema);
        return formatFecha;
    }

}
