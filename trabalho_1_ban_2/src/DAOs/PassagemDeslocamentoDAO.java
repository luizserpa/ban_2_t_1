package DAOs;

import Entidades.PassagemDeslocamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PassagemDeslocamentoDAO {

    final String nomeTabela = "passagem_deslocamento";
    final ConnectionDb connectionDb = new ConnectionDb();

    private void validar(PassagemDeslocamento entidade) {
    }

    public boolean create (PassagemDeslocamento entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String insert = "INSERT INTO " + nomeTabela + " VALUES ( ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong(1, entidade.getCodDes());
            statement.setLong(2, findProximoSeq(entidade.getCodDes()));
            statement.setString(3, entidade.getTipoDes());

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

    public boolean update (PassagemDeslocamento entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "UPDATE " + nomeTabela +
                    " SET data_des = ?, hora_Des = ?, tipo_Des = ? "+
                    "WHERE cod_des = ? AND seq = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(2, entidade.getCodDes());
            statement.setLong(3, entidade.getSeq());
            statement.setString(1, entidade.getTipoDes());

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

    public boolean delete (Long codDes, Long seq){
        PassagemDeslocamento entidade = findById(codDes, seq);

        if (entidade == null){
            return true;
        }

        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "DELETE FROM " + nomeTabela +
                    " WHERE cod_des = ? AND seq = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, codDes);
            statement.setLong(2, seq);

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

    public PassagemDeslocamento findById(Long codDes, Long seq){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE cod_des = ? AND seq = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, codDes);
            statement.setLong(2, seq);

            ResultSet r = statement.executeQuery();
            PassagemDeslocamento entidade = null;

            while (r.next()){
                entidade = new PassagemDeslocamento();
                entidade.setCodDes(r.getLong(1));
                entidade.setSeq(r.getLong(2));
                entidade.setTipoDes(r.getString(5));
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

    public List<PassagemDeslocamento> findAllByCodDes(Long codDes){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE cod_des = ?  ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, codDes);

            ResultSet r = statement.executeQuery();

            List<PassagemDeslocamento> list = new ArrayList<>();
            while (r.next()){
                PassagemDeslocamento entidade = new PassagemDeslocamento();
                entidade.setCodDes(r.getLong(1));
                entidade.setSeq(r.getLong(2));
                entidade.setTipoDes(r.getString(3));

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

    private Long findProximoSeq(Long codDes){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT max(seq) FROM " + nomeTabela
                    + " WHERE cod_Des = ?";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, codDes);
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
