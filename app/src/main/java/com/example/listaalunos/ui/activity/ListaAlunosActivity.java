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
import com.example.listaalunos.model.Aluno;

import java.util.List;

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

        configuraLista();

    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.listaAlunos);
        final List<Aluno> alunos = dao.todos();
        //variável para que o onItemClick posso ter acesso direto à lista
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos));
        //atualiza a lista com as alterações
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                /*Log.i("posicao do aluno", "" + posicao);
                Deixa você ver no logcat o que acontece quando vc clica no "listaDeAlunos"*/
                Aluno alunoEscolhido = alunos.get(posicao);
                Intent vaiParaFormularioActivitty = new Intent(
                        ListaAlunosActivity.this, FormularioAlunoActivity.class);
                vaiParaFormularioActivitty.putExtra("aluno", alunoEscolhido);
                /*obs.: como Aluno não é um valor primitivo, precisa implementar "serializable"
                para ser transferido através do putExtra*/
                startActivity(vaiParaFormularioActivitty);

            }
        });

    }
}