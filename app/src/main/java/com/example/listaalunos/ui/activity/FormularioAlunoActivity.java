package com.example.listaalunos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.listaalunos.R;
import com.example.listaalunos.dao.AlunoDAO;
import com.example.listaalunos.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_DA_APPBAR = "Nome do aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        setTitle(TITULO_DA_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();

    }

    private void configuraBotaoSalvar() {
        Button salvar = findViewById(R.id.salvar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno alunoCriado = criaAluno();
                //Recebe os EditText e transforma em Strings
                salva(alunoCriado);
                //Usa o metodo do AlunoDAO para salvar o aluno na lista "original"
            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activityNome);
        campoTelefone = findViewById(R.id.activityTelefone);
        campoEmail = findViewById(R.id.activityEmail);
    }

    private void salva(Aluno alunoCriado) {
        dao.salva(alunoCriado);
        finish();
    }

    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}