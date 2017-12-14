//package com.example.androidclient.modele;
//
///**
// * Created by schwartz on 11/12/2017.
// */
//
//public class Todo {
//    package com.hb.todoapp;
//
//import android.app.Activity;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//
//import com.hb.todoapp.model.Todo;
//
//import java.util.ArrayList;
//
//    public class TodoActivity extends Activity {
//
//        private ArrayList<Todo> todoList;
//
//        private Button btnAdd;
//
//        private EditText editTitle;
//        private EditText editDesc;
//
//        private TodoAdapter todoAdapter;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_todo);
//
//            ListView l = (ListView) findViewById(R.id.listViewTodos);
//
//            editTitle = (EditText) findViewById(R.id.editTextTitle);
//            editDesc = (EditText) findViewById(R.id.editTextDesc);
//            btnAdd = (Button) findViewById(R.id.btnAdd);
//
//            btnAdd.setOnClickListener(addListener);;
//
//            // Construction de "notre base de données"
//            todoList = new ArrayList<>();
//            todoList.add(new Todo("Séries", "Télécharger Stranger Thinhgs"));
//            todoList.add(new Todo("Courses", "Acheter des tomates"));
//            todoList.add(new Todo("Dev", "Lire la Doc sur les listViews"));
//
//            Todo t1 = new Todo("Film", "Regarder Interstellar");
//            t1.setColor(Color.RED);
//            t1.setState(Todo.State.COMPLETED);
//
//            todoList.add(t1);
//
//            // Méthode 1 : utiliser le layout d'android android.R.layout
//            // A utiliser quand on une liste très simple de texte
//    /*
//        ArrayAdapter<Todo> adapter1 = new ArrayAdapter<Todo>(
//                this,
//                android.R.layout.simple_list_item_1,
//                todoList);
//
//
//        // Ici j'ai une listView  (Vue)
//        // J'ai des données (todoList)  (Modèle)
//        // J'ai un adapter adapter1 (Controler)
//
//        l.setAdapter(adapter1);
//    */
//    /*
//        // Méthode 2 : Utiliser son propre layout avec l'arrayAdapter
//        // Pour afficher une liste simple avec du style mais sans action
//
//        ArrayAdapter<Todo> adapter2 = new ArrayAdapter<Todo>(
//                this,
//                R.layout.row_todo, // Je lui demande d'utilser ce layout pour chaque ligne
//                R.id.txtTitle, // Je lui demande d'utiliser ce textView pour mettre le toString du Todo
//                todoList);
//
//        l.setAdapter(adapter2);
//    */
//
//            // Méthode 3
//            // Personaliser la construction de chaque item de la liste
//            // avec une classe TodoAdapter qui hérite de BaseAdpater
//            // dans la méthode getView on pourra "tout faire"
//            // Definir le style, choisir ou vont chaque information
//            // Definir des listener etc.
//            // On utilisera toujours row_todo
//
//            todoAdapter = new TodoAdapter(this, todoList);
//            l.setAdapter(todoAdapter);
//
//        }
//
//        private View.OnClickListener addListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = editTitle.getText().toString();
//                String desc = editDesc.getText().toString();
//                Todo newTodo = new Todo(title, desc);
//                todoAdapter.add(newTodo);
//            }
//        };
//
//
//    }
//
//}
