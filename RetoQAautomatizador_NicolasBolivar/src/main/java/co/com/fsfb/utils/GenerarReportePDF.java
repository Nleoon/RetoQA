package co.com.fsfb.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import net.serenitybdd.screenplay.targets.Target;

public class GenerarReportePDF {
    Font font = new Font();
    static String nombre="prueba";
    static String fecha;
    static Document documento;
    static FileOutputStream archivo;
    static Paragraph titulo;
    String rutaImagen = "./imagenes/logoBMC.png";
    String hora;
    static String horaIni;
    static String horaFin;
    String fondoFooter = "./imagenes/marcaDeAgua.png";

    public void setRutaImagen(String rutaImagen)
    {
        this.rutaImagen = rutaImagen;
    }

    public class HeaderFooter extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            float x = (document.getPageSize().getLeft() + document.getPageSize().getRight()) / 2;
            font.setColor(BaseColor.GRAY);
            font.setFamily("Arial");
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Pagina: " + writer.getPageNumber(), font), x, 20, 0);
            PdfContentByte cb = writer.getDirectContentUnder();
            PdfGState trans = new PdfGState();
            try {
                Image logoFooter = Image.getInstance(fondoFooter);
                logoFooter.setAbsolutePosition(0, 0);
                logoFooter.scalePercent(23);
                trans.setFillOpacity(1f);
                cb.setGState(trans);
                cb.addImage(logoFooter);
            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }


    public void crearPlantilla(String nomTest, File rutaCarpeta)
    {
            String analyst = "Nicolas Bolivar";
            // INSTANCIAR DOCUMENTO
            documento = new Document();
            // TOMAR LA HORA DEL SISTEMA
            hora = MainClass.fechaHora();
            horaIni = MainClass.fechaHora2();

            try {
                // CREAR RUTA Y NOMBRE DEL PDF
                archivo = new FileOutputStream(rutaCarpeta+"\\" + "Reporte - "+nomTest+"-"+hora+".pdf");
                PdfWriter writer = PdfWriter.getInstance(documento, archivo);

                // **** CREAR ENCABEZADO
                // UBICACION DE LA IMAGEN
                Image header = Image.getInstance(rutaImagen);
                // TAMAÑO DE LA IMAGEN
                header.scaleToFit(80,80);
                // header.setAlignment(Chunk.ALIGN_CENTER);
                header.setWidthPercentage(75);

                // CREAR TITULO DEL PDF
                titulo = new Paragraph (nomTest + "\n\n" + "Fecha inicio: " + horaIni + "\n\n" + "Analista QA: " + analyst);
                titulo.setAlignment(1);

                // CREAR TABLA DE ENCABEZADO
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);

                PdfPCell pos1 = new PdfPCell(header);
                pos1.setHorizontalAlignment(1);
                pos1.setVerticalAlignment(2);

                PdfPCell pos2 = new PdfPCell(titulo);
                pos2.setHorizontalAlignment(1);
                pos2.setVerticalAlignment(2);

                table.addCell(pos2);
                table.addCell(pos1);

                // GENERAR MARGEN
                documento.setMargins(30, 30, 30, 30);
                // ABRIR DOCUMENTO
                documento.open();

                // INSERTAR LA IMAGEN
                documento.add(table);

                //
                HeaderFooter headerFooter = new HeaderFooter();
                writer.setPageEvent(headerFooter);

                documento.add(Chunk.NEWLINE);

            } catch (FileNotFoundException e)
            {
                System.err.println(e.getMessage());
            }
            catch (DocumentException e)
            {
                System.err.println(e.getMessage());
            }
            catch (IOException e)
            {
                System.err.println("ERROR AL LOGO: "+e.getMessage());
            }
    }

    public void crearbody(String steps, String rutaImagen) throws DocumentException, MalformedURLException, IOException
    {
        // OBTENER EL NOMBRE DEL LOCALIZADOR
//		String locator1 = locator.toString();
        // DAR FORMATO A LA FUENTE
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Chunk.ALIGN_LEFT);
        parrafo.setFont(FontFactory.getFont("Arial", 10, Font.NORMAL));
        parrafo.add("ACCION: "+steps);

        // ADICIONAR MENSAJE AL PDF
		//documento.add(parrafo);

        // INSERT IMAGEN
        // UBICACION DE LA IMAGEN
        Image imagen = Image.getInstance(rutaImagen);
        imagen.setBorderColor(BaseColor.BLACK);
        imagen.setBorder(Image.BOX);
        imagen.setBorderWidth(4);
        imagen.setBorderColor(BaseColor.BLACK);

        // TAMAÑO DE LA IMAGEN
        imagen.scaleToFit(440,1000);
        //imagen.scaleToFit(700,1000);
        imagen.setAlignment(Chunk.ALIGN_CENTER);
		//documento.add(imagen);

        // SALTO DE LINEA
        Paragraph saltoDeLinea = new Paragraph("                                                                                                                                                                                                                                                                                                                                                                                   ");
		//documento.add(saltoDeLinea);

        // CREAMOS UNA TABLA
        PdfPTable tb = new PdfPTable(1);
        // CREAMOS UNA CELDA
        PdfPCell c1 = new PdfPCell();
        c1.setBorder(0);
        c1.setIndent(1);
        // AGREGAMOS EL TEXTO Y LA IMAGEN A LA CELDA
        c1.addElement(saltoDeLinea);
        c1.addElement(saltoDeLinea);
        c1.addElement(parrafo);
        c1.addElement(saltoDeLinea);
        c1.addElement(imagen);
        // ANEXAMOS LA CELDA A LA TABLA
        tb.addCell(c1);
        // ANEXAMOS LA TABLA AL DOCUMENTO
        documento.add(tb);
        documento.add(saltoDeLinea);
    }

    public void crearbodyError(Target locator, String rutaImagen, String msnError, String steps) throws DocumentException, MalformedURLException, IOException
    {
        // OBTENER EL NOMBRE DEL LOCALIZADOR
//		String locator1 = locator.toString();

        // PARRAFO
        // DAR FORMATO A LA FUENTE
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Chunk.ALIGN_LEFT);
        parrafo.setFont(FontFactory.getFont("Arial", 10, Font.NORMAL));
        parrafo.add("ACCION: "+steps);

        // MENSAJE DE ERROR
        // DAR FORMATO A LA FUENTE
        Paragraph parrafoError = new Paragraph();
        parrafoError.setAlignment(Chunk.ALIGN_LEFT);
        parrafoError.setFont(FontFactory.getFont("Arial", 8, Font.BOLD,BaseColor.RED));
        parrafoError.add("EL MENSAJE DE ERROR ES: "+"\n"+msnError);

        // INSERT IMAGEN
        // UBICACION DE LA IMAGEN
        Image imagen = Image.getInstance(rutaImagen);
        imagen.setBorderColor(BaseColor.BLACK);
        imagen.setBorder(Image.BOX);
        imagen.setBorderWidth(4);
        imagen.setBorderColor(BaseColor.BLACK);

        // TAMAÑO DE LA IMAGEN
        imagen.scaleToFit(440,1000);
        imagen.setAlignment(Chunk.ALIGN_CENTER);

        // SALTO DE LINEA
        Paragraph saltoDeLinea = new Paragraph("                                                                                                                                                                                                                                                                                                                                                                                   ");

        // CREAMOS UNA TABLA
        PdfPTable tb = new PdfPTable(1);
        // CREAMOS UNA CELDA
        PdfPCell c1 = new PdfPCell();
        c1.setBorder(0);
        c1.setIndent(1);
        // AGREGAMOS EL TEXTO Y LA IMAGEN A LA CELDA
        c1.addElement(saltoDeLinea);
        c1.addElement(saltoDeLinea);
        c1.addElement(parrafo);
        c1.addElement(parrafoError);
        c1.addElement(saltoDeLinea);
        c1.addElement(imagen);
        // ANEXAMOS LA CELDA A LA TABLA
        tb.addCell(c1);
        // ANEXAMOS LA TABLA AL DOCUMENTO
        documento.add(tb);
        documento.add(saltoDeLinea);
    }


    public void cerrarPlantilla() throws DocumentException,MalformedURLException,IOException
    {
        documento.add(Chunk.NEWLINE);
        // DAR FORMATO A LA FUENTE
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Chunk.ALIGN_LEFT);
        parrafo.setFont(FontFactory.getFont("Arial", 10, Font.BOLD));
        parrafo.add("Fecha inicio: "+horaIni+"\n");

        // ADICIONAR MENSAJE AL PDF
        // documento.add(parrafo);
        horaFin = MainClass.fechaHora2();
        parrafo.add("Fecha fin: "+horaFin);

        // ADICIONAR MENSAJE AL PDF
        documento.add(parrafo);
        documento.close();
    }


    public static void cerrarPlantilla2() throws DocumentException,MalformedURLException,IOException
    {
        documento.add(Chunk.NEWLINE);
        // DAR FORMATO A LA FUENTE
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Chunk.ALIGN_LEFT);
        parrafo.setFont(FontFactory.getFont("Arial", 10, Font.BOLD));
        parrafo.add("Fecha inicio: "+horaIni+"\n");

        // ADICIONAR MENSAJE AL PDF
        // documento.add(parrafo);
        horaFin = MainClass.fechaHora2();
        parrafo.add("Fecha fin: "+horaFin);

        // ADICIONAR MENSAJE AL PDF
        documento.add(parrafo);
        documento.close();
    }
}
