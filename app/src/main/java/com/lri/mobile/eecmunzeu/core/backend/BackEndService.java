package com.lri.mobile.eecmunzeu.core.backend;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.lri.mobile.eecmunzeu.core.model.Parish;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface BackEndService {

    int DEFAULT_PAGE = 0;
    int DEFAULT_SIZE = 30;

    int INSTITUTION_PAGE = 0;
    int INSTITUTION_SIZE = 500;

//    String APP_URL = "http://libre-exchange.awswouri.com";
//    String APP_URL = "http://192.168.43.107:9898";
//    String APP_URL = "http://192.168.1.202:9898";
    String APP_URL = "http://34.193.132.208:9898";
    String PRIVACY_LINK = "http://www.libreexchange.com/privacy.html";
    String CUSTOMER_AGREEMENT_LINK = "http://www.libreexchange.com/customeragreement.html";
    String HELP_LINK = "http://www.libreexchange.com/help.html";


    JsonDeserializer<Date> deserializer = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : new Date(json.getAsLong());
        }
    };

    Gson gson = new GsonBuilder()
            .setLenient()
            .registerTypeAdapter(Date.class, deserializer)
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .client(Credentials.getClient())
            .baseUrl(APP_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();



    @GET("/parish/")
    Call<List<Parish>> getAllParishes();

    @GET("/parish/1")
    Call<Parish> getParish();


}
