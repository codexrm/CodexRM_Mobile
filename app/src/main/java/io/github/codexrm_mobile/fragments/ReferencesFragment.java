package io.github.codexrm_mobile.fragments;

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

import io.github.codexrm_mobile.R;
import io.github.codexrm_mobile.Retrofit.LoginResponse;
import io.github.codexrm_mobile.Retrofit.TokenRefreshResponse;
import io.github.codexrm_mobile.controller.ReferenceLibraryManager;
import io.github.codexrm_mobile.model.AuthenticationData;
import io.github.codexrm_mobile.model.Reference;
import io.github.codexrm_mobile.model.ReferenceDialog;

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
                        } catch (IOException e) {
                            Toast.makeText(
                                    getActivity(),
                                    "Hubo un error. No se pudo realizar la exportación de las referencias",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                        Toast.makeText(
                                getActivity(),
                                "Referencias exportadas. Verifique la carpeta download del almacenamiento interno del móvil.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

        return builder.create();
    }

    public void createImportReference(File file) {
        try {
                manager.importReferences(file, importFormat);
                updateList();
                Toast.makeText(
                        getActivity(),
                        "Referencias importadas.",
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

    public AlertDialog createSyncDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Sincronizar Referencias")
                .setMessage("Desea realizar la sincronización de las referencias con el servidor central")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(manager.syncReferences())
                                Toast.makeText(getActivity(),
                                        "Sincronización realizada",
                                        Toast.LENGTH_SHORT).show();

                                else
                                    Toast.makeText(getActivity(),
                                            "Hubo un error al realizar la solicitud",
                                            Toast.LENGTH_SHORT).show();
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

    public void loginUser(LoginResponse response){
        String token = response.getTokenType() + " " + response.getAccessToken();
        if(manager.userLogin(new AuthenticationData(response.getId(), response.getUsername(), response.getName(), response.getLastName(), response.getEmail(), response.isEnabled(),
                token, response.getRefreshToken(), response.getTokenExpirationDate(), response.getRefreshTokenExpirationDate(), response.getRoles())))
            Toast.makeText(getActivity(), "Usuario logeado satisfactoriamente", Toast.LENGTH_SHORT).show();

       else Toast.makeText(getActivity(), "Hubo un error al realizar la solicitud", Toast.LENGTH_SHORT).show();

    }

    public void refreshToken(TokenRefreshResponse response){ manager.refreshToken(response); }

    public boolean verificateUserLogout(){ return manager.verificateUserLogout(); }

    public void logoutUser(){
       manager.userLogout();
        Toast.makeText(getActivity(), "Usuario deslogeado", Toast.LENGTH_SHORT).show();
    }


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
