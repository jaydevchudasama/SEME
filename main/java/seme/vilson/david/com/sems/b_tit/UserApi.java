package seme.vilson.david.com.sems.b_tit;


import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface UserApi {


    @Multipart
    @POST("User/Register")
    Call<String> createUser(@Part("Country") String country,
                            @Part("Phone") String phone);

    @Multipart
    @POST("User/Updates{Id}")
    Call<String> updateUser(@Part("Name") String Name,
                            @Part("") String phone);

}

