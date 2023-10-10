package io.github.codexrm_mobile.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("signin")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("refreshtoken")
    Call<TokenRefreshResponse> refreshToken(@Body TokenRefreshRequest tokenRefreshRequest);

    @POST("signout")
    Call<MessageResponse> logout(@Header("Authorization") String token);

    @POST("Sync")
    Call<SyncResponse> sync(@Query("page") Integer page, @Header("Authorization") String token, @Body SyncRequest syncRequest);

}
