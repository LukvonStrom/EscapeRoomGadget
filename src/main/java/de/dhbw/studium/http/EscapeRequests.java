package de.dhbw.studium.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.studium.log.ILog;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class EscapeRequests {
    private ObjectMapper objectMapper = new ObjectMapper();
    private OkHttpClient client = new OkHttpClient();
    private String baseUri = "https://0mwzrjihzb.execute-api.eu-central-1.amazonaws.com/latest/escape";
    private ILog logger;

    public EscapeRequests(ILog logger) {
        this.logger = logger;
    }

    private RequestBody generateNameBody(String name) {
        String json = "{\"groupName\":\"" + name + "\"}";

        return RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), json);
    }

    private boolean doRequest(String name, String endpoint) throws IOException {
        Request request = new Request.Builder()
                .url(this.baseUri + "/" + endpoint)
                .post(this.generateNameBody(name))
                .build();

        Call call = this.client.newCall(request);
        Response response = call.execute();
        logger.log("Server answered " + response.code() + " " + response.body().string() + " while performing " + endpoint + " request.");
        return response.code() == 201;
    }


    public boolean start(String name) throws IOException {
        return this.doRequest(name, "begin");
    }

    public boolean end(String name) throws IOException {
        return this.doRequest(name, "end");
    }

    public TopListObject[] getTop() throws IOException {
        Request request = new Request.Builder()
                .url(this.baseUri)
                .build();

        Call call = this.client.newCall(request);
        Response response = call.execute();
        String jsonData = Objects.requireNonNull(response.body()).string();

        return this.objectMapper.readValue(jsonData, TopListObject[].class);
    }
}
