/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.simple.*;

/**
 *
 * @author Nico-t2b
 */
@Stateless
@Path("service")
public class Service {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/word")
    public JSONObject word(JSONObject palabra) {
        //parseo json
        String data = palabra.get("data").toString();
        JSONObject result = new JSONObject();
        if (data.length() != 4) {
            //mensaje de error
            result.put("code", "400");
            result.put("description", "bad-request");

        } else {
            //Paso a mayúscula
            data = data.toUpperCase();

            result.put("code", "200");
            result.put("description", "OK");
            result.put("data", data);
        }
        return result;
    }

    @GET
    @Consumes("application/text")
    @Produces("application/json")
    @Path("/time")
    public JSONObject hora(String palabra) {
        JSONObject result = new JSONObject();
        if (palabra != "hora") {
            result.put("code", "400");
            result.put("description", "bad-request");
        } else {
            final Date date = new Date();
            final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS zzz";
            final SimpleDateFormat sdf = new SimpleDateFormat(ISO_FORMAT);
            final TimeZone utc = TimeZone.getTimeZone("UTC");
            sdf.setTimeZone(utc);
            System.out.println(sdf.format(date));

            result.put("code", "200");
            result.put("description", "OK");
            result.put("data", sdf.format(date));

        }

        return result;
    }
}
