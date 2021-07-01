package com.galvanize.springjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.springjson.FlightServiceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightServiceController.class)
public class FlightServiceControllerTest {

    String string1 = "{" +
            "  \"tickets\": [" +
            "    {" +
            "      \"passenger\": {" +
            "        \"firstName\": \"Some name\"," +
            "        \"lastName\": \"Some other name\"" +
            "      }," +
            "      \"price\": 200" +
            "    }," +
            "    {" +
            "      \"passenger\": {" +
            "        \"firstName\": \"Name B\"," +
            "        \"lastName\": \"Name C\"" +
            "      }," +
            "      \"price\": 150" +
            "    }" +
            "  ]" +
            "}";

    @Autowired
    private MockMvc mvc;

    @Test
    public void testTicketTotal() throws Exception {

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(string1);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("350"));

    }

    @Test
    public void testBySerializingJackson() throws Exception {

        String string2;

        ObjectMapper objectMapper = new ObjectMapper();

        Tickets tickets = objectMapper.readValue(string1, Tickets.class);
        string2 = objectMapper.writeValueAsString(tickets);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(string2);
    }

    @Test
    public void testJsonByFileFixture() throws Exception {

        final String json = getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("350"));

        }

    private String getJSON(String path) throws Exception {
        URL jsonResourceLocation = this.getClass().getResource(path);
        File f = new File(jsonResourceLocation.getPath());
        String jsonString = Files.readString(f.toPath());
        return jsonString;
    }


}