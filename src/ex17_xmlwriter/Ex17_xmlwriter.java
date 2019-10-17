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
         Leer el xml creado en el ex16 de manera que almacenemos sus datos
         en objetos product. Almacenamos a la vez esos objetos en un ArrayList
         y los mostramos en bucle
         */
        File fich1 = new File("/home/oracle/Desktop/ex16/products.xml");

        FileReader fich1FR = new FileReader(fich1);

        XMLInputFactory xmlIF = XMLInputFactory.newInstance();
        XMLStreamReader xmlSR = xmlIF.createXMLStreamReader(fich1FR);

        //importamos de nuevo la clase para usar la Clase Products??
        int tipoE = 0;
        ArrayList<Product> listaProduct = new ArrayList<>();

        Product obj;
        String codigo = "";
        String desc = "";
        Double precio = 0d;

        int cont = 0;
        
        while (xmlSR.hasNext()) {

            tipoE = xmlSR.getEventType();

            if (tipoE == XMLStreamConstants.START_ELEMENT) {

                String localName = xmlSR.getLocalName();

                if (localName == "Codigo") {

                    codigo = xmlSR.getElementText();
                    
                    cont++;

                } else if (localName == "Descripcion") {

                    desc = xmlSR.getElementText();
                    
                    cont++;

                } else if (localName == "Precio") {

                    precio = Double.parseDouble(xmlSR.getElementText());
                    
                    cont++;

                }
                
               if(cont == 3){
                   
                   listaProduct.add(new Product(codigo,desc,precio));
                   cont = 0;
               }

            }
            
            xmlSR.next();

        }
        
        xmlSR.close();
        //no lee el primero???
        System.out.println(listaProduct.get(0));

    }

}
