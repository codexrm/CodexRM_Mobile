package io.github.codexrm_mobile.Retrofit;

import io.github.codexrm_mobile.model.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("signin")
    Call<LoginResponse> login(@Body UserLogin userLogin);

}
