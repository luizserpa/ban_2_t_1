package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDb {
    final String serverName = "localhost";
    final String myBd = "trabalho_1";
    final String url = "jdbc:postgresql://" + serverName + "/" + myBd;
    final String userName = "postgres";
    final String password = "221193";



    public ConnectionDb() {
    }

    public Connection gerarConn () throws Exception{
        return DriverManager.getConnection(url, userName, password);
    }
}
