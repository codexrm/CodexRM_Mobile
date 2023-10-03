package io.github.codexrm_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.codexrm_mobile.fragments.ReferencesFragment;
import io.github.codexrm_mobile.model.AuthenticationData;
import io.github.codexrm_mobile.model.UserLogin;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private ReferencesFragment referencesFragment;
    private RequestQueue queue;
    private final String url = "https://192.168.137.1:8081/api/";

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

        queue = Volley.newRequestQueue(this);
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
                referencesFragment.createLoginDialog().show();
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
                referencesFragment.setImportFormat("RIS");
                showFileChooser();
                return true;

            case R.id.impBib:
                referencesFragment.setImportFormat("BIBTEX");
                showFileChooser();
                return true;

            case R.id.logout:
                referencesFragment.createLogoutDialog().show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFileChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try{
            startActivityForResult(Intent.createChooser(intent, "Seleccione el fichero"), 100);
        }catch (Exception exception){
            Toast.makeText(this, "Plase install a file manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == 100 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            File file = new File(uri.getPath());
            referencesFragment.createImportReference(uri.getPath());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private AuthenticationData login(UserLogin userLogin){

        HashMap<String,String> hashMapLogin = new HashMap<>();
        hashMapLogin.put("username", userLogin.getUsername());
        hashMapLogin.put("password", userLogin.getPassword());
        AuthenticationData authenticationData = new AuthenticationData();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url + "auth/signin", new JSONObject(hashMapLogin),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject mJsonObject = response;
                        try {
                            authenticationData.setId(mJsonObject.getInt("id"));
                            authenticationData.setUsername(mJsonObject.getString("username"));
                            authenticationData.setName(mJsonObject.getString("name"));
                            authenticationData.setLastName(mJsonObject.getString("lastName"));
                            authenticationData.setEmail(mJsonObject.getString("email"));
                            authenticationData.setEnabled(mJsonObject.getBoolean("enabled"));
                            authenticationData.setToken(mJsonObject.getString("type")+ " " + mJsonObject.getString("token"));
                            authenticationData.setRefreshToken(mJsonObject.getString("refreshToken"));
                            authenticationData.setTokenExpirationDate((Date) mJsonObject.get("tokenExpirationDate"));
                            authenticationData.setRefreshTokenExpirationDate((Date) mJsonObject.get("refreshTokenExpirationDate"));
                            authenticationData.setRoles((List<String>) mJsonObject.getJSONArray("roles"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
        queue.add(request);
        return authenticationData;
    }

    public static void verifyStoragePermissions(Activity activity) {
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

    private void test(){
        try {


            File fichero = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "fichero.txt");

            /**Escribir fichero*/
            FileOutputStream fos = new FileOutputStream(fichero);
            OutputStreamWriter writer = new OutputStreamWriter(fos);

            writer.write("Linea1");
            writer.write("\n"); //Salto de linea
            writer.write("Linea2");

            writer.flush();
            writer.close();

            /**Leer Fichero*/
            FileInputStream fis = new FileInputStream(fichero);
            InputStreamReader input = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(input);

            String line = reader.readLine();

            Toast.makeText(this, line, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
