package DAOs;

import Entidades.DeslocamentoIncidente;
import Entidades.Incidente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncidenteDAO {

    final String nomeTabela = "incidente";
    final ConnectionDb connectionDb = new ConnectionDb();

    private void validar(Incidente entidade) {
    }

    public boolean create (Incidente entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String insert = "INSERT INTO " + nomeTabela + " VALUES ( ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong(1, entidade.getCodDes());
            statement.setLong(2, findProximoCodInc());
            statement.setString(3, entidade.getTipoInc());
            statement.setString(4, entidade.getDescricao());

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

    public boolean update (Incidente entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "UPDATE " + nomeTabela +
                    " SET tipo_inc = ?, descricao = ? "+
                    "WHERE cod_des = ? AND cod_inc = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(4, entidade.getCodDes());
            statement.setLong(3, entidade.getCodInc());
            statement.setString(1, entidade.getTipoInc());
            statement.setString(2, entidade.getDescricao());

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

    public boolean delete (Long codDes, Long codInc){
        Incidente entidade = findById(codDes, codInc);

        if (entidade == null){
            return true;
        }

        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "DELETE FROM " + nomeTabela +
                    " WHERE cod_des = ? AND cod_inc = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, codDes);
            statement.setLong(2, codInc);

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

    public Incidente findById(Long codDes, Long codInc){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE cod_des = ? AND cod_inc = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, codDes);
            statement.setLong(2, codInc);

            ResultSet r = statement.executeQuery();
            Incidente entidade = null;

            while (r.next()){
                entidade = new Incidente();
                entidade.setCodDes(r.getLong(1));
                entidade.setCodInc(r.getLong(2));
                entidade.setTipoInc(r.getString(3));
                entidade.setDescricao(r.getString(4));
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

    public Incidente findByCodInc(Long codInc){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE cod_inc = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, codInc);

            ResultSet r = statement.executeQuery();
            Incidente entidade = null;

            while (r.next()){
                entidade = new Incidente();
                entidade.setCodDes(r.getLong(1));
                entidade.setCodInc(r.getLong(2));
                entidade.setTipoInc(r.getString(3));
                entidade.setDescricao(r.getString(4));
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


    public List<Incidente> findAllByCodDes(Long codDes){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE cod_des = ?  ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, codDes);

            ResultSet r = statement.executeQuery();

            List<Incidente> list = new ArrayList<>();
            while (r.next()){
                Incidente entidade = new Incidente();
                entidade.setCodDes(r.getLong(1));
                entidade.setCodInc(r.getLong(2));
                entidade.setTipoInc(r.getString(3));
                entidade.setDescricao(r.getString(4));

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

    public List<Incidente> listar(){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela;

            PreparedStatement statement = connection.prepareStatement(sb);
            ResultSet r = statement.executeQuery();

            List<Incidente> list = new ArrayList<>();
            while (r.next()){
                Incidente entidade = new Incidente();
                entidade.setCodDes(r.getLong(1));
                entidade.setCodInc(r.getLong(2));
                entidade.setTipoInc(r.getString(3));
                entidade.setDescricao(r.getString(4));

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

    public List<Incidente> listarPorTipo(String tipo){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE tipo_inc = ?  ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setString(1, tipo);

            ResultSet r = statement.executeQuery();

            List<Incidente> list = new ArrayList<>();
            while (r.next()){
                Incidente entidade = new Incidente();
                entidade.setCodDes(r.getLong(1));
                entidade.setCodInc(r.getLong(2));
                entidade.setTipoInc(r.getString(3));
                entidade.setDescricao(r.getString(4));

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

    private Long findProximoCodInc(){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT max(cod_inc) FROM " + nomeTabela;

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


    public List<DeslocamentoIncidente> findAllDeslocamentoIncidenteByCodMot(long id) {
        try {
            Connection connection = connectionDb.gerarConn();

            String sb = "SELECT d.cod_des, d.cod_veiculo, d.cod_mot, d.descricao, i.tipo_inc, i.descricao, m.nome FROM deslocamento d " +
                    "JOIN incidente i On (i.cod_des = d.cod_des ) " +
                    "JOIN veiculo v on (d.cod_veiculo = v.cod_veiculo) " +
                    "JOIN modelo m on (v.cod_modelo = m.cod_modelo) " +
                    "WHERE d.cod_mot = ?";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            List<DeslocamentoIncidente> list = new ArrayList<>();
            while (resultSet.next()){
                DeslocamentoIncidente di = new DeslocamentoIncidente();
                di.setCodDes(resultSet.getLong(1));
                di.setCodVeiculo(resultSet.getLong(2));
                di.setCodMot(resultSet.getLong(3));
                di.setDescricao(resultSet.getString(4));
                di.setTipoInc(resultSet.getString(5));
                di.setDescricaoIncidente(resultSet.getString(6));
                di.setVeiculo(resultSet.getString(7));

                list.add(di);
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

    public List<DeslocamentoIncidente> findAllDeslocamentoIncidenteByCodVeiculo(long id) {
        try {
            Connection connection = connectionDb.gerarConn();

            String sb = "SELECT d.cod_des, d.cod_veiculo, d.cod_mot, d.descricao, i.tipo_inc, i.descricao, m.nome FROM deslocamento d " +
                    "JOIN incidente i On (i.cod_des = d.cod_des ) " +
                    "JOIN motorista m on (d.cod_mot = m.cod_mot) " +
                    "WHERE d.cod_veiculo = ?";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            List<DeslocamentoIncidente> list = new ArrayList<>();
            while (resultSet.next()){
                DeslocamentoIncidente di = new DeslocamentoIncidente();
                di.setCodDes(resultSet.getLong(1));
                di.setCodVeiculo(resultSet.getLong(2));
                di.setCodMot(resultSet.getLong(3));
                di.setDescricao(resultSet.getString(4));
                di.setTipoInc(resultSet.getString(5));
                di.setDescricaoIncidente(resultSet.getString(6));
                di.setMotorista(resultSet.getString(7));

                list.add(di);
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
