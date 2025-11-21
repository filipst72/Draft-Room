package raf.draft.dsw.model.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JacksonSerializer implements Serializer {
    private final ObjectMapper objectMapper;

    public JacksonSerializer() {
        objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Color.class, new ColorSerializer());
        simpleModule.addDeserializer(Color.class, new ColorDeserializer());
        objectMapper.registerModule(simpleModule);
    }

    public Project loadProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            return objectMapper.readValue(fileReader, Project.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveProject(Project project) {
        try (FileWriter fileWriter = new FileWriter(project.getPutanja())) {
            objectMapper.writeValue(fileWriter, project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveRoom(Room room)
    {
        try (FileWriter fileWriter = new FileWriter(room.getPutanja())) {
        objectMapper.writeValue(fileWriter, room);
    } catch (IOException e) {
        e.printStackTrace();
    }}

    public Room loadRoom(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            return objectMapper.readValue(fileReader, Room.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
