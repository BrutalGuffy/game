package mm;


import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HttpClient {

    OkHttpClient client = new OkHttpClient();

    public String post(String url, String body) throws Exception {

        Request request = new Request.Builder()
                .post(RequestBody.create(MediaType.parse("application/json"), body))
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public Response get(String url) throws Exception {

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        return client.newCall(request).execute();
    }
}


