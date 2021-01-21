package com.kmilo.pablodemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kmilo.pablodemo.R;
import com.kmilo.pablodemo.entities.Species;
import com.kmilo.pablodemo.utilities.Utilities;

import java.util.ArrayList;

public class SpecieAdapter extends RecyclerView.Adapter<SpecieAdapter.EspeciesViewHolder> implements View.OnClickListener {

    ArrayList<Species> listaEspecies;
    private View.OnClickListener listener;

    public SpecieAdapter(ArrayList<Species> listaEspecies) {
        this.listaEspecies = listaEspecies;
    }

    @Override
    public EspeciesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(this);

        return new EspeciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EspeciesViewHolder holder, int position) {
        holder.txtNombre.setText(listaEspecies.get(position).getNombre());
        if(Utilities.PORTRAIT == true){
            holder.txtInfo.setText(listaEspecies.get(position).getInfo());
        }
        holder.foto.setImageResource(listaEspecies.get(position).getImagenId());

    }

    @Override
    public int getItemCount() {
        return listaEspecies.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class EspeciesViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre,txtInfo;
        ImageView foto;


        public EspeciesViewHolder(View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            if(Utilities.PORTRAIT == true){
                txtInfo = (TextView) itemView.findViewById(R.id.txtInfo);
            }
            foto = (ImageView) itemView.findViewById(R.id.imgImagen);
        }
    }
}
