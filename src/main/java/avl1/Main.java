package avl1;

import avl1.dao.AlunoDAO;
import avl1.modelo.Aluno;
import avl1.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        AlunoDAO alunoDAO = new AlunoDAO(em);

        menu();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        while(choice == 6){
            switch (choice) {
                case 1 -> cadastrarAluno(alunoDAO);
                case 2-> excluirAluno(alunoDAO);
                case 3 -> alterarAluno();
                case 4 -> buscarAluno();
                case 5 -> listarAluno();
                default -> System.out.println("Escolha Inválida");
            }
            menu();
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void cadastrarAluno(AlunoDAO alunoDAO) {

        Scanner scanner = new Scanner(System.in);

        EntityManager em = JPAUtil.getEntityManager();

        System.out.println("CADASTRO DE ALUNO:");
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
        scanner.close();
    }

    private static void excluirAluno(AlunoDAO alunoDAO) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("EXCLUIR ALUNO:");
        System.out.println("Digite o nome: ");
        String nome = scanner.nextLine();

        alunoDAO.excluir(nome);

        scanner.close();

    }

    private static void alterarAluno() {

    }

    private static void buscarAluno() {

    }

    private static void listarAluno() {

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