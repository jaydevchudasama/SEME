package seme.vilson.david.com.sems.b_tit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RegisterUserApi extends Interceptorclass
{
    public static final String BASE_URL = "http://bartit.azurewebsites.net/Service.svc/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient()
    {
        if (retrofit == null)
        {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptorclass()).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
