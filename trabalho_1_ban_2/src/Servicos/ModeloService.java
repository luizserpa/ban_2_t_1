package Servicos;

import DAOs.ModeloDAO;
import Entidades.Modelo;
import Utilitarios.ProjetoUtil;

import java.util.List;

public class ModeloService extends CrudService {

    final String nomeEntidade = "modelo";
    ModeloDAO dao = new ModeloDAO();
    ProjetoUtil projetoUtil = new ProjetoUtil();

    public void crud(){
        super.crud(dao.getNomeTabela());
    }

    @Override
    public void listar() {
        System.out.println("Listar " + nomeEntidade);
        List<Modelo> list = dao.listar();
        if (list != null && !list.isEmpty()){
            System.out.println("Cód mod.  Cód mar.  Nome                  Comb.  Motor                            ");
            for (Modelo e : list){
                String s = String.format("%8d  %8d  %-20s  %-5s  %5.2f", e.getCodModelo(), e.getCodMarca(), e.getNome(), e.getCombustivel(), e.getMotor());
                System.out.println(s);
            }
        }else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    @Override
    public void excluir() {
        System.out.println("Excluir " + nomeEntidade);
        System.out.println("Favor informar o código do modelo que deseja excluir, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Modelo entidade = dao.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            if (dao.delete(id)){
                System.out.println("Modelo excluido com sucesso!");
            }
        }
    }

    @Override
    public void consultar() {
        System.out.println("Consulta " + nomeEntidade);
        System.out.println("Favor informar o código do modelo que deseja consultar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Modelo entidade = dao.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            System.out.println(nomeEntidade);
            System.out.println("Código modelo: " + entidade.getCodModelo());
            System.out.println("Código marca: " + entidade.getCodMarca());
            System.out.println("Nome: " + entidade.getNome());
            System.out.println("Combustivel: " + entidade.getCombustivel());
            System.out.println("Motor: " + entidade.getMotor());
            if (entidade.getDataDesat() > 0){
                System.out.println(nomeEntidade + " desativado");
            }
        }
    }

    @Override
    public void alterar() {
        System.out.println("Alterar " + nomeEntidade);
        System.out.println("Favor informar o código do modelo que deseja alterar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Modelo entidade = dao.findById(id);

            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            if (entidade.getDataDesat() > 0){
                System.out.println(nomeEntidade + " desativo. Não pode mais ser alterado.");
                return;
            }

            System.out.println("Informações atuais: ");
            System.out.println("Código modelo: " + entidade.getCodModelo());
            System.out.println("Código marca: " + entidade.getCodMarca());
            System.out.println("Nome: " + entidade.getNome());
            System.out.println("Combustivel: " + entidade.getCombustivel());
            System.out.println("Motor: " + entidade.getMotor());

            System.out.println("Novos valores: ");
            System.out.println("Código marca: ");
            Long codMarca = (long)projetoUtil.scannInt();
            System.out.println("Nome: ");
            String nome = projetoUtil.scannString();
            System.out.println("Combustivel: (A - Alcool| G - Gasolina | F - Flex )");
            String combustivel = projetoUtil.scannString();
            System.out.println("Motor: ");
            double motor = projetoUtil.scannDouble();

            entidade.setCodMarca(codMarca);
            entidade.setNome(nome);
            entidade.setCombustivel(combustivel.charAt(0));
            entidade.setMotor(motor);

            if(dao.update(entidade)){
                System.out.println(nomeEntidade + " atualizado com sucesso! ");
            }
        }
    }

    @Override
    public void criar() {
        System.out.println("Criar nova " + nomeEntidade);
        System.out.println("Código marca: ");
        Long codMarca = (long)projetoUtil.scannInt();
        System.out.println("Nome: ");
        String nome = projetoUtil.scannString();
        System.out.println("Combustivel: (A - Alcool| G - Gasolina | F - Flex )");
        String combustivel = projetoUtil.scannString();
        System.out.println("Motor: ");
        double motor = projetoUtil.scannDouble();

        Modelo entidade = new Modelo();
        entidade.setCodMarca(codMarca);
        entidade.setNome(nome);
        entidade.setCombustivel(combustivel.charAt(0));
        entidade.setMotor(motor);

        if(dao.create(entidade)){
            System.out.println(nomeEntidade + " criada com sucesso! ");
        }
    }
}
