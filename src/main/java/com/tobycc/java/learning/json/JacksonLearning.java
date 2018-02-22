package com.tobycc.java.learning.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tobycc.java.model.Album;
import com.tobycc.java.model.Artist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by toby.christey-clover on 22/02/2018.
 */
public class JacksonLearning {

    public void quickJavaToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        Album obj = new Album("Random Title");
        try {
            //Object to JSON in file
            mapper.writeValue(new File("c:\\file.json"), obj);

            //Object to JSON in String
            String jsonInString = mapper.writeValueAsString(obj);
        } catch(IOException e) {

        }
    }

    public void quickJSONToJava() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "{'name' : 'mkyong'}";
        try {
            //JSON from file to Object
            Album obj = mapper.readValue(new File("c:\\file.json"), Album.class);

            //JSON from URL to Object
            Album obj2 = mapper.readValue(new URL("http://mkyong.com/api/staff.json"), Album.class);

            //JSON from String to Object
            Album obj3 = mapper.readValue(jsonInString, Album.class);
        } catch(IOException e) {

        }
    }

}



class JavaToJSONObjectConvertJackson {
    public static void main(String[] args) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        Album album = new Album("Kind Of Blue");
        album.setLinks(new String[] { "link1", "link2" });
        List<String> songs = new ArrayList<String>();
        songs.add("So What");
        songs.add("Flamenco Sketches");
        songs.add("Freddie Freeloader");
        album.setSongs(songs);
        Artist artist = new Artist();
        artist.name = "Miles Davis";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        artist.birthDate = format.parse("26-05-1926");
        album.setArtist(artist);
        album.addMusician("Miles Davis", "Trumpet, Band leader");
        album.addMusician("Julian Adderley", "Alto Saxophone");
        album.addMusician("Paul Chambers", "double bass");
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        mapper.setDateFormat(outputFormat);
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
            @Override
            public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
                if (field.getFullName().equals("com.studytrails.json.jackson.Artist#name"))
                    return "Artist-Name";
                return super.nameForField(config, field, defaultName);
            }

            @Override
            public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                if (method.getAnnotated().getDeclaringClass().equals(Album.class) && defaultName.equals("title"))
                    return "Album-Title";
                return super.nameForGetterMethod(config, method, defaultName);
            }
        });

        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.writeValue(System.out, album);
    }
}

//Use class above instead of tree model, cleaner and easier to maintain entities than on the fly JSONs!
class JavaToJSONTreeModelJackson {
    public static void main(String[] args) throws IOException {
        // Create the node factory that gives us nodes.
        JsonNodeFactory factory = new JsonNodeFactory(false);

        // create a json factory to write the treenode as json. for the example
        // we just write to console
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator generator = jsonFactory.createGenerator(System.out);
        ObjectMapper mapper = new ObjectMapper();

        // the root node - album
        JsonNode album = factory.objectNode();


        ((ObjectNode) album).put("Album-Title", "Kind Of Blue");

        ArrayNode links = factory.arrayNode();
        links.add("link1").add("link2");
        ((ObjectNode) album).set("links", links);

        ObjectNode artist = factory.objectNode();
        artist.put("Artist-Name", "Miles Davis");
        artist.put("birthDate", "26 May 1926");
        ((ObjectNode) album).set("artist", artist);

        ObjectNode musicians = factory.objectNode();
        musicians.put("Julian Adderley", "Alto Saxophone");
        musicians.put("Miles Davis", "Trumpet, Band leader");
        ((ObjectNode) album).set("musicians", musicians);


        mapper.writeTree(generator, album);

    }
}

class JSONToJavaStreamParser {
    public static void main(String[] args) throws MalformedURLException, IOException {
        // Get a list of albums from free music archive. limit the results to 5
        String url = "http://freemusicarchive.org/api/get/albums.json?api_key=60BLHNQCAOUFPIBZ&limit=5";
        // get an instance of the json parser from the json factory
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(new URL(url));

        // continue parsing the token till the end of input is reached
        while (!parser.isClosed()) {
            // get the token
            JsonToken token = parser.nextToken();
            // if its the last token then we are done
            if (token == null)
                break;
            // we want to look for a field that says dataset

            if (JsonToken.FIELD_NAME.equals(token) && "dataset".equals(parser.getCurrentName())) {
                // we are entering the datasets now. The first token should be
                // start of array
                token = parser.nextToken();
                if (!JsonToken.START_ARRAY.equals(token)) {
                    // bail out
                    break;
                }
                // each element of the array is an album so the next token
                // should be {
                token = parser.nextToken();
                if (!JsonToken.START_OBJECT.equals(token)) {
                    break;
                }
                // we are now looking for a field that says "album_title". We
                // continue looking till we find all such fields. This is
                // probably not a best way to parse this json, but this will
                // suffice for this example.
                while (true) {
                    token = parser.nextToken();
                    if (token == null)
                        break;
                    if (JsonToken.FIELD_NAME.equals(token) && "album_title".equals(parser.getCurrentName())) {
                        token = parser.nextToken();
                        System.out.println(parser.getText());
                    }

                }

            }

        }

    }
}


class JavaJSONDataBinding {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        String url = "http://freemusicarchive.org/api/get/albums.json?api_key=60BLHNQCAOUFPIBZ&limit=2";
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Album albums = mapper.readValue(new URL(url), Album.class);
        Artist[] datasets = albums.getArtists();
        for (Artist dataset : datasets) {
            System.out.println(dataset.getName());
        }
    }

}
