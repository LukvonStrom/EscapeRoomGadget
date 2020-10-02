package de.dhbw.studium.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.studium.log.ILog;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

class GroupDto{
    public String groupName;
}

public class EscapeRequests {
    private ObjectMapper objectMapper = new ObjectMapper();
    private OkHttpClient client = new OkHttpClient();
    private ObjectMapper mapper = new ObjectMapper();
    private ILog logger;

    public EscapeRequests(ILog logger, String apiUrl) {
        this.logger = logger;
        this.baseUri = apiUrl;
    }

    private RequestBody generateNameBody(String name) {
        GroupDto group = new GroupDto();
        group.groupName = name;


        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), mapper.writeValueAsString(group));
    }

    private boolean doRequest(String name, String endpoint) throws IOException {
        Request request = new Request.Builder()
                .url(this.baseUri + "/" + endpoint)
                .post(this.generateNameBody(name))
                .build();

        Call call = this.client.newCall(request);
        Response response = call.execute();
        logger.log("HTTP Antwort vom Server mit " + response.code() + " " + response.body().string() + " bei " + endpoint + " Anfrage.");
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
