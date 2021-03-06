package com.kmilo.pablodemo.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kmilo.pablodemo.R;
import com.kmilo.pablodemo.adapters.SpecieAdapter;
import com.kmilo.pablodemo.entities.Species;
import com.kmilo.pablodemo.interfaces.IMasterDetails;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpeciesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpeciesListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<Species> listaEspecies;
    RecyclerView recyclerEspecies;

    Activity activity;
    IMasterDetails interfaceComunicaFragments;

    public SpeciesListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpeciesListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SpeciesListFragment newInstance(String param1, String param2) {
        SpeciesListFragment fragment = new SpeciesListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_species_list, container, false);

        listaEspecies = new ArrayList<>();
        recyclerEspecies = (RecyclerView) view.findViewById(R.id.recyclerId);
        recyclerEspecies.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaEspecies();

        SpecieAdapter adapter = new SpecieAdapter(listaEspecies);
        recyclerEspecies.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Seleccion: "
                        + listaEspecies.get(recyclerEspecies
                        .getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();

                interfaceComunicaFragments.enviarEspecie(listaEspecies.get(recyclerEspecies.getChildAdapterPosition(v)));
            }
        });

        return view;
    }

    private void llenarListaEspecies() {
        listaEspecies.add(new Species(getString(R.string.bluemarlin_nombre), getString(R.string.bluemarlin_info)
                , getString(R.string.bluemarlin_detalle), R.drawable.bluemarlin, R.drawable.bluemarlin));

        listaEspecies.add(new Species(getString(R.string.dolphinfish_nombre), getString(R.string.dolphinfish_info)
                , getString(R.string.dolphinfish_detalle), R.drawable.dolphinfish, R.drawable.dolphinfish));

        listaEspecies.add(new Species(getString(R.string.kingmackerel_nombre), getString(R.string.kingmackerel_info)
                , getString(R.string.dolphinfish_detalle), R.drawable.kingmackerel, R.drawable.kingmackerel));

        listaEspecies.add(new Species(getString(R.string.sailfish_nombre), getString(R.string.sailfish_info)
                , getString(R.string.sailfish_detalle), R.drawable.sailfish, R.drawable.sailfish));

        listaEspecies.add(new Species(getString(R.string.snook_nombre), getString(R.string.snook_info)
                , getString(R.string.snook_detalle), R.drawable.snook, R.drawable.snook));

        listaEspecies.add(new Species(getString(R.string.tarpon_nombre), getString(R.string.tarpon_info)
                , getString(R.string.tarpon_detalle), R.drawable.tarpon, R.drawable.tarpon));

        listaEspecies.add(new Species(getString(R.string.tuna_nombre), getString(R.string.tuna_info)
                , getString(R.string.tuna_detalle), R.drawable.tuna, R.drawable.tuna));

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Activity){
            this.activity= (Activity) context;
            interfaceComunicaFragments= (IMasterDetails) this.activity;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}