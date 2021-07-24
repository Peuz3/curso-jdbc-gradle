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

    //Consulta por ID
    public Curso getById(int id){

        //Preparar objeto curso para receber os valores bd
        Curso curso = new Curso();

        try(Connection connection = ConnectionFactory.getConnection()) {
            //Prepara a consulta SQL
            String sql = "SELECT * FROM curso WHERE id = ?";

            //Prepara o statement com os parâmetros
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            //Executa consulta e armazena o retorno da consulta no resultSet
            ResultSet resultSet = preparedStatement.executeQuery();

            //Guarda os valores retornada da tabela
            if(resultSet.next()){
                curso.setId(resultSet.getInt("id"));
                curso.setNome(resultSet.getString("nome"));
                curso.setDuracaoHoras(resultSet.getDouble("duracao_Horas"));
            }

        } catch (SQLException throwables) {
            System.out.println("Não foi possível fazer a busca do curso");
            throwables.printStackTrace();
        }

        return curso;
    }

    //Inserção
    public void create(Curso curso){
        try(Connection connection = ConnectionFactory.getConnection()) {
            //Prepara a inserção dos cursos
            String sql = "INSERT INTO curso(nome, duracao_horas) VALUES (?,?)";

            //Statement com os parâmetros recebidos
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setDouble(2,curso.getDuracaoHoras());

            //Executa a inserção e armazena o número de linhas afetadas
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Inserção De Curso Efetudada Com Sucesso! " + rowsAffected + " linha");

        } catch (SQLException throwables) {
            System.out.println("Falha ao inserir curso!");
            throwables.printStackTrace();
        }
    }
    //Remoção
    public void delete (int id){

        try(Connection connection = ConnectionFactory.getConnection()) {

            //Prepara o sql
            String sql = "DELETE FROM curso WHERE id = ?";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            //Executa delete e armazena o numero de linhas afetadas
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Curso Excluído Com Sucesso " + rowsAffected + " linha");
        } catch (SQLException throwables) {
            System.out.println("Falha ao remover curso!");
            throwables.printStackTrace();
        }
    }


    //Atualização
    public void update(Curso curso){
        try(Connection connection = ConnectionFactory.getConnection()) {

            //Sql de Atualização
            String sql = "UPDATE curso SET nome = ?, duracao_horas = ? WHERE id = ?";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,curso.getNome());
            preparedStatement.setDouble(2,curso.getDuracaoHoras());
            preparedStatement.setInt(3,curso.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Atualização De Curso Efetuada Com Sucesso! " + rowsAffected + " linha");

        } catch (SQLException throwables) {
            System.out.println("Falha ao atualizar o curso");
            throwables.printStackTrace();
        }
    }


}
