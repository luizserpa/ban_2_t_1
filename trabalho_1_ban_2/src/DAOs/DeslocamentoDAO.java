package DAOs;

import Entidades.Deslocamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeslocamentoDAO {

    final String nomeTabela = "deslocamento";
    final ConnectionDb connectionDb = new ConnectionDb();

    private void validar(Deslocamento entidade) {
    }

    public Long create (Deslocamento entidade){
        validar(entidade);
        try {
            Long id = findProximoId();

            Connection connection = connectionDb.gerarConn();
            String insert = "INSERT INTO " + nomeTabela + " VALUES ( ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong(1, id);
            statement.setLong(2, entidade.getCodVeiculo());
            statement.setLong(3, entidade.getCodMot());
            statement.setString(4, entidade.getDescricao());
            statement.setString(5, entidade.getStatusDes());

            statement.execute();
            statement.close();
            connection.close();

            return id;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return 0L;
        }
    }

    public boolean update (Deslocamento entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "UPDATE " + nomeTabela +
                    " SET cod_veiculo = ?, cod_mot = ?, descricao = ?, status_des = ? "+
                    "WHERE cod_des = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(5, entidade.getCodDes());
            statement.setLong(1, entidade.getCodVeiculo());
            statement.setLong(2, entidade.getCodMot());
            statement.setString(3, entidade.getDescricao());
            statement.setString(4, entidade.getStatusDes());

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
        Deslocamento entidade = findById(id);

        if (entidade == null){
            return true;
        }

        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "DELETE FROM " + nomeTabela +
                    " WHERE cod_des = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, id);

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

    public Deslocamento findById(Long id){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE cod_des = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, id);

            ResultSet r = statement.executeQuery();

            Deslocamento entidade = null;
            while (r.next()){
                entidade = new Deslocamento();
                entidade.setCodDes(r.getLong(1));
                entidade.setCodVeiculo(r.getLong(2));
                entidade.setCodMot(r.getLong(3));
                entidade.setDescricao(r.getString(4));
                entidade.setStatusDes(r.getString(5));
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

    public List<Deslocamento> findByStatus(String status){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE status_des = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setString(1, status);

            ResultSet r = statement.executeQuery();

            List<Deslocamento> list = new ArrayList<>();
            while (r.next()){
                Deslocamento entidade = new Deslocamento();
                entidade.setCodDes(r.getLong(1));
                entidade.setCodVeiculo(r.getLong(2));
                entidade.setCodMot(r.getLong(3));
                entidade.setDescricao(r.getString(4));
                entidade.setStatusDes(r.getString(5));

                list.add(entidade);
            }
            statement.close();
            connection.close();
            return list;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Deslocamento> listar(){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela;

            PreparedStatement statement = connection.prepareStatement(sb);
            ResultSet r = statement.executeQuery();

            List<Deslocamento> list = new ArrayList<>();
            while (r.next()){
                Deslocamento entidade = new Deslocamento();
                entidade.setCodDes(r.getLong(1));
                entidade.setCodVeiculo(r.getLong(2));
                entidade.setCodMot(r.getLong(3));
                entidade.setDescricao(r.getString(4));
                entidade.setStatusDes(r.getString(5));

                list.add(entidade);
            }
            statement.close();
            connection.close();
            return list;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    private Long findProximoId(){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT max(cod_des) FROM " + nomeTabela;

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


}
