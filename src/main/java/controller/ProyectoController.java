package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ProyectoDTO;
import model.Proyecto;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import repository.ProyectoRepository;
import service.ProyectoService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProyectoController {

    private static ProyectoController controller = null;
    private final ProyectoService proyectoService;
    private FileWriter file;

    private List<Element> listaAllProyects = new ArrayList<>();
    private List<Element> listaProyectsByProgrammer = new ArrayList<>();
    String semiPath = System.getProperty("user.dir")+ File.separator+ "src" + File.separator + "main" + File.separator+ "resources" + File.separator ;



    private ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }


    public static ProyectoController getInstance() {
        if (controller == null) {
            controller = new ProyectoController(new ProyectoService(new ProyectoRepository()));
        }
        return controller;
    }



    //PARA CREAR EL JSON
    public String getAllProyectosJSON() {
        try {

            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            String path = semiPath+"json"+File.separator+"AllProyects.json";
            FileWriter writer = new FileWriter(path);
            prettyGson.toJson((proyectoService.getAllProyectos()), writer);
            writer.flush();
            writer.close();

            return prettyGson.toJson(proyectoService.getAllProyectos());



        } catch (SQLException | IOException e) {
            System.err.println("Error ProyectoController en getAllProyectos: " + e.getMessage());
            return "Error ProyectoController en getAllProyectos: " + e.getMessage();
        }
    }


    //PARA CREAR EL XML
    public void  getAllProyectosXML() throws IOException, SQLException {

        Document data = new Document();
        data.setRootElement(new Element("Proyectos"));
        for (ProyectoDTO p: proyectoService.getAllProyectos()
             ) {
            Element proyectoElement = new Element("proyecto");
            proyectoElement.addContent(new Element("nombre").setText(p.getNombre()));
            proyectoElement.addContent(new Element("id").setText(p.getId()));
            proyectoElement.addContent(new Element("presupuesto").setText(String.valueOf(p.getPresupuesto())));
            proyectoElement.addContent(new Element("fecha_inicio").setText(String.valueOf(p.getFecha_inicio())));
            proyectoElement.addContent(new Element("fecha_fin").setText(String.valueOf(p.getFecha_fin())));
            proyectoElement.addContent(new Element("jefe").setText(String.valueOf(p.getJefeProyecto())));

            listaAllProyects.add(proyectoElement);
        }

        Element root = data.getRootElement();
        root.addContent(listaAllProyects);

        String path = semiPath+"xml"+File.separator+"AllProyects.xml";

        writeXMLFile(path, data);
        listaAllProyects.clear();

    }


    public String getProyectoByIdJSON(String id) {
        try {
            String path = semiPath+"json"+File.separator+"ProyectoByIdProgramador.json";
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(path);
            prettyGson.toJson((proyectoService.getProyectoById(id)), writer);
            writer.flush();
            writer.close();

            return prettyGson.toJson(proyectoService.getProyectoById(id));
        } catch (SQLException | IOException e) {
            System.err.println("Error ProyectoController en getProyectoById: " + e.getMessage());
            return "Error ProyectoController en getProyectoById: " + e.getMessage();
        }
    }

    public String getProyectosByProgramadorJSON(String id) {
        try {


            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            String path = semiPath+"json"+File.separator+"ProyectoByProgramador.json";
            FileWriter writer = new FileWriter(path);
            prettyGson.toJson((proyectoService.getAllProyectosByProgramador(id)), writer);
            writer.flush();
            writer.close();

            return prettyGson.toJson(proyectoService.getAllProyectosByProgramador(id));

        } catch (SQLException | IOException e) {
            System.err.println("Error ProyectoController en getProyectosByProgramador: " + e.getMessage());
            return "Error ProyectoController en getProyectosByProgramador: " + e.getMessage();
        }

    }


public void getProyectosByProgramadorXML(String id) throws SQLException, IOException {
    Document dat = new Document();
    dat.setRootElement(new Element("Proyects"));
    for (Proyecto p: proyectoService.getAllProyectosByProgramador(id)
    ) {
        Element proyectoElement = new Element("proyecto");
        proyectoElement.addContent(new Element("nombre").setText(p.getNombre()));
        proyectoElement.addContent(new Element("id").setText(p.getIdProyecto()));
        proyectoElement.addContent(new Element("presupuesto").setText(String.valueOf(p.getPresupuesto())));
        proyectoElement.addContent(new Element("fecha_inicio").setText(String.valueOf(p.getFechaInicio())));
        proyectoElement.addContent(new Element("fecha_fin").setText(String.valueOf(p.getFechaFin())));

        listaProyectsByProgrammer.add(proyectoElement);
    }

    Element root = dat.getRootElement();
    root.addContent(listaProyectsByProgrammer);
    String path = semiPath+"xml"+File.separator+"ProyectoByProgramador.xml";

    writeXMLFile(path, dat);
    listaProyectsByProgrammer.clear();
}


    public String postProyectoJSON(ProyectoDTO proyectoDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

            return prettyGson.toJson(proyectoService.postProyecto(proyectoDTO));
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en postProyecto: " + e.getMessage());
            return "Error ProyectoController en postProyecto: " + e.getMessage();
        }
    }

    public String updateProyectoJSON(ProyectoDTO proyectoDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(proyectoService.updateProyecto(proyectoDTO));
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en updateProyecto: " + e.getMessage());
            return "Error ProyectoController en updateProyecto: " + e.getMessage();
        }
    }

    public String deleteProyectoJSON(ProyectoDTO proyectoDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(proyectoService.deleteProyecto(proyectoDTO));
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en deleteProyecto: " + e.getMessage());
            return "Error ProyectoController en deleteProyecto: " + e.getMessage();
        }
    }

    private XMLOutputter preProcess() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput;
    }


    public void writeXMLFile(String uri, Document data) throws IOException {
        XMLOutputter xmlOutput = this.preProcess();
        xmlOutput.output(data, new FileWriter(uri));
        System.out.println("Fichero XML generado con éxito");
        controller=null;

    }



}
