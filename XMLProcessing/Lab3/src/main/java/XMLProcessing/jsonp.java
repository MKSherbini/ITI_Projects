//package XMLProcessing;
//
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//import javax.json.JsonWriter;
//import javax.json.stream.JsonGenerator;
//import javax.json.stream.JsonParser;
//import java.io.*;
//import java.math.BigDecimal;
//
//public class jsonp {
//    public static void main(String[] args) throws IOException {
////        jsonGen();
////        jsonParse();
//
//        jsonWriter();
//        jsonReader();
//    }
//
//    private static void jsonReader() throws FileNotFoundException {
//        JsonReader jsonReader = Json.createReader(new FileReader("test.json"));
//        var json = jsonReader.readObject();
//
//        System.out.println(json);
//        System.out.println("biteable: " + json.getBoolean("biteable"));
//        jsonReader.close();
//    }
//
//    private static void jsonWriter() throws IOException {
//        JsonObject json = Json.createObjectBuilder()
//                .add("name", "Falco")
//                .add("age", BigDecimal.valueOf(3))
//                .add("biteable", Boolean.FALSE).build();
//        String result = json.toString();
//
//        System.out.println(result);
//        JsonWriter jsonWriter = Json.createWriter(new FileWriter("test.json"));
//        jsonWriter.writeObject(json);
//        jsonWriter.close();
//    }
//
//    private static void jsonGen() throws IOException {
//        // Create Json and serialize
//
//        JsonGenerator jsonGenerator = Json.createGenerator(new FileWriter("test.json"));
//        jsonGenerator.writeStartObject()
//                .write("name", "Falco")
//                .write("age", 4)
//                .write("biteable", false)
////                .write(json)
//                .writeEnd();
//
//        jsonGenerator.close();
//    }
//
//    private static void jsonParse() throws FileNotFoundException {
//        // Parse back
////        final String result = "{\"name\":\"Falco\",\"age\":3,\"bitable\":false}";
////        final JsonParser parser = Json.createParser(new StringReader(result));
//        final JsonParser parser = Json.createParser(new FileReader("test.json"));
//
//        String key = null;
//        String value = null;
//        while (parser.hasNext()) {
//            final JsonParser.Event event = parser.next();
//            switch (event) {
//                case KEY_NAME:
//                    System.out.print("key: ");
//                    key = parser.getString();
//                    System.out.println(key);
//                    break;
//                case VALUE_NUMBER:
//                case VALUE_STRING:
//                case VALUE_TRUE:
//                case VALUE_FALSE:
//                    System.out.print("value: ");
//                    value = parser.getValue().toString();
//                    System.out.println(value);
//                    break;
//            }
//        }
//        parser.close();
//    }
//}
