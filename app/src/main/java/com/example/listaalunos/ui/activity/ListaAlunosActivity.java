package com.example.listaalunos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listaalunos.R;
import com.example.listaalunos.dao.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Lista de alunos";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITULO_APP_BAR);
        configuraFabNovoAluno();

    }

    private void configuraFabNovoAluno() {
        View botaoNovoAluno = findViewById(R.id.fabNovoAluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioAlunoActivity();
            }
        });
    }

    private void abreFormularioAlunoActivity() {
        //mostra de qual activity a informação veio e para onde vai
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        configuraLista(dao);

    }

    private void configuraLista(AlunoDAO dao) {
        ListView listaDeAlunos = findViewById(R.id.listaAlunos);
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dao.todos()));
        //atualiza a lista com as alterações
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("posicao do aluno", "" + position);
        //Deixa você ver no logcat o que acontece quando vc clica no "listaDeAlunos"
            }
        });

    }
}