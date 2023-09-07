package io.github.codexrm_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import io.github.codexrm_mobile.fragments.ReferencesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        ReferencesFragment referencesFragment = (ReferencesFragment)
                getSupportFragmentManager().findFragmentById(R.id.references_container);

        if (referencesFragment == null) {
            referencesFragment = ReferencesFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.references_container, referencesFragment)
                    .commit();
        }
    }
}