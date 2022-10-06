package Servicos;


import DAOs.*;
import Entidades.*;
import Utilitarios.ProjetoUtil;

import java.util.List;

public class DeslocamentoService extends CrudService {

    final String nomeEntidade = "deslocamento";
    DeslocamentoDAO dao = new DeslocamentoDAO();
    PassagemDeslocamentoDAO passagemDeslocamentoDAO = new PassagemDeslocamentoDAO();
    MotoristaDAO motoristaDAO = new MotoristaDAO();
    VeiculoDAO veiculoDAO = new VeiculoDAO();
    ModeloDAO modeloDAO = new ModeloDAO();
    ProjetoUtil projetoUtil = new ProjetoUtil();

    public void crud(){
        ProjetoUtil projetoUtil = new ProjetoUtil();
        int opcaoMenu = 0;
        do {
            System.out.println("Cadastro " + nomeEntidade);
            System.out.println("1. Consulta");
            System.out.println("2. Excluir");
            System.out.println("3. Lista todos");
            System.out.println("4. Lista por status");
            System.out.println("5. Voltar.\n");

            System.out.println("Informe o número da opção desejada.");
            opcaoMenu = projetoUtil.scannInt();

            switch (opcaoMenu){
                case 1:
                    ProjetoUtil.clearScreen();
                    consultar();
                    break;
                case 2:
                    ProjetoUtil.clearScreen();
                    excluir();
                    break;
                case 3:
                    ProjetoUtil.clearScreen();
                    listar();
                    break;
                case 4:
                    ProjetoUtil.clearScreen();
                    listarPorStatus();
                    break;
                case 5:
                    ProjetoUtil.clearScreen();
                    break;
                default:
                    System.out.println("Opção invalida. Favor entre outro valor.");
            }
        }while (opcaoMenu != 6);
    }

    private void listarPorStatus() {
        System.out.println("Listar " + nomeEntidade);
        System.out.println("Favor informar o status do deslocamento para listar.");
        String status = projetoUtil.scannString();
        List<Deslocamento> list = dao.listar();
        if (list != null && !list.isEmpty()){
            System.out.println("Cód des.  Cód. vei  Cód mot.  Descrição                                    Status  ");
            for (Deslocamento e : list){
                if (!e.getStatusDes().equals(status)) continue;
                String s = String.format("%8d  %8d  %8d  %-43s  %-6s", e.getCodDes(), e.getCodVeiculo(), e.getCodMot(), e.getDescricao(), e.getStatusDes());
                System.out.println(s);
            }
        }else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    @Override
    public void listar() {
        System.out.println("Listar " + nomeEntidade);
        List<Deslocamento> list = dao.listar();
        if (list != null && !list.isEmpty()){
            System.out.println("Cód des.  Cód. vei  Cód mot.  Descrição                                    Status  ");
            for (Deslocamento e : list){
                String s = String.format("%8d  %8d  %8d  %-43s  %-6s", e.getCodDes(), e.getCodVeiculo(), e.getCodMot(), e.getDescricao(), e.getStatusDes());
                System.out.println(s);
            }
        }else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    public void listarPorStatus(String status){
        System.out.println("Listar por status" + nomeEntidade);
        List<Deslocamento> list = dao.findByStatus(status);
        if (list != null && !list.isEmpty()){
            System.out.println("Cód des.  Cód. vei  Modelo      Cód mot.  Nome                  Descrição                                    Status  ");
            for (Deslocamento e : list){
                Motorista motorista = motoristaDAO.findById(e.getCodMot());
                Veiculo veiculo = veiculoDAO.findById(e.getCodVeiculo());
                Modelo modelo = modeloDAO.findById(veiculo.getCodModelo());
                String s = String.format("%8d  %8d  %-10s  %8d  %-20s  %-43s  %-6s", e.getCodDes(), e.getCodVeiculo(), modelo.getNome(), e.getCodMot(), motorista.getNome(), e.getDescricao(), e.getStatusDes());
                System.out.println(s);
            }
        }else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    @Override
    public void excluir() {
        System.out.println("Excluir " + nomeEntidade);
        System.out.println("Favor informar o código do deslocamento que deseja excluir, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Deslocamento entidade = dao.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }

            List<PassagemDeslocamento> list = passagemDeslocamentoDAO.findAllByCodDes(id);
            for (PassagemDeslocamento pd : list){
                passagemDeslocamentoDAO.delete(pd.getCodDes(), pd.getSeq());
            }

            if (dao.delete(id)){
                System.out.println("Deslocamento excluido com sucesso!");
            }
        }
    }

    @Override
    public void consultar() {
        System.out.println("Consulta " + nomeEntidade);
        System.out.println("Favor informar o código do deslocamento que deseja consultar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Deslocamento entidade = dao.findById(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            System.out.println(nomeEntidade);
            System.out.println("Código deslocamento: " + entidade.getCodDes());
            System.out.println("Código veiculo: " + entidade.getCodVeiculo());
            System.out.println("Código motorista: " + entidade.getCodMot());
            System.out.println("Descrição: " + entidade.getDescricao());
            System.out.println("Status: " + entidade.getStatusDes());
        }
    }

    @Override
    public void alterar() {
        System.out.println("Alterar " + nomeEntidade);
        System.out.println("Favor informar o código do deslocamento que deseja alterar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Deslocamento entidade = dao.findById(id);

            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            if (entidade.getStatusDes().equals("FI")){
                System.out.println(nomeEntidade + " finalizado. Não pode mais ser alterado.");
                return;
            }

            System.out.println("Informações atuais: ");
            System.out.println("Código deslocamento: " + entidade.getCodDes());
            System.out.println("Código veiculo: " + entidade.getCodVeiculo());
            System.out.println("Código motorista: " + entidade.getCodMot());
            System.out.println("Descrição: " + entidade.getDescricao());
            System.out.println("Status: " + entidade.getStatusDes());

            System.out.println("Novos valores: ");
            System.out.println("Código veiculo: ");
            Long codVeiculo = (long)projetoUtil.scannInt();
            System.out.println("Código deslocamento: ");
            Long codMot = (long)projetoUtil.scannInt();
            System.out.println("Descrição: ");
            String descricao = projetoUtil.scannString();
            System.out.println("Status (EA - Em andamento | FI - Finalizado ): ");
            String status = projetoUtil.scannString();


            entidade.setCodDes(codMot);
            entidade.setCodVeiculo(codVeiculo);
            entidade.setDescricao(descricao);
            entidade.setStatusDes(status);

            if(dao.update(entidade)){
                System.out.println(nomeEntidade + " atualizado com sucesso! ");
            }
        }
    }

    public void alterarStatus(Long id, String status) {
        Deslocamento entidade = dao.findById(id);
        if (entidade == null){
            System.out.println(nomeEntidade + " não encontrado.");
            return;
        }
        if (entidade.getStatusDes().equals("FI")){
            System.out.println(nomeEntidade + " finalizado. Não pode mais ser alterado.");
            return;
        }

        entidade.setStatusDes(status);

        if(dao.update(entidade)){
            //System.out.println(nomeEntidade + " atualizado com sucesso! ");
        }
    }


    public void novaSaida() {
        System.out.println("Código veiculo: ");
        Long codVeiculo = (long)projetoUtil.scannInt();
        System.out.println("Código motorista: ");
        Long codMotorista = (long)projetoUtil.scannInt();
        System.out.println("Descrição: ");
        String descricao = projetoUtil.scannString();


        Deslocamento entidade = new Deslocamento();
        entidade.setCodVeiculo(codVeiculo);
        entidade.setCodMot(codMotorista);
        entidade.setDescricao(descricao);
        entidade.setStatusDes("EA");

        Long id = dao.create(entidade);

        if(id > 0){
            //System.out.println(nomeEntidade + " criada com sucesso! ");
            PassagemDeslocamento pd = new PassagemDeslocamento();
            pd.setCodDes(id);
            pd.setTipoDes("FI");

            passagemDeslocamentoDAO.create(pd);

        }
    }

    public void novaEntrada() {
        System.out.println("Favor informar o código do deslocamento que voltou para a empresa, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Deslocamento entidade = dao.findById(id);

            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            if (entidade.getStatusDes().equals("FI")){
                System.out.println(nomeEntidade + " finalizado. Não pode mais ser alterado.");
                return;
            }

            alterarStatus(id, "FI");

            //System.out.println(nomeEntidade + " atualizado com sucesso! ");
            PassagemDeslocamento pd = new PassagemDeslocamento();
            pd.setCodDes(id);
            pd.setTipoDes("FI");

            passagemDeslocamentoDAO.create(pd);
        }
    }
}
