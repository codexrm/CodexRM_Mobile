package io.github.codexrm_mobile.Retrofit;

import io.github.codexrm_mobile.model.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("signin")
    Call<LoginResponse> login(@Body UserLogin userLogin);

    @POST("refreshtoken")
    Call<TokenRefreshResponse> refreshToken(@Body TokenRefreshRequest tokenRefreshRequest);

    @POST("signout")
    Call<MessageResponse> logout(@Header("Authorization") String token);

}
