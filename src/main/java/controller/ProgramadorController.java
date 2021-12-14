package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ProgramadorDTO;
import dto.ProyectoDTO;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import repository.ProgramadorRepository;
import repository.ProyectoRepository;
import service.ProgramadorService;
import service.ProyectoService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramadorController {


    private static ProgramadorController controller = null;
    private final ProgramadorService programadorService;
    private FileWriter file;
    private Document data = null;
    private List<Element> listaElementos = new ArrayList<>();
    String semiPath = System.getProperty("user.dir")+ File.separator+ "src" + File.separator + "main" + File.separator+ "resources" + File.separator;


    private ProgramadorController(ProgramadorService programadorService) {
        this.programadorService = programadorService;
    }


    public static ProgramadorController getInstance() {
        if (controller == null) {
            controller = new ProgramadorController(new ProgramadorService(new ProgramadorRepository()));
        }
        return controller;
    }



    //PARA CREAR EL JSON
    public String getAllProgramadoresJSON() {
        try {

            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            String path = semiPath+"json"+ File.separator+"AllProgrammers.json";

            FileWriter writer = new FileWriter(path);
            prettyGson.toJson((programadorService.getAllProgramadores()), writer);
            writer.flush();
            writer.close();

            return prettyGson.toJson(programadorService.getAllProgramadores());



        } catch (SQLException | IOException e) {
            System.err.println("Error ProgramadorController en getAllProyectos: " + e.getMessage());
            return "Error ProgramadorController en getAllProyectos: " + e.getMessage();
        }
    }


    //PARA CREAR EL XML
    public void  getAllProgramadoresXML() throws IOException, SQLException {

        this.data = new Document();
        this.data.setRootElement(new Element("Programadores"));
        for (ProgramadorDTO p: programadorService.getAllProgramadores()
        ) {
            Element programadorElement = new Element("programador");
            programadorElement.addContent(new Element("id").setText(p.getId()));
            programadorElement.addContent(new Element("nombre").setText(p.getNombre()));
            programadorElement.addContent(new Element("fecha_Alta").setText(String.valueOf(p.getFecha_alta())));
            programadorElement.addContent(new Element("departamento").setText(String.valueOf(p.getDepartamento())));
            programadorElement.addContent(new Element("proyectos").setText(String.valueOf(p.getProyectos())));
            programadorElement.addContent(new Element("sueldo").setText(String.valueOf(p.getSueldo())));

            listaElementos.add(programadorElement);
        }

        Element root = this.data.getRootElement();
        root.addContent(listaElementos);

        String path = semiPath+"xml"+File.separator+"AllProgrammers.xml";

        writeXMLFile(path);




    }
    private XMLOutputter preProcess() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput;
    }


    public void writeXMLFile(String uri) throws IOException {
        XMLOutputter xmlOutput = this.preProcess();
        xmlOutput.output(this.data, new FileWriter(uri));
        System.out.println("Fichero XML generado con éxito");
        controller=null;

    }


    public String getProgramadorByIdJSON(String id) {
        try {

            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(programadorService.getProgramadorById(id));
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en getProyectoById: " + e.getMessage());
            return "Error ProgramadorController en getProyectoById: " + e.getMessage();
        }
    }

    public String postProgramadorJSON(ProgramadorDTO programadorDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

            return prettyGson.toJson(programadorService.postProgramador(programadorDTO));
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en postProyecto: " + e.getMessage());
            return "Error ProgramadorController en postProyecto: " + e.getMessage();
        }
    }

    public String updateProgramadorJSON(ProgramadorDTO programadorDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(programadorService.updateProgramador(programadorDTO));
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en updateProyecto: " + e.getMessage());
            return "Error ProgramadorController en updateProyecto: " + e.getMessage();
        }
    }

    public String deleteProgramadorJSON(ProgramadorDTO programadorDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(programadorService.deleteProgramador(programadorDTO));
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en deleteProyecto: " + e.getMessage());
            return "Error ProgramadorController en deleteProyecto: " + e.getMessage();
        }
    }












}
