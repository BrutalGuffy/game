package junit;

import mm.HttpClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class HttpClientTest {

   /* @Rule
    public MockServerRule mockServerRule = new MockServerRule("localhost", 8080);

    private ClientAndServer mockServer;
    private MockServerClient mockServerClient;

    @Before
    public void setUp() throws Exception {
        mockServer = mockServer.startClientAndServer(8080);
        new MockServerClient("localhost", 8080)
                .when(request().withMethod("POST").withPath("/")
                      .withBody("4").withHeader("\"Content-type\", \"text/plain\""))
                .respond(response().withHeaders(new Header("Content-Type", "text/plain"))
                      .withBody("value"));
    }

    @Test
    public void postRequest() throws Exception {
        HttpClient httpClient = new HttpClient();
        mockServerClient.when(HttpRequest.request("/foo")).respond(HttpResponse.response().withStatusCode(200).withBody("123"));
        String response = httpClient.post("http://localhost:8080/foo", "4");
        assertEquals(response, 123);
        //assertEquals(response, "value");
    }*/
}