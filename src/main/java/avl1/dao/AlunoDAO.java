package avl1.dao;

import avl1.modelo.Aluno;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

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

        Aluno aluno =  buscarPorNome(nome).getFirst();

        if(aluno != null){
            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno removido com sucesso!");
        }
        else{
            System.out.println("Aluno não encontrado");
        }
    }

    public List<Aluno> buscarPorNome(String nome){
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
        return em.createQuery(jpql, Aluno.class)
                .setParameter("n",nome)
                .getResultList();
    }

    public void alterar(String nomeAluno){

        Scanner scanner = new Scanner(System.in);
        em.getTransaction().begin();
        Aluno aluno = buscarPorNome(nomeAluno).getFirst();
        if(aluno == null){
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.println(aluno.toString());

        System.out.println("Digite o nome: ");
        aluno.setNome(scanner.nextLine());

        System.out.println("Digite o RA: ");
        aluno.setRa(scanner.nextLine());

        System.out.println("Digite o email: ");
        aluno.setEmail(scanner.nextLine());

        System.out.println("Digite a nota 1: ");
        aluno.setNota1(scanner.nextBigDecimal());

        System.out.println("Digite a nota 2: ");
        aluno.setNota2(scanner.nextBigDecimal());

        System.out.println("Digite a nota 3: ");
        aluno.setNota3(scanner.nextBigDecimal());

        em.merge(aluno);
        em.getTransaction().commit();

        System.out.println("Aluno alterado com sucesso!");
    }

    public List<Aluno> listarTodosAlunos() {
        String jpql = "SELECT a FROM Aluno a";
        List<Aluno> alunos =  em.createQuery(jpql, Aluno.class).getResultList();
        return alunos;
    }


}
