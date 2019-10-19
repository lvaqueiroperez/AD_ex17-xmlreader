package ex17_xmlwriter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import javax.xml.stream.XMLStreamException;
import ex13_serializacion2.Product;
import javax.xml.stream.XMLStreamConstants;

public class Ex17_xmlwriter {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

        /*
         ler dende o ficheiro products.xml que creaches no exercicio XMLwriter 
        os datos que almacenache nel e crear un Arraylist de obxectos product 
        imprimindo as variables de ditos    obxectos dende o Arraylist
         */
 /*
         Leer el xml creado en el ex16 de manera que almacenemos sus datos
         en objetos product. Almacenamos a la vez esos objetos en un ArrayList
         y los mostramos en bucle
         */
        File fich1 = new File("C:\\Users\\luis-\\Desktop\\2ºDAM\\AD\\ex16\\products.xml");

        FileReader fich1FR = new FileReader(fich1);

        XMLInputFactory xmlIF = XMLInputFactory.newInstance();
        XMLStreamReader xmlSR = xmlIF.createXMLStreamReader(fich1FR);

        //importamos de nuevo la clase para usar la Clase Products??
        int tipoE = 0;
        ArrayList<Product> listaProduct = new ArrayList<>();

        Product obj;
        String codigo = "";
        String desc = "";
        Double precio = null;

        int cont = 0;

        while (xmlSR.hasNext()) {

            tipoE = xmlSR.getEventType();

            if (tipoE == XMLStreamConstants.START_ELEMENT) {

                //LOS ATRIBUTOS VAN DENTRO DE START ELEMENT !
                String localName = xmlSR.getLocalName();
                if (localName == "Codigo") {

                    codigo = xmlSR.getElementText();

                } else if (localName == "Descripcion") {

                    desc = xmlSR.getElementText();

                } else if (localName == "Precio") {

                    precio = Double.parseDouble(xmlSR.getElementText());

                }

                if ((codigo != "") && (desc != "") && (precio != null)) {

                    listaProduct.add(new Product(codigo, desc, precio));
                    codigo = "";
                    desc = "";
                    precio = null;

                }

            }
            xmlSR.next();
        }

        xmlSR.close();

        for (Product p : listaProduct) {

            System.out.println(p);

        }

    }

}
