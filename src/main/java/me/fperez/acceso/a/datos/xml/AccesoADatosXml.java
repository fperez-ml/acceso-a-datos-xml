/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package me.fperez.acceso.a.datos.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.fperez.acceso.a.datos.xml.modelo.Persona;

/**
 *
 * @author fperez
 */
public class AccesoADatosXml {

    private static final Logger logger = Logger.getLogger(AccesoADatosXml.class.getName());
    private static final String FICHERO_CSV = ".\\datos\\personas.csv";
    private static final String ENCABEZADO_CSV = "nombre,edad,ciudad";

    public static void main(String[] args) {

        if (args.length < 1) {
            logger.log(Level.SEVERE,
                    String.format(
                            "ERROR!! no facilitado un fichero como argumento: %d",
                            args.length)
            );
            //Por simplicidad hacemos un exit(0), lo habitual exit(1)
            System.exit(0);
        }

        File ficheroXML = new File(args[0]);

        //Inicializamos un objeto XStream
        XStream xstream = new XStream();

        //Configuramos la seguridad del objeto XStream
        //más información en https://x-stream.github.io/security.html
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypes(new Class[]{Persona.class, java.util.List.class});
        xstream.ignoreUnknownElements();

        //Configuramos el objeto para el procesamiento del fichero XML pessonas.xml
        // --- ADD THESE ALIASES ---
        //Estos alias indican el tipo de dato asociado a la etiqueta
        xstream.alias("Persona", Persona.class);
        xstream.alias("list", java.util.List.class); // Optional, but good practice

        try {

            ArrayList<Persona> personas = new ArrayList<Persona>();

            personas.addAll(
                    (List<Persona>) xstream.fromXML(ficheroXML)
            );

            //Procesamos el ArrayList para escribir un fichero CSV
            BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO_CSV));
            bw.write(ENCABEZADO_CSV);
            bw.newLine();
            for (Persona persona : personas) {
                String linea = "";
                linea += persona.getNombre() + ",";
                //Transformamos el Año de Nacimiento en Edad
                int anyoActual = LocalDate.now().getYear();
                linea += Integer.toString(anyoActual - persona.getAnyoNacimiento()) + ",";
                linea += persona.getCiudad();
                System.out.println("Procesando persona: " + persona);
                // Creamos la linea
                bw.write(linea);
                bw.newLine();
            }
            //Cerramos el fichero
            bw.close();

        } catch (StreamException e) {
            String msg = "";
            msg += "ERROR!!! \n" + e.getMessage() + "\n";
            logger.log(Level.SEVERE, msg);
        } catch (Exception e) {
            String msg = "";
            msg += "ERROR!!! \n" + e + "\n";
            logger.log(Level.SEVERE, msg);
            System.out.println(msg);
        }

    }
}
