package io.github.codexrm_mobile.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.jbibtex.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.github.codexrm_mobile.model.dto.ReferenceDTO;
import io.github.codexrm_mobile.R;
import io.github.codexrm_mobile.model.retrofit.LoginResponse;
import io.github.codexrm_mobile.model.retrofit.SyncRequest;
import io.github.codexrm_mobile.model.retrofit.TokenRefreshResponse;
import io.github.codexrm_mobile.controller.ReferenceLibraryManager;
import io.github.codexrm_mobile.model.models.AuthenticationData;
import io.github.codexrm_mobile.model.models.Reference;
import io.github.codexrm_mobile.model.models.ReferenceDialog;

public class ReferencesFragment extends Fragment {

    private ListView mReferencesList;
    private ReferencesAdapter mReferencesAdapter;
    private ReferenceDialog referenceDialog;
    private ReferenceLibraryManager manager;
    private static String importFormat;

    public ReferencesFragment() {
        this.referenceDialog = new ReferenceDialog();
        this.manager = new ReferenceLibraryManager();
    }

    public ReferenceLibraryManager getManager() { return manager; }

    public void setImportFormat(String importFormat) { ReferencesFragment.importFormat = importFormat; }

    public static ReferencesFragment newInstance() {
        ReferencesFragment fragment = new ReferencesFragment();
        // Setup parámetros
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Gets parámetros
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_references, container, false);

        // Instancia del ListView.
        mReferencesList = (ListView) root.findViewById(R.id.references_list);

        updateList();

        // Eventos
        mReferencesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Reference currentReference = mReferencesAdapter.getItem(position);
                AlertDialog infoDialog = createReferenceInfoDialog(currentReference);
                infoDialog.show();
            }
        });

        return root;
    }

    public AlertDialog createReferenceInfoDialog(Reference currentReference) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Información")
                .setItems(referenceDialog.createReferenceInfoDialog(currentReference), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),
                                "Información visualizada", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }

    public AlertDialog createExportReferenceDialog(String format) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final ArrayList<Integer> itemsSeleccionados = new ArrayList();
        ArrayList<Reference> referenceList = new ArrayList();

        int position = 0;
        final CharSequence[] items = new CharSequence[mReferencesAdapter.getCount()];

        while (position != mReferencesAdapter.getCount()) {
            Reference reference = mReferencesAdapter.getItem(position);
            referenceList.add(reference);
            items[position] = reference.getTitle();
            position++;
        }

        builder.setTitle("Seleccionar referencia(s)")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            // Guardar indice seleccionado
                            itemsSeleccionados.add(which);
                        } else if (itemsSeleccionados.contains(which)) {
                            // Remover indice sin selección
                            itemsSeleccionados.remove(Integer.valueOf(which));
                        }
                    }
                }).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            manager.exportReferenceList(referenceSelectedExport(itemsSeleccionados, referenceList), format);
                            Toast.makeText(
                                    getActivity(),
                                    "Referencias exportadas. Verifique la carpeta Download del almacenamiento interno del móvil.",
                                    Toast.LENGTH_LONG)
                                    .show();
                        } catch (IOException e) {
                            Toast.makeText(
                                    getActivity(),
                                    "Hubo un error. No se pudo realizar la exportación de las referencias",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });

        return builder.create();
    }

    public void createImportReference(File file) {
        try {
            if(file.getName().replaceAll("^.*\\.(.*)$", "$1").equals("txt")){

                if(manager.importReferences(file, importFormat)){
                    updateList();
                    Toast.makeText(
                            getActivity(),
                            "Referencias importadas.",
                            Toast.LENGTH_LONG)
                            .show();
                } else Toast.makeText(
                        getActivity(),
                        "El fichero  no contiene referencias en el formato selecionado",
                        Toast.LENGTH_LONG)
                        .show();


            } else Toast.makeText(
                    getActivity(),
                    "Debe seleccionar un archivo txt",
                    Toast.LENGTH_LONG)
                    .show();



        } catch (IOException | ParseException e) {
            Toast.makeText(
                    getActivity(),
                    "Hubo un error. No se pudo realizar la importación de las referencias",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    public SyncRequest createSyncRequest() { return manager.createSyncRequest(); }

    public void syncReferences(ArrayList<ReferenceDTO> referenceList) {
        manager.syncReferences(referenceList);
        updateList();
        Toast.makeText(getActivity(), "Referencias sincronizadas", Toast.LENGTH_LONG).show();

    }

    public void loginUser(LoginResponse response){
        String token = response.getTokenType() + " " + response.getAccessToken();
        if(manager.userLogin(new AuthenticationData(response.getId(), response.getUsername(), response.getName(), response.getLastName(), response.getEmail(), response.isEnabled(),
                token, response.getRefreshToken(), response.getTokenExpirationDate(), response.getRefreshTokenExpirationDate(), response.getRoles())))
            Toast.makeText(getActivity(), "Usuario logeado", Toast.LENGTH_LONG).show();

       else Toast.makeText(getActivity(), "Hubo un error al realizar la solicitud. Verifique usuario y contraseña", Toast.LENGTH_LONG).show();

    }

    public void logoutUser(){
        manager.userLogout();
        Toast.makeText(getActivity(), "Usuario deslogeado", Toast.LENGTH_LONG).show();
    }

    public void refreshToken(TokenRefreshResponse response){ manager.refreshToken(response); }

    public boolean verificateUserLogout(){ return manager.verificateUserLogout(); }

    public boolean verificateToken(){ return manager.verificateExpiationDate(); }

    public boolean verificateAuthentication(){ return manager.verificateAutentication(); }

    private void updateList(){
        // Inicializar el adaptador con la fuente de datos.
        mReferencesAdapter = new ReferencesAdapter(getActivity(),
                manager.getReference());

        //Relacionando la lista con el adaptador
        mReferencesList.setAdapter(mReferencesAdapter);
    }

    private ArrayList<Reference> referenceSelectedExport(ArrayList<Integer> itemsSeleccionados, ArrayList<Reference> referenceList){
        ArrayList<Reference> referenceSelectedList = new ArrayList();
        for(int i = 0; i < itemsSeleccionados.size(); i++)
            referenceSelectedList.add(referenceList.get(itemsSeleccionados.get(i).intValue()));

        return referenceSelectedList;
    }
}
