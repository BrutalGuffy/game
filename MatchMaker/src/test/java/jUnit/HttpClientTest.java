package jUnit;

import mm.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;

import static org.junit.Assert.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class HttpClientTest {

    private ClientAndServer mockServer;

    @Before
    public void setUp() throws Exception {
        mockServer = mockServer.startClientAndServer(8080);
        new MockServerClient("localhost", 8080)
                .when(request().withMethod("POST").withPath("/")
                      .withBody("4").withHeader("\"Content-type\", \"text/plain\""))
                .respond(response().withHeaders(new Header("Content-Type", "text/plain"))
                      .withBody("required"));
    }

    @Test
    public void postRequest() throws Exception {
        HttpClient httpClient = new HttpClient();
        String response = httpClient.post("http://localhost:8080", "4");
        assertEquals(response, "required");
    }
}