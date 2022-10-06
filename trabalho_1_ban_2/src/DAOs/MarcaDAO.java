package DAOs;

import Entidades.Marca;
import Entidades.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO {

    final String nomeTabela = "marca";

    public String getNomeTabela() {
        return nomeTabela;
    }

    final ConnectionDb connectionDb = new ConnectionDb();
    
    public boolean create (Marca entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String insert = "INSERT INTO " + nomeTabela + " VALUES ( ?, ?, ? );";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setLong(1, findProximoId());
            statement.setString(2, entidade.getNome());
            statement.setInt(3, entidade.getDataDesat());

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

    public boolean update (Marca entidade){
        validar(entidade);
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "UPDATE " + nomeTabela +
                    " SET nome = ?, data_desat = ? "+
                    "WHERE cod_marca = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(3, entidade.getCodMarca());
            statement.setString(1, entidade.getNome());
            statement.setInt(2, entidade.getDataDesat());

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

    public boolean delete (Long codMarca){
        Marca entidade = findById(codMarca);

        if (entidade == null){
            return true;
        }
        entidade.setDataDesat(1);
        return update(entidade);
    }

    public Marca findById(Long id){
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela + " WHERE COD_MARCA = ? ";

            PreparedStatement statement = connection.prepareStatement(sb);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            Marca entidade = null;
            while (resultSet.next()){
                entidade = new Marca();
                entidade.setCodMarca(resultSet.getLong(1));
                entidade.setNome(resultSet.getString(2));
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
            String sb = "SELECT max(cod_marca) FROM " + nomeTabela;

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

    public List<Marca> listar() {
        try {
            Connection connection = connectionDb.gerarConn();
            String sb = "SELECT * FROM " + nomeTabela;

            PreparedStatement statement = connection.prepareStatement(sb);
            ResultSet resultSet = statement.executeQuery();

            List<Marca> list = new ArrayList<>();
            while (resultSet.next()){
                Marca entidade = new Marca();
                entidade.setCodMarca(resultSet.getLong(1));
                entidade.setNome(resultSet.getString(2));

                if (entidade.getDataDesat() > 0){
                    continue;
                }

                list.add(entidade);
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

    private void validar(Marca motorista) {
    }
}
