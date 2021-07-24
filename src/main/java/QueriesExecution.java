import dao.CursoDAO;
import model.Curso;

import java.util.ArrayList;
import java.util.List;

public class QueriesExecution {
    public static void main(String[] args) {
        CursoDAO cursoDAO = new CursoDAO();

        List<Curso> cursoList = cursoDAO.list();
        cursoList.stream().forEach(System.out::println);




    }
}
