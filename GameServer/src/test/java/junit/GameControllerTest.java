package junit;

import gameserver.GameController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class GameControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new GameController()).build();
    }

    @Test
    public void createGameIncId() throws Exception {
        GameController.setIdGenerator(0);
        MvcResult mvcRes = mockMvc.perform(post("/game/create").content("4")
                .contentType(MediaType.TEXT_PLAIN)).andReturn();
        String result = mvcRes.getResponse().getContentAsString();
        assertEquals(result, "0");

        mvcRes = mockMvc.perform(post("/game/create").content("4")
                .contentType(MediaType.TEXT_PLAIN)).andReturn();
        result = mvcRes.getResponse().getContentAsString();
        assertEquals(result, "1");

        mvcRes = mockMvc.perform(post("/game/create").content("4")
                .contentType(MediaType.TEXT_PLAIN)).andReturn();
        mvcRes = mockMvc.perform(post("/game/create").content("4")
                .contentType(MediaType.TEXT_PLAIN)).andReturn();
        result = mvcRes.getResponse().getContentAsString();
        assertEquals(result, "3");
    }


    @Test
    public void createGameReturnedId() throws Exception {
        GameController.setIdGenerator(50);
        MvcResult mvcRes = mockMvc.perform(post("/game/create").content("4")
                .contentType(MediaType.TEXT_PLAIN)).andReturn();
        String result = mvcRes.getResponse().getContentAsString();
        assertEquals(result, "50");

        GameController.setIdGenerator(25);
        mvcRes = mockMvc.perform(post("/game/create").content("4")
                .contentType(MediaType.TEXT_PLAIN)).andReturn();
        result = mvcRes.getResponse().getContentAsString();
        assertEquals(result, "25");

        mvcRes = mockMvc.perform(post("/game/create").content("4")
                .contentType(MediaType.TEXT_PLAIN)).andReturn();
        result = mvcRes.getResponse().getContentAsString();
        assertEquals(result, "26");
    }

    @Test
    public void startGame() throws Exception {
        MvcResult mvcRes = mockMvc.perform(post("/game/start").content("value")
                .contentType(MediaType.TEXT_PLAIN)).andReturn();
        String result = mvcRes.getResponse().getContentAsString();
        assertEquals(result, "value");
    }
}