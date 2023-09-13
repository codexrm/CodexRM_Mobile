package io.github.codexrm_mobile.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import io.github.codexrm_mobile.R;
import io.github.codexrm_mobile.model.Reference;
import io.github.codexrm_mobile.model.ReferenceInfoDialog;

public class ReferencesFragment extends Fragment {

   private ListView mReferencesList;
   private ReferencesAdapter mReferencesAdapter;
   private ReferenceInfoDialog referenceInfoDialog;

    public ReferencesFragment() {
       this.referenceInfoDialog = new ReferenceInfoDialog();
    }

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

        // Inicializar el adaptador con la fuente de datos.
        mReferencesAdapter = new ReferencesAdapter(getActivity(),
                ReferenceRepository.getInstance().getReference());

        //Relacionando la lista con el adaptador
        mReferencesList.setAdapter(mReferencesAdapter);

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

        builder.setTitle("Información de la referencia")
                .setItems(referenceInfoDialog.createReferenceInfoDialog(currentReference), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                getActivity(),
                                "Información visualizada",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        return builder.create();
    }
}