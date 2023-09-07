package io.github.codexrm_mobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.github.codexrm_mobile.R;

public class ReferencesFragment extends Fragment {

   private ListView mReferencesList;
   private ReferencesAdapter mReferencesAdapter;

    public ReferencesFragment() {
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
        return root;
    }
}