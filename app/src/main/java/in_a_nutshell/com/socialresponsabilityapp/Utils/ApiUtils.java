package in_a_nutshell.com.socialresponsabilityapp.Utils;

import android.support.constraint.solver.widgets.ResolutionNode;

import java.util.List;
import java.util.Map;

import in_a_nutshell.com.socialresponsabilityapp.Models.AuthorizationModel;
import in_a_nutshell.com.socialresponsabilityapp.Models.IssueModel;
import in_a_nutshell.com.socialresponsabilityapp.Models.UserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiUtils {
    public static String BASE_URL = "http://itec-api.deventure.co/api/";

    @Headers("Content-Type: aplication/json")
    @GET("Account/IsAuthorized")
    Call<AuthorizationModel> isAuthorized(
            @HeaderMap Map<String, String> headers,
            @Query("email") String email
    );

    @Headers("Content-Type: aplication/json")
    @GET("Issue/GetAll")
    Call<List<IssueModel>> getIssues();

    @POST("Token")
    @FormUrlEncoded
    Call<ResponseBody> getToken(
            @Field("username") String email,
            @Field("password") String password,
            @Field("grant_type") String grant_type
    );


}
