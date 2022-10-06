package DAOs;

import Entidades.Marca;
import Entidades.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModeloDAO {

    final String nomeTabela = "modelo";

    public String getNomeTabela() {
        return nomeTabela;
    }

    final ConnectionDb connectionDb = new ConnectionDb();

    private void validar(Modelo entidade) {
    }
    
    public boolean create (Modelo entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String insert = "INSERT INTO " + nomeTabela + " VALUES ( ?, ?, ?, ?, ?, ? );";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong(1, findProximoId());
            statement.setLong(2, entidade.getCodMarca());
            statement.setString(3, entidade.getNome());
            statement.setString(4, entidade.getCombustivel().toString());
            statement.setDouble(5, entidade.getMotor());
            statement.setInt(6, entidade.getDataDesat());

            statement.execute();
            statement.close();
            connection.close();

            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean update (Modelo entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "UPDATE " + nomeTabela +
                    " SET cod_marca = ?, nome = ?, combustivel = ?, motor = ?, data_desat = ? "+
                    "WHERE cod_modelo = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(6, entidade.getCodModelo());
            statement.setLong(1, entidade.getCodMarca());
            statement.setString(2, entidade.getNome());
            statement.setString(3, entidade.getCombustivel().toString());
            statement.setDouble(4, entidade.getMotor());
            statement.setInt(5, entidade.getDataDesat());

            statement.execute();
            statement.close();
            connection.close();

            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean delete (Long id){
        Modelo entidade = findById(id);

        if (entidade == null){
            return true;
        }
        entidade.setDataDesat(1);
        return update(entidade);
    }

    public Modelo findById(Long id){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE cod_modelo = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            Modelo entidade = null;
            while (resultSet.next()){
                entidade = new Modelo();
                entidade.setCodModelo(resultSet.getLong(1));
                entidade.setCodMarca(resultSet.getLong(2));
                entidade.setNome(resultSet.getString(3));
                entidade.setCombustivel(resultSet.getString(4).charAt(0));
                entidade.setMotor(resultSet.getDouble(5));
                entidade.setDataDesat(resultSet.getInt(6));
            }
            statement.close();
            connection.close();
            return entidade;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    private Long findProximoId(){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT max(cod_modelo) FROM " + nomeTabela;

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
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Modelo> listar(){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela;

            PreparedStatement statement = connection.prepareStatement(sb);
            ResultSet resultSet = statement.executeQuery();

            List<Modelo> list = new ArrayList<>();
            while (resultSet.next()){
                Modelo entidade = new Modelo();
                entidade.setCodModelo(resultSet.getLong(1));
                entidade.setCodMarca(resultSet.getLong(2));
                entidade.setNome(resultSet.getString(3));
                entidade.setCombustivel(resultSet.getString(4).charAt(0));
                entidade.setMotor(resultSet.getDouble(5));
                entidade.setDataDesat(resultSet.getInt(6));

                if (entidade.getDataDesat() > 0){
                    continue;
                }

                list.add(entidade);
            }
            statement.close();
            connection.close();
            return list;
        }catch (Exception e){
            System.out.println("Erro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

}
