package io.github.codexrm_mobile.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.codexrm_mobile.R;
import io.github.codexrm_mobile.model.models.Reference;

/**
 * Adaptador de References
 */
public class ReferencesAdapter extends ArrayAdapter<Reference> {

    public ReferencesAdapter(Context context, List<Reference> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_reference,
                    parent,
                    false);
        }

        // Referencias UI.
        TextView title = (TextView) convertView.findViewById(R.id.reference_title);
        TextView year = (TextView) convertView.findViewById(R.id.reference_year);
        TextView note = (TextView) convertView.findViewById(R.id.reference_note);

        // Reference actual.
        Reference reference = getItem(position);

        // Setup.
        title.setText(reference.getTitle());
        year.setText(reference.getYear());
        note.setText(reference.getNote());

        return convertView;
    }
}
