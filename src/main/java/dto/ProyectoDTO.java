package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Data;
import model.Programador;
import model.Tecnologias;
import java.sql.Date;
import java.util.UUID;

@Data
@Builder
public class ProyectoDTO {

    private String id;
    private Programador jefeProyecto;
    private String nombre;
    private Double presupuesto;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Tecnologias tecnologia;
    private String idJefe;



    public static ProyectoDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, ProyectoDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }



}
