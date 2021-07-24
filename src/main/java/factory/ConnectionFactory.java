package factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    //Construtor declarado como privado para evitar instâncias
    private ConnectionFactory(){
        throw new UnsupportedOperationException();
    }

    public  static Connection getConnection(){

        //Declarando objeto da conexão
        Connection connection = null;

        //Carregando o arquivo de propriedades com os parâmetros de conexão com o banco
        try(InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")) {

            //Definindo parâmetros para se conectar com o banco
            Properties properties = new Properties();
            properties.load(inputStream);

            String driver = properties.getProperty("jdbc.driver");
            String dataBaseAddress = properties.getProperty("db.address");
            String dataBaseName = properties.getProperty("db.name");
            String user = properties.getProperty("db.user.login");
            String password = properties.getProperty("db.user.password");
            String timeZone = "useTimezone=true&serverTimezone=UTC";

            //Construção da string de conexão
            StringBuilder stringBuilder = new StringBuilder("jdbc:")
                    .append(driver).append("://")
                    .append(dataBaseAddress).append("/")
                    .append(dataBaseName).append("?")
                    .append(timeZone);

            String connectionUrl = stringBuilder.toString();

            //Criando uma conexão usando o DriveManager
            try{
                connection = DriverManager.getConnection(connectionUrl,user,password);
            } catch (SQLException throwables) {
                System.out.println("Falha ao tentar conectar ao banco");
                throwables.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("FALHA ao tentar carregar aquivos de propriedades");
            e.printStackTrace();
        }

        return  connection;
    }
}
