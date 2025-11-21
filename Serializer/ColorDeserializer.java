package raf.draft.dsw.model.Serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.awt.*;
import java.io.IOException;

public  class ColorDeserializer extends JsonDeserializer<Color> {
    @Override
    public Color deserialize(JsonParser parser, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String rgb = parser.getText();
        return Color.decode(rgb);
    }
}