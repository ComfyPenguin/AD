package andy.examen2;

import lombok.Data;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

@Data
public class Runner implements Serializable {
    String name;
    int age;
    int marathons;
    String nationality;
    String notable_fact;

    public Runner(JSONObject runner) {
        this.name = runner.getString("name");
        this.age = runner.getInt("age");
        this.marathons = runner.getInt("marathons");
        this.nationality = runner.getString("nationality");
        this.notable_fact = runner.getString("notable_fact");
    }


}
