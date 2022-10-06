package Servicos;

import DAOs.MarcaDAO;
import Entidades.Marca;
import Utilitarios.ProjetoUtil;

import java.util.List;

public class MarcaService extends CrudService {

    final String nomeEntidade = "marca";
    MarcaDAO dao = new MarcaDAO();
    ProjetoUtil projetoUtil = new ProjetoUtil();

    public void crud(){
        super.crud(dao.getNomeTabela());
    }

    @Override
    public void listar() {
        System.out.println("Listar " + nomeEntidade);
        List<Marca> list = dao.listar();
        if (list != null && !list.isEmpty()){
            System.out.println("Cód mar.  Nome                                            ");
            for (Marca e : list){
                String s = String.format("%8d  %s ", e.getCodMarca(), e.getNome());
                System.out.println(s);
            }
        }else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    @Override
    public void excluir() {
        System.out.println("Excluir " + nomeEntidade);
        System.out.println("Favor informar o código da marca que deseja excluir, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Marca entidade = dao.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            if (dao.delete(id)){
                System.out.println("Motorista excluido com sucesso!");
            }
        }
    }

    @Override
    public void consultar() {
        System.out.println("Consulta " + nomeEntidade);
        System.out.println("Favor informar o código da marca que deseja consultar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Marca entidade = dao.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            System.out.println(nomeEntidade);
            System.out.println("Código marca: " + entidade.getCodMarca());
            System.out.println("Nome: " + entidade.getNome());
            if (entidade.getDataDesat() > 0){
                System.out.println(nomeEntidade + " desativado");
            }
        }
    }

    @Override
    public void alterar() {
        System.out.println("Alterar " + nomeEntidade);
        System.out.println("Favor informar o código da marca que deseja alterar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Marca entidade = dao.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            if (entidade.getDataDesat() > 0){
                System.out.println(nomeEntidade + " desativo. Não pode mais ser alterado.");
                return;
            }

            System.out.println("Informações atuais: ");
            System.out.println("Nome: " + entidade.getNome());

            System.out.println("Novos valores: ");
            System.out.println("Nome: ");
            String nome = projetoUtil.scannString();

            entidade.setNome(nome);

            if(dao.update(entidade)){
                System.out.println(nomeEntidade + " atualizado com sucesso! ");
            }
        }
    }

    @Override
    public void criar() {
        System.out.println("Criar nova " + nomeEntidade);
        System.out.println("Nome: ");
        String nome = projetoUtil.scannString();

        Marca entidade = new Marca();
        entidade.setNome(nome);

        if(dao.create(entidade)){
            System.out.println(nomeEntidade + " criada com sucesso! ");
        }
    }
}
