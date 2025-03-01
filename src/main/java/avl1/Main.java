package avl1;

import avl1.dao.AlunoDAO;
import avl1.modelo.Aluno;
import avl1.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

//Abel Baes Correa - SC3039307
//Francisco Guatura Maldonado - SC3039111

public class Main {

    private static EntityManager em = JPAUtil.getEntityManager();
    private static AlunoDAO alunoDAO = new AlunoDAO(em);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        menu();
        int choice = scanner.nextInt();
        scanner.nextLine();

        while(choice != 6){
            switch (choice) {
                case 1 -> cadastrarAluno(alunoDAO);
                case 2-> excluirAluno(alunoDAO);
                case 3 -> alterarAluno(alunoDAO);
                case 4 -> buscarAluno(alunoDAO);
                case 5 -> listarAluno(alunoDAO);
                default -> System.out.println("Escolha Inválida");
            }
            menu();
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void cadastrarAluno(AlunoDAO alunoDAO) {

        System.out.println("CADASTRO DE ALUNO:\n");
        System.out.println("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o RA: ");
        String ra = scanner.nextLine();

        System.out.println("Digite o email: ");
        String email = scanner.nextLine();

        System.out.println("Digite a nota 1: ");
        BigDecimal nota1 = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.println("Digite a nota 2: ");
        BigDecimal nota2 = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.println("Digite a nota 3: ");
        BigDecimal nota3 = scanner.nextBigDecimal();
        scanner.nextLine();

        Aluno aluno = new Aluno(nome,ra,email,nota1,nota2,nota3);

        alunoDAO.cadastrar(aluno);
    }

    private static void excluirAluno(AlunoDAO alunoDAO) {

        System.out.println("EXCLUIR ALUNO:\n");
        System.out.println("Digite o nome: ");
        String nome = scanner.nextLine();

        alunoDAO.excluir(nome);
    }

    private static void alterarAluno(AlunoDAO alunoDAO) {

        System.out.println("Digite o nome do aluno que deseja alterar: ");
        String nomeAluno = scanner.nextLine();

        alunoDAO.alterar(nomeAluno);
    }

    private static void buscarAluno(AlunoDAO alunoDAO) {

        System.out.println("Consultar Aluno:\n");
        System.out.println("Digite o nome: ");
        String nome = scanner.nextLine();

        List<Aluno> buscaAluno = alunoDAO.buscarPorNome(nome);
        if (buscaAluno.isEmpty()) {
            System.out.println("Não existem alunos cadastrados");
            return;
        }

        Aluno aluno = buscaAluno.getFirst();

        System.out.println(aluno.toString());
    }

    private static void listarAluno(AlunoDAO alunoDAO) {

        List<Aluno> alunos = alunoDAO.listarTodosAlunos();

        if (alunos.isEmpty()) {
            System.out.println("Não existem alunos cadastrados");
            return;
        }

        System.out.println("\nExibindo todos os alunos:\n");

        alunos.forEach(aluno -> {
            BigDecimal media = aluno.getNota1()
                    .add(aluno.getNota2())
                    .add(aluno.getNota3())
                    .divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);

            String aprovacao = media.compareTo(BigDecimal.valueOf(6)) >= 0 ? "Aprovado"
                    : media.compareTo(BigDecimal.valueOf(4)) >= 0 ? "Recuperação" : "Reprovado";

            System.out.println(aluno.toString() + "Média: " + media + "\n" + "Situação: " + aprovacao + "\n");
        });

    }

    private static void menu(){
        System.out.println("** CADASTRO DE ALUNOS **\n");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Excluir aluno");
        System.out.println("3 - Alterar aluno");
        System.out.println("4 - Buscar aluno pelo nome");
        System.out.println("5 - Listar alunos (com status aprovação)");
        System.out.println("6 - FIM");

        System.out.println("Digite a opção desejada: ");
    }

}