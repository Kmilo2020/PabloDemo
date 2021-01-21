package com.kmilo.pablodemo.activities;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kmilo.pablodemo.R;
import com.kmilo.pablodemo.entities.Species;
import com.kmilo.pablodemo.fragments.SpeciesDetailsFragment;
import com.kmilo.pablodemo.fragments.SpeciesListFragment;
import com.kmilo.pablodemo.interfaces.IMasterDetails;
import com.kmilo.pablodemo.utilities.Utilities;

public class MasterDetailsActivity extends AppCompatActivity
        implements SpeciesListFragment.OnFragmentInteractionListener,
        SpeciesDetailsFragment.OnFragmentInteractionListener, IMasterDetails {

    SpeciesListFragment listaFragment;
    SpeciesDetailsFragment detalleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_details);

        //We validate that it is working in Portrait mode from a smartPhone
        Utilities.PORTRAIT=true;
        if(findViewById(R.id.contenedorFragment)!=null){
            if (savedInstanceState!=null){
                return;
            }
            listaFragment = new SpeciesListFragment();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.contenedorFragment,listaFragment).commit();
        }else{
            Utilities.PORTRAIT=false;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void enviarEspecie(Species especie) {
        if(detalleFragment!=null && findViewById(R.id.contenedorFragment)==null){
            detalleFragment.asignarInformacion(especie);
        }else{
            detalleFragment=new SpeciesDetailsFragment();
            Bundle bundleEnvio=new Bundle();
            bundleEnvio.putSerializable("objeto",especie);
            detalleFragment.setArguments(bundleEnvio);

            //cargamos el fragment en el Activity
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.contenedorFragment,detalleFragment).addToBackStack(null).commit();
        }
    }
}