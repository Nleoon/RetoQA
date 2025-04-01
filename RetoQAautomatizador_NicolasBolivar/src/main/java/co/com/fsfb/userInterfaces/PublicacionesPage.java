package co.com.fsfb.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class PublicacionesPage {

    // PUBLICACIONES
    public static final Target BTN_CREAR_NUEVO = Target.the("BOTON CREAR NUEVA PUBLICACION").
            locatedBy("//a[@class='btn btn-info btn img-circle btn-m-add-pub']");
    public static final Target LIST_TIPO_ARTICULO = Target.the("LISTA DE TIPO ARTICULO").
            locatedBy("#pubArticleTypeIDNew");
    public static final Target OPCIONES_TIPO_ARTICULO = Target.the("OPCIONES DE LA LISTA DE TIPO ARTICULO").
            locatedBy("#pubArticleTypeIDNew option");
    public static final Target TXT_TITULO = Target.the("TXT TITULO").
            locatedBy("//input[@id='pubPrincipalTitleNew']");

    public static final Target CHECK_TIENE_AUTORES = Target.the("CHECK TIENE AUTORES").
            locatedBy("//input[@id='pubMustHaveAuthorsNew']");
    public static final Target TXT_AUTORES = Target.the("TXT AUTORES").
            locatedBy("//div[@id='txtRichFieldAuthors']//p");
    public static final Target TXT_AFILIACION= Target.the("TXT AFILIACION").
            locatedBy("//div[@id='txtRichFieldMembership']//p");

    public static final Target CHECK_TIENE_REVISTA = Target.the("CHECK TIENE REVISTA").
            locatedBy("//input[@id='pubMustHaveMagazineNew']");
    public static final Target LIST_TIPO_REVISTA = Target.the("LISTA DE TIPO REVISTA").
            locatedBy("//select[@id='pubMagazineTypeIDNew']");
    public static final Target OPCIONES_TIPO_REVISTA = Target.the("OPCIONES DE LA LISTA DE TIPO REVISTA").
            locatedBy("#pubMagazineTypeIDNew option");
    public static final Target LIST_REVISTA = Target.the("LISTA DE TIPO REVISTA").
            locatedBy("//select[@id='pubMagazineIDNew']");
    public static final Target OPCIONES_REVISTA = Target.the("OPCIONES DE LA LISTA DE REVISTA").
            locatedBy("#pubMagazineIDNew option");
    public static final Target LIST_CATEGORIA_PUBLINDEX = Target.the("LISTA DE CATEGORIA PUBLINDEX").
            locatedBy("//select[@id='pubPublindexCategoryIDNew']");
    public static final Target OPCIONES_CATEGORIA_PUBLINDEX = Target.the("OPCIONES DE LA LISTA DE CATEGORIA PUBLINDEX").
            locatedBy("#pubPublindexCategoryIDNew option");
    public static final Target TXT_FECHA_PUBLICACION = Target.the("FECHA DE PUBLICACION").
            locatedBy("//input[@id='pubPublishDateNew']");
    public static final Target TXT_VOLUMEN = Target.the("VOLUMEN").
            locatedBy("//input[@id='pubVolumeNew']");
    public static final Target TXT_NUMERO = Target.the("NUMERO").
            locatedBy("//input[@id='pubNumberNew']");
    public static final Target TXT_PAGINAS = Target.the("PAGINAS").
            locatedBy("//input[@id='pubPageNew']");

    public static final Target TXT_ABSTRACT = Target.the("ABSTRACT").
            locatedBy("//div[@id='txtRichFieldAbstract']//p");
    public static final Target TXT_CITA = Target.the("CITA").
            locatedBy("//div[@id='txtRichFieldQuote']//p");
    public static final Target INPUT_PALABRA_CLAVE = Target.the("PALABRA CLAVE").
            locatedBy("//input[@id='pubKeywordNew']");
    public static final Target INPUT_DOI = Target.the("DOI").
            locatedBy("//input[@id='pubDOINew']");
    public static final Target LIST_ESPECIALIDAD = Target.the("ESPECIALIDAD").
            locatedBy("//select[@id='pubSpecialtyIDNew']");
    public static final Target OPCIONES_LISTA_ESPECIALIDAD = Target.the("OPCIONES ESPECIALIDAD").
            locatedBy("#pubSpecialtyIDNew option");
    public static final Target TXT_APROBACION_CCEI = Target.the("APROBACION CCEI").
            locatedBy("//input[@id='pubCCEIApprovalNew']");
    public static final Target BTN_ADJUNTAR_DOCUMENTO = Target.the("ADJUNTAR DOCUMENTO").
            locatedBy("//input[@id='pubFileNew']");
    public static final Target BTN_CREAR = Target.the("BTN CREAR").
            locatedBy("//button[@type='submit'][contains(text(),'Crear')]");
    public static final Target BTN_SI_CREAR_OK = Target.the("BTN SI CREAR").
            locatedBy("//button[@class='swal2-confirm swal2-styled']");
    public static final Target BTN_ELIMINAR_PUBLICACION = Target.the("BTN SI CREAR").
            locatedBy("//tr[@class='odd']//i[@class='fa fa-trash-alt']");

    public static final Target TXT_BUSCAR = Target.the("CAMPO DE BUSQUEDA").
            locatedBy("//input[@type='search']");
    public static final Target BTN_VISUALIZAR = Target.the("BOTON VISUALIZAR").
            locatedBy("//button[@class='btn btn-sm btn-info btn-xs-protocols BotonVisualizarPublicacion']");
    public static final Target BTN_CERRAR_VISUALIZAR = Target.the("BOTON CERRAR VISUALIZAR").
            locatedBy("(//button[@class='close'])[1]");
    public static final Target BTN_CERRAR_VISUALIZAR_2 = Target.the("BOTON CERRAR VISUALIZAR").
            locatedBy("//button[@class='btn btn-danger']");

    // PUBLICACIONES OTROS USUARIOS
    public static final Target IFRAME = Target.the("IFRAME").
            locatedBy("//iframe[@src='/Publications/IndexPublic']");
    public static final Target TXT_BUSCAR_PUBLICACIONES = Target.the("CAMPO DE BUSQUEDA PUBLICACIONES").
            locatedBy("//input[@id='searchInput']");
    public static final Target BTN_BUSCAR_PUBLICACIONES = Target.the("BUSCAR PUBLICACIONES").
            locatedBy("//button[@id='searchButton']");
    public static final Target BTN_BUSCAQUEDA_AVANZADA = Target.the("BUSQEUDA AVANZADA").
            locatedBy("//button[@id='advancedSearchButton']");
    public static final Target LIST_CAMPO_DE_BUSQUEDA = Target.the("LIST CAMPO DE BUSQUEDA").
            locatedBy("//select[@id='searchField']");
    public static final Target TXT_TERMINO_BUSQUEDA = Target.the("TERMINO DE BUSQUEDA").
            locatedBy("//input[@id='searchTerm']");
    public static final Target BTN_ADICIONAR = Target.the("ADICIONAR").
            locatedBy("//button[@id='addSearchTermButton']");
    public static final Target BTN_BUSCAR = Target.the("BUSCAR BUSQEUDA AVANZADA").
            locatedBy("//button[@id='executeAdvancedSearchButton']");
    public static final Target BTN_LIMPIAR = Target.the("LIMPIAR BUSQEUDA AVANZADA").
            locatedBy("//button[@id='clearAdvancedSearchButton']");
    public static final Target BTN_ELIMINAR_HISTORIAL = Target.the("ELIMINAR HISTORIAL").
            locatedBy("//button[@id='clearSearchHistoryButton']");
    public static final Target BTN_ABRIR_PUBLICACION = Target.the("ABRIR PUBLICACION").
            locatedBy("//i[@class='fa fa-download']");
    public static final Target BTN_DESCARGAR_PUBLICACION = Target.the("DESCARGAR PUBLICACION").
            locatedBy("//a[@id='btnDescargar']");
    public static final Target BTN_CITAR_PUBLICACION = Target.the("CITAR PUBLICACION").
            locatedBy("//button[@id='btnCitar']");
    public static final Target BTN_COPIAR_CITA = Target.the("COPIAR CITA PUBLICACION").
            locatedBy("//button[@id='btnCopiar']");
    public static final Target BTN_CERRAR_COPIAR_CITA = Target.the("CERRAR COPIAR CITA PUBLICACION").
            locatedBy("//div[@class='modal-dialog modal-dialog-centered']//button[@type='button'][normalize-space()='Cerrar']");

    // ASSERTS
    public static final Target ASSERT_CAMPOS_TABLA_BUSQUEDA = Target.the("ASSERT CAMPOS TABLA BUSQUEDA").
            locatedBy("//td[{'0'}]");
    public static final Target ASSERT_TITULO_PUBLICACION_VISTA_USUARIO = Target.the("ASSERT TITULO PUBLICACION VISTA USUARIO").
            locatedBy("//div[@class='card-body']/h5[contains(text(),'NuevaPublicacion QA')]");
    public static final Target ASSERT_AUTOR_VISTA_USUARIO = Target.the("ASSERT AUTOR PUBLICACION VISTA USUARIO").
            locatedBy("//div[@class='card-body']/h6/p[contains(text(),'NB AUTOMATIZADOR')]");
    public static final Target ASSERT_ABSTRACT_VISTA_USUARIO = Target.the("ASSERT ABSTRACT PUBLICACION VISTA USUARIO").
            locatedBy("//div[@class='card-text html-content-authors']/p[contains(text(),'ABSTRACT QA')]");
    public static final Target ASSERT_HISTORIAL_BUSQUEDA = Target.the("ASSERT HISTORIAL DE BUSQUEDA AVANZADA").
            locatedBy("//li[@class='list-group-item search-history-item']");
}
