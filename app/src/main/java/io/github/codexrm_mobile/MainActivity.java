package io.github.codexrm_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import io.github.codexrm_mobile.Retrofit.LoginResponse;
import io.github.codexrm_mobile.Retrofit.MessageResponse;
import io.github.codexrm_mobile.Retrofit.TokenRefreshRequest;
import io.github.codexrm_mobile.Retrofit.TokenRefreshResponse;
import io.github.codexrm_mobile.Retrofit.UserService;
import io.github.codexrm_mobile.fragments.ReferencesFragment;
import io.github.codexrm_mobile.model.UserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private ReferencesFragment referencesFragment;
    private static String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        referencesFragment = (ReferencesFragment)
                getSupportFragmentManager().findFragmentById(R.id.references_container);

        if (referencesFragment == null) {
            referencesFragment = ReferencesFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.references_container, referencesFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.login:
               createLoginDialog().show();
                return true;

            case R.id.ipAddress:
                createIpAdddressDialog().show();
                return true;

            case R.id.sync:
                referencesFragment.createSyncDialog().show();
                return true;

            case R.id.expRis:
                verifyStoragePermissions(this);
                referencesFragment.createExportReferenceDialog("RIS").show();
                return true;

            case R.id.expBib:
                verifyStoragePermissions(this);
                referencesFragment.createExportReferenceDialog("BIBTEX").show();
                return true;

            case R.id.impRis:
                verifyStoragePermissions(this);
                referencesFragment.setImportFormat("RIS");
                showFileChooser();
                return true;

            case R.id.impBib:
                verifyStoragePermissions(this);
                referencesFragment.setImportFormat("BIBTEX");
                showFileChooser();
                return true;

            case R.id.logout:
                createLogoutDialog().show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public AlertDialog createIpAdddressDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_ip_address, null);

        builder.setView(v);

        Button button = (Button) v.findViewById(R.id.buttonIpAddress);
        EditText ipAddress_input = (EditText) v.findViewById(R.id.ipAddress_input);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ipAddress_input.getText().toString().isEmpty()) {
                            ipAddress_input.setError("Campo obligatorio");
                            ipAddress_input.requestFocus();
                        } else {
                            ipAddress = (ipAddress_input.getText().toString());
                           Toast.makeText(v.getContext(), "Dirección IP establecida", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        return builder.create();
    }

    public AlertDialog createLoginDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_signin, null);

        builder.setView(v);

        Button signin = (Button) v.findViewById(R.id.buttonLogin);
        EditText username = (EditText) v.findViewById(R.id.username_input);
        EditText password = (EditText) v.findViewById(R.id.password_input);

        signin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(username.getText().toString().isEmpty()){
                            username.setError("Campo obligatorio");
                            username.requestFocus();
                        }else
                        if(password.getText().toString().isEmpty()){
                            password.setError("Campo obligatorio");
                            password.requestFocus();
                        } else{
                            if(ipAddress == null)
                                Toast.makeText(
                                        v.getContext(),
                                        "Debe establecer la Dirección IP",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            else{
                                loginRequest(new UserLogin(username.getText().toString(), password.getText().toString()));
                            }
                        }
                    }
                }
        );
        return builder.create();
    }

    public AlertDialog createLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar Sesión")
                .setMessage("Desea cerrar su sesión")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(referencesFragment.verificateUserLogout())
                                    Toast.makeText(
                                            MainActivity.this,
                                            "Usuario deslogeado del sistema",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                else{
                                    logoutRequest();

                                }
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

        return builder.create();
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Seleccione el fichero"), 100);
        } catch (Exception exception) {
            Toast.makeText(this, "Plase install a file manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            String path = uri.getLastPathSegment();
            String[] pathS = path.split(":");
            referencesFragment.createImportReference(new File(String.valueOf(Environment.getExternalStoragePublicDirectory(pathS[1]))));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void loginRequest(UserLogin userLogin) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://" + ipAddress + ":8081/api/auth/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserService client = retrofit.create(UserService.class);
        Call<LoginResponse>  call = client.login(userLogin);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               if(response.code() == 200)
                   referencesFragment.loginUser(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String error = t.getMessage();
                Toast.makeText(MainActivity.this, "Hubo un error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshTokenRequest(TokenRefreshRequest request) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://" + ipAddress + ":8081/api/auth/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserService client = retrofit.create(UserService.class);
        Call<TokenRefreshResponse>  call = client.refreshToken(request);
        call.enqueue(new Callback<TokenRefreshResponse>() {
            @Override
            public void onResponse(Call<TokenRefreshResponse> call, Response<TokenRefreshResponse> response) {
                if(response.code() == 200)
                    referencesFragment.refreshToken(response.body());
            }

            @Override
            public void onFailure(Call<TokenRefreshResponse> call, Throwable t) {
                String error = t.getMessage();
                Toast.makeText(MainActivity.this, "Hubo un error" + t, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void logoutRequest() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://" + ipAddress + ":8081/api/auth/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserService client = retrofit.create(UserService.class);
        Call<MessageResponse>  call = client.logout(referencesFragment.getManager().getAuthenticationData().getToken());
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if(response.code() == 200)
                    if(response.body().getMessage().equals("Log out successful!"))
                        referencesFragment.logoutUser();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                String error = t.getMessage();
                Toast.makeText(MainActivity.this, "Hubo un error" + t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}

