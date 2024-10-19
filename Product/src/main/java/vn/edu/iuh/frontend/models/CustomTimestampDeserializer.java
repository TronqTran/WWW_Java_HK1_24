package vn.edu.iuh.frontend.models;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomTimestampDeserializer extends JsonDeserializer<Timestamp> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    @Override
    public Timestamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String timestampString = p.getText();
        timestampString = timestampString.replace("[UTC]", ""); // remove the [UTC] part
        try {
            Date date = dateFormat.parse(timestampString);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new JsonParseException(p, "Failed to parse timestamp string", e);
        }
    }
}
