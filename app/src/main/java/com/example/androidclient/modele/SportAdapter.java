//package com.example.androidclient.modele;
//
///**
// * Created by schwartz on 11/12/2017.
// */
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.LinearLayout;
//import android.widget.Switch;
//import android.widget.TextView;
//
//import com.example.androidclient.FormulaireDonnees;
//import com.example.androidclient.PerformanceActivity;
//
//import java.util.ArrayList;
//
//
//public class SportAdapter extends BaseAdapter {
//
//    // public class TodoAdapter extends BaseAdapter implements Filterable {
//
//
//    private ArrayList<Sport> sportList;
//
//
//    private Context context;
//
//    public SportAdapter(Context context, ArrayList<Sport> list) {
//        this.context = context;
//        this.sportList = list;
//    }
//
//    // Ajouter un sport dans la liste
//    public void add(Sport sport) {
//        sportList.add(sport);
//        // notifyDataSetChanged => on signale que les données ont changé
//        // et donc cela va "recharger" la listView
//        notifyDataSetChanged();
//    }
//
//    // Nombre d'éléments dans ma liste
//    // Il faut le surchager la méthode car on a pas tout le temps
//    // des arraylist
//    @Override
//    public int getCount() {
//        return this.sportList.size();
//    }
//
//    // D'obtenir le sport à la position index
//    @Override
//    public Object getItem(int index) {
//        return this.sportList.get(index);
//    }
//
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//
//    // Cette méthode getView va être appelée automatiquement à chaque fois
//    // qu'une ligne est affichée à l'écran
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        // LinearLayout correspond à l'élement racine de row_todo
//        LinearLayout linearLayout = null;
//        if (convertView != null) {
//            linearLayout = (LinearLayout) convertView;
//            convertView.setOnClickListener(null);
//        } else {
//            linearLayout = (LinearLayout) LayoutInflater.from(context).
//                    inflate(R.layout.row_Sport, parent, false);
//        }
//
//        // Je récupère quel t odo on est entrain d'afficher
//        final Sport currentSport = (Sport) getItem(position);
//
//        // Ici je dois "remplir" les élements graphiques de mon row_todo
//
//        Button btnNomSport = (Button) linearLayout.findViewById(R.id.btnNomSport);
//        btnNomSport.setOnClickListener(bClickListenerSport);
//
//        return linearLayout;
//    }
//    private View.OnClickListener bClickListenerSport = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            Intent i = new Intent(v.getContext(), PerformanceActivity.class);
//            startActivity(i);
//        }
//    };
//}
//
//
