package Servicos;


import DAOs.VeiculoDAO;
import Entidades.Veiculo;
import Utilitarios.ProjetoUtil;

import java.util.List;

public class VeiculoService extends CrudService {

    final String nomeEntidade = "modelo";
    VeiculoDAO dao = new VeiculoDAO();
    ProjetoUtil projetoUtil = new ProjetoUtil();

    public void crud(){
        super.crud(dao.getNomeTabela());
    }

    @Override
    public void listar() {
        System.out.println("Listar " + nomeEntidade);
        List<Veiculo> list = dao.listar();
        if (list != null && !list.isEmpty()){
            System.out.println("Cód vei.  Chassi      Cód mod.  Placa       Uf  ");
            for (Veiculo e : list){
                String s = String.format("%8d  %-10s  %8d  %-10s  %s ", e.getCodVeiculo(), e.getChassi(), e.getCodModelo(), e.getPlaca(), e.getUf());
                System.out.println(s);
            }
        }else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    @Override
    public void excluir() {
        System.out.println("Excluir " + nomeEntidade);
        System.out.println("Favor informar o código do veiculo que deseja excluir, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Veiculo entidade = dao.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            if (dao.delete(id)){
                System.out.println("Veiculo excluido com sucesso!");
            }
        }
    }

    @Override
    public void consultar() {
        System.out.println("Consulta " + nomeEntidade);
        System.out.println("Favor informar o código do veiculo que deseja consultar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Veiculo entidade = dao.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            System.out.println(nomeEntidade);
            System.out.println("Código veiculo: " + entidade.getCodVeiculo());
            System.out.println("Chassi: " + entidade.getChassi());
            System.out.println("Código modelo: " + entidade.getCodModelo());
            System.out.println("Placa: " + entidade.getPlaca());
            System.out.println("UF: " + entidade.getUf());
            if (entidade.getDataDesat() > 0){
                System.out.println(nomeEntidade + " desativado");
            }
        }
    }

    @Override
    public void alterar() {
        System.out.println("Alterar " + nomeEntidade);
        System.out.println("Favor informar o código do veiculo que deseja alterar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Veiculo entidade = dao.findById(id);

            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            if (entidade.getDataDesat() > 0){
                System.out.println(nomeEntidade + " desativo. Não pode mais ser alterado.");
                return;
            }

            System.out.println("Informações atuais: ");
            System.out.println("Código veiculo: " + entidade.getCodVeiculo());
            System.out.println("Chassi: " + entidade.getChassi());
            System.out.println("Código modelo: " + entidade.getCodModelo());
            System.out.println("Placa: " + entidade.getPlaca());
            System.out.println("UF: " + entidade.getUf());

            System.out.println("Novos valores: ");
            System.out.println("Chassi: ");
            String chassi = projetoUtil.scannString();
            System.out.println("Código modelo: ");
            Long codModelo = (long)projetoUtil.scannInt();
            System.out.println("Placa: ");
            String placa = projetoUtil.scannString();
            System.out.println("UF: ");
            String uf = projetoUtil.scannString();
            System.out.println("Inicio uso: ");
            int inicioUso = projetoUtil.scannInt();
            System.out.println("Termino uso: ");
            int terminoUso = projetoUtil.scannInt();

            entidade.setChassi(chassi);
            entidade.setCodModelo(codModelo);
            entidade.setPlaca(placa);
            entidade.setUf(uf);

            if(dao.update(entidade)){
                System.out.println(nomeEntidade + " atualizado com sucesso! ");
            }
        }
    }

    @Override
    public void criar() {
        System.out.println("Criar nova " + nomeEntidade);
        System.out.println("Chassi: ");
        String chassi = projetoUtil.scannString();
        System.out.println("Código modelo: ");
        Long codModelo = (long)projetoUtil.scannInt();
        System.out.println("Placa: ");
        String placa = projetoUtil.scannString();
        System.out.println("UF: ");
        String uf = projetoUtil.scannString();

        Veiculo entidade = new Veiculo();
        entidade.setChassi(chassi);
        entidade.setCodModelo(codModelo);
        entidade.setPlaca(placa);
        entidade.setUf(uf);

        if(dao.create(entidade)){
            System.out.println(nomeEntidade + " criada com sucesso! ");
        }
    }
}
