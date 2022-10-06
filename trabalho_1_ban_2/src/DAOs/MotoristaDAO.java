package DAOs;

import Entidades.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO {

    final String nomeTabela = "motorista";
    final ConnectionDb connectionDb = new ConnectionDb();
    
    public boolean create (Motorista motorista){
        validar(motorista);
        try {
            Connection connection = connectionDb.gerarConn();
            String insert = "INSERT INTO " + nomeTabela + " VALUES ( ?, ?, ?, ? );";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong(1, findProximoId());
            statement.setString(2, motorista.getNome());
            statement.setString(3, motorista.getCnh());
            statement.setInt(4, motorista.getDataDesat());

            statement.execute();
            statement.close();
            connection.close();

            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela Motorista");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean update (Motorista motorista){
        validar(motorista);
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "UPDATE " + nomeTabela +
                    " SET nome = ?, cnh = ?, data_desat = ? " +
                    "WHERE cod_mot = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(4, motorista.getCodMot());
            statement.setString(1, motorista.getNome());
            statement.setString(2, motorista.getCnh());
            statement.setInt(3, motorista.getDataDesat());

            statement.execute();
            statement.close();
            connection.close();

            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela Motorista");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean delete (Long codMot){
        Motorista motorista = findById(codMot);

        if (motorista == null){
            return true;
        }

        motorista.setDataDesat(1);
        return update(motorista);
    }

    public Motorista findById(Long codMot){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE COD_MOT = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, codMot);

            ResultSet resultSet = statement.executeQuery();

            Motorista motorista = null;
            while (resultSet.next()){
                motorista = new Motorista();
                motorista.setCodMot(resultSet.getLong(1));
                motorista.setNome(resultSet.getString(2));
                motorista.setCnh(resultSet.getString(3));
                motorista.setDataDesat(resultSet.getInt(4));
            }
            statement.close();
            connection.close();
            return motorista;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela Motorista");
            System.out.println(e.getMessage());

            return null;
        }
    }

    private Long findProximoId(){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT max(cod_mot) FROM " + nomeTabela;

            PreparedStatement statement = connection.prepareStatement(sb);
            ResultSet resultSet = statement.executeQuery();

            Long prox = 0L;
            while (resultSet.next()){
                prox = resultSet.getLong(1);
            }
            prox++;

            statement.close();
            connection.close();
            return prox;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela Motorista");
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Motorista> listar() {
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela;

            PreparedStatement statement = connection.prepareStatement(sb);
            ResultSet resultSet = statement.executeQuery();

            List<Motorista> list = new ArrayList<>();
            while (resultSet.next()){
                Motorista motorista = new Motorista();
                motorista.setCodMot(resultSet.getLong(1));
                motorista.setNome(resultSet.getString(2));
                motorista.setCnh(resultSet.getString(3));
                motorista.setDataDesat(resultSet.getInt(4));

                if (motorista.getDataDesat() > 0){
                    continue;
                }

                list.add(motorista);
            }

            statement.close();
            connection.close();
            return list;
        } catch (Exception e){
            System.out.println("Erro ao buscar lista");
            System.out.println(e.getMessage());

            return null;
        }
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    private void validar(Motorista motorista) {
    }
}
