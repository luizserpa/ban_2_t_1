package DAOs;

import Entidades.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    final String nomeTabela = "veiculo";

    public String getNomeTabela() {
        return nomeTabela;
    }

    final ConnectionDb connectionDb = new ConnectionDb();

    private void validar(Veiculo entidade) {
    }

    public boolean create (Veiculo entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String insert = "INSERT INTO " + nomeTabela + " VALUES ( ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong(1, findProximoId());
            statement.setString(2, entidade.getChassi());
            statement.setLong(3, entidade.getCodModelo());
            statement.setString(4, entidade.getPlaca());
            statement.setString(5, entidade.getUf());
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

    public boolean update (Veiculo entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "UPDATE " + nomeTabela +
                    " SET chassi = ?, cod_modelo = ?, placa = ?, uf = ?, inicio_uso = ?, termino_uso = ?, data_desat = ? "+
                    "WHERE cod_veiculo = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(6, entidade.getCodVeiculo());
            statement.setString(1, entidade.getChassi());
            statement.setLong(2, entidade.getCodModelo());
            statement.setString(3, entidade.getPlaca());
            statement.setString(4, entidade.getUf());
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
        Veiculo entidade = findById(id);

        if (entidade == null){
            return true;
        }
        entidade.setDataDesat(1);
        return update(entidade);
    }

    public Veiculo findById(Long id){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE cod_veiculo = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, id);

            ResultSet r = statement.executeQuery();

            Veiculo entidade = null;
            while (r.next()){
                entidade = new Veiculo();
                entidade.setCodVeiculo(r.getLong(1));
                entidade.setChassi(r.getString(2));
                entidade.setCodModelo(r.getLong(3));
                entidade.setPlaca(r.getString(4));
                entidade.setUf(r.getString(5));
                entidade.setDataDesat(r.getInt(6));
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

    public List<Veiculo> listar(){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela;

            PreparedStatement statement = connection.prepareStatement(sb);
            ResultSet r = statement.executeQuery();
            List<Veiculo> list = new ArrayList<>();

            while (r.next()){
                Veiculo entidade = new Veiculo();
                entidade.setCodVeiculo(r.getLong(1));
                entidade.setChassi(r.getString(2));
                entidade.setCodModelo(r.getLong(3));
                entidade.setPlaca(r.getString(4));
                entidade.setUf(r.getString(5));
                entidade.setDataDesat(r.getInt(6));

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

    private Long findProximoId(){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT max(cod_veiculo) FROM " + nomeTabela;

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
