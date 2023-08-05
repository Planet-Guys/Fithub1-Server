package fithub.app.firebase.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import fithub.app.firebase.dto.FcmMessageV1;
import fithub.app.firebase.dto.FcmMessageV2;
import fithub.app.firebase.dto.FcmMessageV3;
import fithub.app.firebase.service.FireBaseService;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FireBaseServiceImpl implements FireBaseService {

    @Value("${fcm.url}")
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/fithub-push-alarm/messages:send";
    private final ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(FireBaseServiceImpl.class);

    @Override
    public void sendMessageToV1(String targetToken, String title, String body, String targetView, String targetPK) throws IOException {
        String message = makeMessageV1(targetToken, title, body, targetView, targetPK);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(
                message, MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        logger.info("fire base 푸쉬알림 결과 : {}", response.body().toString());
    }

    @Override
    public void sendMessageToV2(String targetToken, String title, String body, String targetView, String targetPK) throws IOException {
        String message = makeMessageV2(targetToken, title, body, targetView, targetPK);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(
                message, MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        logger.info("fire base 푸쉬알림 결과 : {}", response.body().toString());
    }

    @Override
    public void sendMessageToV3(String targetToken, String title, String body, String targetView, String targetPK) throws IOException {
        String message = makeMessageV3(targetToken, title, body, targetView, targetPK);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(
                message, MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        logger.info("fire base 푸쉬알림 결과 : {}", response.body().toString());
    }

    private String makeMessageV1(String targeToken, String title, String body, String targetView, String targetPK) throws JsonParseException, JsonProcessingException{
        FcmMessageV1 fcmMessageV1 = FcmMessageV1.builder()
                .message(
                        FcmMessageV1.Message.builder()
                                .token(targeToken).
                                notification(FcmMessageV1.Notification.builder()
                                        .title(title)
                                        .body(body)
                                        .build()
                                ).
                                data(FcmMessageV1.Data.builder()
                                        .targetView(targetView)
                                        .targetPK(targetPK).build()
                                ).
                                build()
                )
                .validateOnly(false).build();
        return objectMapper.writeValueAsString(fcmMessageV1);
    }

    private String makeMessageV2(String targeToken, String title, String body, String targetView, String targetPK) throws JsonParseException, JsonProcessingException{
        FcmMessageV2 fcmMessageV2 = FcmMessageV2.builder()
                .message(
                        FcmMessageV2.Message.builder()
                                .token(targeToken).
                                notification(FcmMessageV2.Notification.builder()
                                        .title(title)
                                        .body(body)
                                        .targetView(targetView)
                                        .targetPK(targetPK).build()
                                ).
                                build()
                )
                .validateOnly(false).build();
        return objectMapper.writeValueAsString(fcmMessageV2);
    }

    private String makeMessageV3(String targeToken, String title, String body, String targetView, String targetPK) throws JsonParseException, JsonProcessingException{
        FcmMessageV3 fcmMessageV3 = FcmMessageV3.builder()
                .message(
                        FcmMessageV3.Message.builder()
                                .token(targeToken).
                                data(FcmMessageV3.Data.builder()
                                        .title(title)
                                        .body(body)
                                        .targetView(targetView)
                                        .targetPK(targetPK).build()
                                ).
                                build()
                )
                .validateOnly(false).build();
        return objectMapper.writeValueAsString(fcmMessageV3);
    }


    private String getAccessToken() throws IOException{
        String fireBaseConfigPath = "firebase/fithub-firebase-key.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(fireBaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
