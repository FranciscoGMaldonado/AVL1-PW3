package avl1.dao;

import avl1.modelo.Aluno;
import jakarta.persistence.EntityManager;

public class AlunoDAO {

    private EntityManager em;

    public AlunoDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Aluno aluno){
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public void excluir(String nome){
        String jpql = "SELECT n FROM alunos n WHERE n.nome = :nome";
        Aluno aluno = em.createQuery(jpql, alunos.class).setParameter("nome", nome).s
    }

}
