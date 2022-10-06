package Servicos;

import DAOs.MotoristaDAO;
import Entidades.Motorista;
import Utilitarios.ProjetoUtil;

import java.util.List;

public class MotoristaService extends CrudService {

    final String nomeEntidade = "motorista";
    MotoristaDAO motoristaDAO = new MotoristaDAO();
    ProjetoUtil projetoUtil = new ProjetoUtil();

    public void crud(){
        super.crud(motoristaDAO.getNomeTabela());
    }

    @Override
    public void listar() {
        System.out.println("Listar " + nomeEntidade);
        List<Motorista> list = motoristaDAO.listar();
        if (list != null && !list.isEmpty()){
            System.out.println("Cód mot.  Nome                  CNH                           ");
            for (Motorista e : list){
                String s = String.format("%8d  %-20s  %s", e.getCodMot(), e.getNome(), e.getCnh());
                System.out.println(s);
            }
        }else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    @Override
    public void excluir() {
        System.out.println("Excluir " + nomeEntidade);
        System.out.println("Favor informar o código do motorista que deseja excluir, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Motorista entidade = motoristaDAO.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            if (motoristaDAO.delete(id)){
                System.out.println("Motorista excluido com sucesso!");
            }
        }
    }

    @Override
    public void consultar() {
        System.out.println("Consulta " + nomeEntidade);
        System.out.println("Favor informar o código do motorista que deseja consultar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Motorista entidade = motoristaDAO.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            System.out.println(nomeEntidade);
            System.out.println("Código motorista: " + entidade.getCodMot());
            System.out.println("Nome: " + entidade.getNome());
            System.out.println("CNH: " + entidade.getCnh());
            if (entidade.getDataDesat() > 0){
                System.out.println(nomeEntidade + " desativado");
            }
        }
    }

    @Override
    public void alterar() {
        System.out.println("Alterar " + nomeEntidade);
        System.out.println("Favor informar o código do motorista que deseja alterar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Motorista entidade = motoristaDAO.findById(id);
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
            System.out.println("CNH: " + entidade.getCnh());

            System.out.println("Novos valores: ");
            System.out.println("Nome: ");
            String nome = projetoUtil.scannString();
            System.out.println("Carteira de habilitação: ");
            String cnh = projetoUtil.scannString();

            entidade.setNome(nome);
            entidade.setCnh(cnh);

            if(motoristaDAO.update(entidade)){
                System.out.println(nomeEntidade + " atualizado com sucesso! ");
            }
        }
    }

    @Override
    public void criar() {
        System.out.println("Criar novo " + nomeEntidade);
        System.out.println("Nome: ");
        String nome = projetoUtil.scannString();
        System.out.println("Carteira de habilitação: ");
        String cnh = projetoUtil.scannString();

        Motorista motorista = new Motorista();
        motorista.setNome(nome);
        motorista.setCnh(cnh);

        if(motoristaDAO.create(motorista)){
            System.out.println(nomeEntidade + " criado com sucesso! ");
        }
    }
}
