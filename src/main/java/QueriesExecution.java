import dao.CursoDAO;
import model.Curso;


import java.util.List;
import java.util.Scanner;

public class QueriesExecution {
    public static void main(String[] args) {
        CursoDAO cursoDAO = new CursoDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("------------------------------------------------------------");
        System.out.println("LISTAGEM DOS CURSOS");
        System.out.println("------------------------------------------------------------");

        List<Curso> cursoList = cursoDAO.list();
        cursoList.stream().forEach(System.out::println);


        System.out.println("------------------------------------------------------------");
        System.out.println("CONSULTA POR ID");
        System.out.println("------------------------------------------------------------");

        System.out.print("Gostaria de pesquisar o curso pelo código? (1 - SIM || 2 - NÃO) ");
        int opcao = scanner.nextInt();

        if(opcao == 1){
            System.out.print("Digite o código do curso: ");
            int entradaConsultaId = scanner.nextInt();
            Curso cursoById = cursoDAO.getById(entradaConsultaId);
            System.out.println(cursoById);
        }else if(opcao == 2){
            System.out.println("Não houve procura por código");

        }else{
            System.out.println("Opção Inválida!");

        }

        System.out.println("------------------------------------------------------------");
        System.out.println("INSERÇÃO DE CURSOS");
        System.out.println("------------------------------------------------------------");

        System.out.print("Gostaria de inserir algum curso? (1 - SIM || 2 - NÃO) ");
        opcao = scanner.nextInt();

        if(opcao == 1){
            System.out.print("Quantos cursos serão inseridos? ");
            int qtdCursosInseridos = scanner.nextInt();
            for(int i = 0; i< qtdCursosInseridos; i++){

                System.out.print("Informe o nome do Curso: ");
                String nomeCurso = scanner.next();

                System.out.print("Informe a carga horária do curso: ");
                double cargaHoraria = scanner.nextDouble();

                Curso insertCurso = new Curso(nomeCurso,cargaHoraria);

                cursoDAO.create(insertCurso);
            }
        }else if(opcao == 2){
            System.out.println("Não houve inserção de novos cursos!");
        }else{
            System.out.println("Opção Inválida");
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("REMOÇÃO DE CURSOS");
        System.out.println("------------------------------------------------------------");

        System.out.print("Gostaria de excluir um curso? (1 - SIM || 2 - NÃO) ");
        opcao = scanner.nextInt();

        if(opcao == 1){
            System.out.println("Qual o código do curso?");
            int codCurso = scanner.nextInt();

            cursoDAO.delete(codCurso);

        } else if(opcao == 2){
            System.out.println("Não houve remoção de cursos");
        }else{
            System.out.println("Opção Inválida");
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("ATUALIZAÇÃO DE CURSOS");
        System.out.println("------------------------------------------------------------");

        System.out.print("Gostaria de ATUALIZAR algum curso? ");
        opcao = scanner.nextInt();
        if(opcao == 1){
            System.out.print("Qual o código do curso? ");
            int codCurso = scanner.nextInt();

            Curso updateCurso = cursoDAO.getById(codCurso);

            System.out.print("Qual o nome do curso? ");
            String nome = scanner.next();

            System.out.print("Qual a carga horária do curso? ");
            double cargaHoraria = scanner.nextDouble();

            updateCurso.setNome(nome);
            updateCurso.setDuracaoHoras(cargaHoraria);

            cursoDAO.update(updateCurso);


        }else if(opcao == 2){
            System.out.println("Não houve atualização de curso");
        } else {
            System.out.println("Opção Inválida");
        }













    }
}
