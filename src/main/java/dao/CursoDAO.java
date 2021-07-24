package dao;

import factory.ConnectionFactory;
import model.Curso;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    //Consulta
    public List<Curso> list(){
        //Preparar a lista que virá do bd após a consulta
        List<Curso> cursoList = new ArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection()) {
            //Consulta sql
            String sql = "SELECT * FROM curso";

            //Preparar o statement com os parâmetros recebidos
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Executa a consulta e armazena o retorno do resultSet
            ResultSet resultSet = preparedStatement.executeQuery();

            //Criando um objeto de curso e guardar em uma lista
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                double duracaoHoras = resultSet.getDouble("duracao_horas");

                cursoList.add( new Curso(id,nome,duracaoHoras));

            }
        } catch (SQLException throwables) {
            System.out.println("Não foi possível fazer a listagem dos Cursos!");
            throwables.printStackTrace();
        }

        return cursoList;

    }

}
