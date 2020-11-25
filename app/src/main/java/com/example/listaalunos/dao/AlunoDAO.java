package com.example.listaalunos.dao;

import com.example.listaalunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
/*Classe para criar uma lista anônima, adicionar um elemento (um aluno), e devolver uma cópia,
para manter a lista "original" privada*/

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno alunoCriado) {
        alunoCriado.setId(contadorDeIds);
        alunos.add(alunoCriado);
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = BuscaAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    private Aluno BuscaAlunoPeloId(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
