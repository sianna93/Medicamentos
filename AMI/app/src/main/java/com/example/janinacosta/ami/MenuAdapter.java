package com.example.janinacosta.ami;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Janina Costa on 26/12/2016.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<MenuOpciones> items;

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public int color;
        public TextView nombre;
        public LinearLayout colorLl;


        public MenuViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            colorLl = (LinearLayout) v.findViewById(R.id.fondo_color);

            //Acciones a los cards
            colorLl.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    final Intent intent;
                    switch (getAdapterPosition()){
                        /*case 0:
                            intent = new Intent (v.getContext(),ActividadMedicamentosDia.class ); //modificar cuando estén creadas las actividades
                            v.getContext().startActivity(intent);
                            break;*/
                        case 1:
                            intent = new Intent (v.getContext(),MisMedicamentosActivity.class );
                            v.getContext().startActivity(intent);
                            break;
                        /*
                        case 2:
                            intent = new Intent (v.getContext(),ActividadHistorial.class );
                            v.getContext().startActivity(intent);
                            break;*/
                        case 3:
                            intent = new Intent (v.getContext(),ActividadLogin.class );
                            v.getContext().startActivity(intent);
                            break;
                    }

                }
            });
        }




    }

    public MenuAdapter(List<MenuOpciones> items) {
        this.items = items;

        //LinearLayout  card = (LinearLayout) findViewById(R.id.fondo_color);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.actividad_menu_card, viewGroup, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.colorLl.setBackgroundColor(Color.parseColor(items.get(i).getColor()));



        //codigo extra
        /*
        if((i% 2 == 0)){
            //viewHolder.itemView.getResources().getStringArray(R.array.initial_colors);
            viewHolder.itemView.setBackgroundColor(Color.BLUE);
        }else{
            //viewHolder.itemView.getResources().getStringArray(R.array.initial_colors);
            viewHolder.itemView.setBackgroundColor(Color.CYAN);
        }
        */

        //codigo para setear colores a los cards aleatoriamente
        //int[] androidColors = viewHolder.itemView.getResources().getIntArray(R.array.initial_colors);
        //int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        //viewHolder.itemView.setBackgroundColor(i);


    }
}

