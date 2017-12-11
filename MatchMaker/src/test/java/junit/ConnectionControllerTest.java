package junit;

import mm.ConnectionController;
import mm.MatchMaker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ConnectionControllerTest {
    /*private ClientAndServer mockServer;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new ConnectionController()).build();

        mockServer = mockServer.startClientAndServer(8080);

        new MockServerClient("localhost", 8080)
                .when(request().withMethod("POST").withPath("/game/create")
                        .withBody("4").withHeader("\"Content-type\", \"text/plain\""))
                .respond(response().withHeaders(new Header("Content-Type", "text/plain"))
                        .withBody("0"));

        Thread matchMaker = new Thread(new MatchMaker());
        matchMaker.start();
    }

    @Test
    public void returnedId() throws Exception {
        MvcResult mvcRes = mockMvc.perform(post("/matchmaker/join").content("name=a")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)).andReturn();
        String result = mvcRes.getResponse().getContentAsString();
        assertEquals(result, "0");
    }

    @After
    public void stop() throws Exception {
        mockServer.stop();
    }*/
}