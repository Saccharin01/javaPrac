package LoAExchange.network;

import LoAExchange.dto.RequestPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;


public class RequestSender {
    private final String API_KEY;
    private final String REQUEST_URL;

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public RequestSender(String apiKey, String requestURL) {
        this.API_KEY = apiKey;
        this.REQUEST_URL = requestURL;
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String sendRequest(RequestPayload payload) throws IOException {
        String jsonBody = objectMapper.writeValueAsString(payload);

        Request requestBody = new Request.Builder()
                .url(REQUEST_URL)
                .addHeader("accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("authorization", "bearer " + API_KEY)
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();
        try (Response response = client.newCall(requestBody).execute()) {
            if(!response.isSuccessful()) {
                throw new IOException("요청 실패! 상태코드 : " + response.code());
            }
            return response.body().string();
        }
    }

}
