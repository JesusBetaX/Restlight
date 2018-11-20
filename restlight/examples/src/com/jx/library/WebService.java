package com.jx.library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import restlight.BasicHttpStack;
import restlight.Request;
import restlight.Response;
import restlight.Restlight;

public final class WebService {

  private static WebService instance;
  
  private final Restlight restlight;

  public static final Gson GSON = new GsonBuilder()
        .setDateFormat("M/d/yy hh:mm a")
        .create();
  

  private WebService() {
    // Advertencia: Solo se debe crear una sola instancia de esta clase.
    restlight = new Restlight();
    restlight.setStack(new BasicHttpStack() {  
      final OkHttpClient client = new OkHttpClient();
      @Override
      public HttpURLConnection open(URL src) throws IOException {
        return client.open(src);
      }
    });
  }
  
  public <T> Request<T> gsonRequest(final Class<T> classOf) {
    return new Request<T>() {
      @Override
      public T parseResponse(Response<T> response) throws Exception {
        Reader json = response.charStream(getCharset());
        //String json = response.string(getCharset());
        return GSON.fromJson(json, classOf);
      }
    };
  }
  
  public Restlight restlight() {
    return restlight;
  }
  
  public static WebService getInstance() {
    if (instance == null) instance = new WebService();
    return instance;
  }
}
