package seme.vilson.david.com.sems.b_tit.Networking;

import java.io.IOException;


public class Interceptorclass implements okhttp3.Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", "F7H2M54DFWY6J6K644826789Q97N8FKV");// <-- this is the important line

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}














/*

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                        httpClient.addInterceptor(new Interceptorclass() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                // Request customization: add request headers
                                Request.Builder requestBuilder = original.newBuilder()
                                        .header("Country", "F7H2M54DFWY6J6K644826789Q97N8FKV");
//                                        .header("F7H2M54DFWY6J6K644826789Q97N8FKV", "Phone");// <-- this is the important line

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        });

                        OkHttpClient client = httpClient.build();
                        //

                        retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL) //BaseURL always ends with "/"
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(client)
                                .build();

 */