package controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DepartamentoDTO;
import dto.ProyectoDTO;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import repository.DepartamentoRepository;
import service.DepartamentoService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoController {

    private static DepartamentoController controller = null;
    private FileWriter file;

    private List<Element> listaAllDepartamentos = new ArrayList<>();
    private List<Element> listaJefesByDepartment = new ArrayList<>();
    private List<Element> listaproyectosByDepartment = new ArrayList<>();
    String semiPath = System.getProperty("user.dir")+ File.separator+ "src" + File.separator + "main" + File.separator+ "resources" + File.separator ;


    // Mi Servicio unido al repositorio
    private DepartamentoService departamentoService;
    // Eliminamos los campso que qno queremos que salgan en el JSON
    ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return field.getName().startsWith("password")
                    || field.getName().startsWith("user_id")
                    || field.getName().startsWith("category_id");
        }
    };

    // Implementamos nuestro Singleton para el controlador
    private DepartamentoController(DepartamentoService commentService) {
        this.departamentoService = commentService;
    }

    public static DepartamentoController getInstance() {
        if (controller == null) {
            controller = new DepartamentoController(new DepartamentoService(new DepartamentoRepository()));
        }
        return controller;
    }

    //PARA CREAR EL JSON
    public String getAllDepartamentosJSON() {
        try {

            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            String path = semiPath+"json"+ File.separator+"AllDepartments.json";
            FileWriter writer = new FileWriter(path);
            prettyGson.toJson((departamentoService.getAllDepartamentos()), writer);
            writer.flush();
            writer.close();

            return prettyGson.toJson(departamentoService.getAllDepartamentos());



        } catch (SQLException | IOException e) {
            System.err.println("Error ProyectoController en getAllProyectos: " + e.getMessage());
            return "Error ProyectoController en getAllProyectos: " + e.getMessage();
        }
    }
    //PARA CREAR EL XML
    public void  getAllDepartamentosXML() throws IOException, SQLException {

        Document data = new Document();
        data.setRootElement(new Element("Departamentos"));
        for (DepartamentoDTO d: departamentoService.getAllDepartamentos()
        ) {
            Element deparElement = new Element("departamento");
            deparElement.addContent(new Element("id").setText(d.getId()));
            deparElement.addContent(new Element("nombre").setText(d.getNombre()));
            deparElement.addContent(new Element("jefeDepartamento").setText(d.getJefeDepartamento().toString()));
            deparElement.addContent(new Element("presupuesto").setText(String.valueOf(d.getPresupuesto())));
            deparElement.addContent(new Element("proyectosTerminados").setText(String.valueOf(d.getProyectosTerminados())));
            deparElement.addContent(new Element("proyectosDesarrollo").setText(String.valueOf(d.getProyectosDesarrollo())));
            deparElement.addContent(new Element("presupuestoAnual").setText(String.valueOf(d.getPresupuestoAnual())));
            deparElement.addContent(new Element("historicoJefes").setText(String.valueOf(d.getHistoricoJefes())));
            listaAllDepartamentos.add(deparElement);
        }

        Element root = data.getRootElement();
        root.addContent(listaAllDepartamentos);

        String path = semiPath+"xml"+File.separator+"AllDepartments.xml";

        writeXMLFile(path, data);
        listaAllDepartamentos.clear();

    }

    public String getDepartamentoProyectosJSON(String nombreDepartamento) {
        try {

            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            String path = semiPath+"json"+ File.separator+"AllProyectsByDepartment.json";
            FileWriter writer = new FileWriter(path);
            prettyGson.toJson((departamentoService.getDepartamentoProyectos(nombreDepartamento)), writer);
            writer.flush();
            writer.close();

            return prettyGson.toJson(departamentoService.getDepartamentoProyectos(nombreDepartamento));



        } catch (SQLException | IOException e) {
            System.err.println("Error ProyectoController en getAllProyectos: " + e.getMessage());
            return "Error ProyectoController en getAllProyectos: " + e.getMessage();
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
