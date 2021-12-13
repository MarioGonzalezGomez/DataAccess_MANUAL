import com.google.gson.Gson;
import controller.DepartamentoController;
import controller.ProgramadorController;
import controller.ProyectoController;
import database.DataBaseController;
import dto.ProgramadorDTO;
import dto.ProyectoDTO;
import model.Programador;
import model.Proyecto;
import repository.DepartamentoRepository;
import service.DepartamentoService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class Facade {
    private static Facade instance;


    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }


    public void checkService() {
        DataBaseController controller = DataBaseController.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello World'");
            if (rs.isPresent()) {
                rs.get().first();
                controller.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }


    public void initDataBase() {
        String sqlFile = System.getProperty("user.dir") + File.separator + "sql" + File.separator + "init_db.sql";
        //System.out.println(sqlFile);
        DataBaseController controller = DataBaseController.getInstance();
        try {
            controller.open();
            controller.initData(sqlFile);
            controller.close();
        } catch (SQLException | FileNotFoundException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }

    }


    public void Proyectos() throws SQLException, IOException {

        ProyectoController proyectoController = ProyectoController.getInstance();

        System.out.println("GET Todos los proyectos");
        //EN JSON y tambien por consola
        System.out.println(proyectoController.getAllProyectosJSON());
        //EN XML
        proyectoController.getAllProyectosXML();

        System.out.println("*****************************************************");

        System.out.println("GET Proyecto con ID = 65521399-1fc0-4085-bc18-f0f43e211508");
        //EN JSON y tambien por consola
        System.out.println(proyectoController.getProyectoByIdJSON("65521399-1fc0-4085-bc18-f0f43e211508"));

        System.out.println("*****************************************************");


        System.out.println("GET Todos los proyectos de un programador");
        //EN JSON
        System.out.println(proyectoController.getProyectosByProgramadorJSON("7f784746-5819-b5e2-ace7-978bcd3a9be1"));
        //EN XML
        proyectoController.getProyectosByProgramadorXML("7f784746-5819-b5e2-ace7-978bcd3a9be1");



      Programador jefe = new Programador("000","PRUEBAJEFE",new Date(2005,10,12),"okkokoo",2000.00,"5318aec6-e474-4478-8882-0495657e4218");

        System.out.println("POST Insertando Proyecto");
        ProyectoDTO proyectoDTO1 = ProyectoDTO.builder()
                .id("1")
                .nombre("PROYECTO_PRUEBA111")
                .idJefe("7f784746-5819-gr43-ace7-978bcd3a9be1")
                .presupuesto(5000.00)
                .fecha_inicio(new Date(2005,10,25))
                .fecha_fin(new Date(2006,10,25))
                .build();
        System.out.println(proyectoController.postProyectoJSON(proyectoDTO1));

        System.out.println("*****************************************************");


        System.out.println("UPDATE Proyecto con ID = 65521399-1fc0-4085-bc18-f0f43e211508");
        ProyectoDTO proyectoDTO = ProyectoDTO.builder()
                .id("65521399-1fc0-4085-bc18-f0f43e211508")
                .nombre("CAMBIO NOMBRE AL RESTAURANT")
                .idJefe("503e1433-1f98-449c-b602-72ffde821e62")
                .presupuesto(5000.00)
                .fecha_inicio(new Date(2005,10,25))
                .fecha_fin(new Date(2006,10,25))
                .build();
        System.out.println(proyectoController.updateProyectoJSON(proyectoDTO));

        System.out.println("*****************************************************");

    }


    public void programadores() throws SQLException, IOException {


        ProgramadorController programadorController = ProgramadorController.getInstance();

        System.out.println("GET Todos los programadores");
        //EN JSON
        System.out.println(programadorController.getAllProgramadoresJSON());
        //EN XML
        programadorController.getAllProgramadoresXML();

        System.out.println("*****************************************************");

        System.out.println("GET Programador con ID = 638ab226-b150-4e48-90fa-7190b9f47da1");
        System.out.println(programadorController.getProgramadorByIdJSON("638ab226-b150-4e48-90fa-7190b9f47da1"));

        System.out.println("*****************************************************");

        /*System.out.println("POST Insertando programador");
        ProgramadorDTO programadorDTO = ProgramadorDTO.builder()
                .id("1")
                .nombre("PROGRAMADOR PRUEBA ")
                .fecha_alta(new Date(2005,10,25))
                .idDepartamento("5318aec6-e474-4478-8882-0495657e4218")
                .sueldo(4000.00)
                .contraseña("contraseña1234")
                .build();
        System.out.println(programadorController.postProgramadorJSON(programadorDTO));*/

        System.out.println("*****************************************************");




/*

        System.out.println("UPDATE Proyecto con ID = 65521399-1fc0-4085-bc18-f0f43e211508");
        proyectoDTO = ProyectoDTO.builder()
                .id("65521399-1fc0-4085-bc18-f0f43e211508")
                .nombre("CAMBIO NOMBRE AL RESTAURANT")
                .idJefe("503e1433-1f98-449c-b602-72ffde821e62")
                .presupuesto(5000.00)
                .fecha_inicio(new Date(2005,10,25))
                .fecha_fin(new Date(2006,10,25))
                .build();
        System.out.println(proyectoController.updateProyectoJSON(proyectoDTO));

        System.out.println("*****************************************************");
*/



    }


    public void departamentos() throws SQLException, IOException {


        DepartamentoController departamentoController = DepartamentoController.getInstance();

        System.out.println("GET Todos los departamentos");
        //EN JSON
        System.out.println(departamentoController.getAllDepartamentosJSON());
        //EN XML
        departamentoController.getAllDepartamentosXML();

        System.out.println("GET Todos los proyectos de un departamento");

        System.out.println(departamentoController.getDepartamentoProyectosJSON("Finanzas"));
        //EN XML




    }



}


