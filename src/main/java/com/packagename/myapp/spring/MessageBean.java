package com.packagename.myapp.spring;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;
import com.microsoft.graph.httpcore.HttpClients;
import com.microsoft.graph.httpcore.ICoreAuthenticationProvider;
import com.packagename.myapp.spring.demo.IgComments;
import com.packagename.myapp.spring.demo.IgMedia2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

@Service
public class MessageBean implements Serializable {


    public String getMessage(Object token) throws IOException {
        OAuth2AuthenticationDetails token2 = (OAuth2AuthenticationDetails) token;
        ICoreAuthenticationProvider authenticationProvider = request -> request;
        OkHttpClient client = HttpClients.createDefault(authenticationProvider);
        Request request = new Request.Builder()
                .url("https://graph.facebook.com/v5.0/18083462881123226?fields=comments%7Busername%2Ctext%7D%2Ccomments_count&access_token=" +
                        token2.getTokenValue())
                .build();
        Response response = client.newCall(request).execute();

        Gson gson = new Gson();
        IgMedia2 igMedia2 = gson.fromJson(response.body().string(), IgMedia2.class);

        String next = igMedia2.getComments().getPaging().getNext();
        while (next != null && !next.isEmpty()) {
            request = new Request.Builder()
                    .url(next)
                    .build();
            response = client.newCall(request).execute();

            IgComments igComments = gson.fromJson(response.body().string(), IgComments.class);
            igMedia2.getComments().getData().addAll(igComments.getData());
            next = igComments.getPaging() == null ? null : igComments.getPaging().getNext();

        }
        List<IgComments.IgComment2> data = igMedia2.getComments().getData();
        int size = data.size();
        int randomNum = ThreadLocalRandom.current().nextInt(0, size);
        IgComments.IgComment2 igComment2 = data.get(randomNum);
        return "Победитель: " + igComment2.getUsername() + "\n Когда она отметила: " + igComment2.getText();


    }
}
