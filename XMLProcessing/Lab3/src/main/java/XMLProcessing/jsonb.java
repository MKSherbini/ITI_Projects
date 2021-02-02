package XMLProcessing;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;

public class jsonb {
    public static void main(String[] args) {
//        simpleClass();


        genericCol();


//        // Create custom configuration
//        JsonbConfig nillableConfig = new JsonbConfig().withNullValues(true);
//
//      // Create Jsonb with custom configuration
//        Jsonb jsonb = JsonbBuilder.create(nillableConfig);
//
//      // Use it!
//        String result = jsonb.toJson(pojo);
    }

    private static void genericCol() {
        // List of dogs
        List<Dog> dogs = new ArrayList<>();
        Dog falco = new Dog("Falco", 4, false);
        Dog cassidy = new Dog("cassidy", 1, true);

        dogs.add(falco);
        dogs.add(cassidy);

        // Create Jsonb and serialize
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(dogs);
        System.out.println("serializing");
        System.out.println(result);

        System.out.println("dogs");
        // Deserialize back
        dogs = jsonb.fromJson(result, new ArrayList<Dog>() {
        }.getClass().getGenericSuperclass());

        dogs.forEach(System.out::println);

        System.out.println("re-serializing");
        System.out.println(jsonb.toJson(dogs));
    }

    private static void simpleClass() {
        // Create a dog instance
        Dog falco = new Dog("Falco", 4, false);

        // Create Jsonb and serialize
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(falco);
        System.out.println(result);

        // Deserialize back
        falco = jsonb.fromJson("{\"age\":4,\"bitable\":false,\"reading-name\":\"Falco\"}", Dog.class);

        System.out.println(falco);
    }
}
