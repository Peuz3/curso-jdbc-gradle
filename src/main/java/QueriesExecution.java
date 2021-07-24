import dao.CursoDAO;
import model.Curso;

import java.util.ArrayList;
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

        //Curso cursoById = cursoDAO.getById(2);
        System.out.println("------------------------------------------------------------");
        System.out.println("CONSULTA POR ID");
        System.out.println("------------------------------------------------------------");

        System.out.print("Gostaria de pesquisar o curso pelo código? 1 - SIM || 2 - NÃO ");
        int opcao = scanner.nextInt();

        if(opcao == 1){
            System.out.print("Digite o código do curso: ");
            int entradaConsultaId = scanner.nextInt();
            Curso cursoById = cursoDAO.getById(entradaConsultaId);
            System.out.println(cursoById);
        }else{
            System.out.println("Procura por ID Encerrado");
        }








    }
}
