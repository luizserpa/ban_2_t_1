package Servicos;


import DAOs.IncidenteDAO;
import Entidades.DeslocamentoIncidente;
import Entidades.Incidente;
import Utilitarios.ProjetoUtil;

import java.util.List;

public class IncidenteService extends CrudService {

    final String nomeEntidade = "incidente";
    IncidenteDAO dao = new IncidenteDAO();
    ProjetoUtil projetoUtil = new ProjetoUtil();

    public void crud(){
        ProjetoUtil projetoUtil = new ProjetoUtil();
        int opcaoMenu = 0;
        do {
            System.out.println("Cadastro " + nomeEntidade);
            System.out.println("1. Novo");
            System.out.println("2. Alterar");
            System.out.println("3. Consulta");
            System.out.println("4. Excluir");
            System.out.println("5. Lista todos");
            System.out.println("6. Lista por tipo");
            System.out.println("7. Voltar.\n");

            System.out.println("Informe o número da opção desejada.");
            opcaoMenu = projetoUtil.scannInt();

            switch (opcaoMenu){
                case 1:
                    ProjetoUtil.clearScreen();
                    criar();
                    break;
                case 2:
                    ProjetoUtil.clearScreen();
                    alterar();
                    break;
                case 3:
                    ProjetoUtil.clearScreen();
                    consultar();
                    break;
                case 4:
                    ProjetoUtil.clearScreen();
                    excluir();
                    break;
                case 5:
                    ProjetoUtil.clearScreen();
                    listar();
                    break;
                case 6:
                    ProjetoUtil.clearScreen();
                    listarPorTipo();
                    break;
                case 7:
                    ProjetoUtil.clearScreen();
                    break;
                default:
                    System.out.println("Opção invalida. Favor entre outro valor.");
            }
        }while (opcaoMenu != 7);
    }

    private void listarPorTipo() {
        System.out.println("Listar " + nomeEntidade);
        System.out.println("Favor informar o tipo incidente para listar.");
        String tipo = projetoUtil.scannString();
        List<Incidente> list = dao.listarPorTipo(tipo);
        if (list != null && !list.isEmpty()){
            System.out.println("Cód des.  Cód inc.  tipo  Descrição                              ");
            for (Incidente e : list){
                String s = String.format("%8d  %8d  %s  %-43s", e.getCodDes(), e.getCodInc(), e.getTipoInc(), e.getDescricao());
                System.out.println(s);
            }
        }else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    @Override
    public void listar() {
        System.out.println("Listar " + nomeEntidade);
        List<Incidente> list = dao.listar();
        if (list != null && !list.isEmpty()){
            System.out.println("Cód des.  Cód inc.  tipo  Descrição                              ");
            for (Incidente e : list){
                String s = String.format("%8d  %8d  %s  %-43s", e.getCodDes(), e.getCodInc(), e.getTipoInc(), e.getDescricao());
                System.out.println(s);
            }
        }else {
            System.out.println("Nenhum registro foi encontrado.");
        }
    }

    @Override
    public void excluir() {
        System.out.println("Excluir " + nomeEntidade);
        System.out.println("Favor informar o código do incidente que deseja excluir, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Incidente entidade = dao.findByCodInc(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }

            if (dao.delete(entidade.getCodDes(), entidade.getCodInc())){
                System.out.println("Deslocamento excluido com sucesso!");
            }
        }
    }

    @Override
    public void consultar() {
        System.out.println("Consulta " + nomeEntidade);
        System.out.println("Favor informar o código do incidente que deseja consultar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Incidente entidade = dao.findByCodInc(id);
            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }
            System.out.println(nomeEntidade);
            System.out.println("Código deslocamento: " + entidade.getCodDes());
            System.out.println("Código incidente: " + entidade.getCodInc());
            System.out.println("Tipo: " + entidade.getTipoInc());
            System.out.println("Descrição: " + entidade.getDescricao());
        }
    }

    @Override
    public void alterar() {
        System.out.println("Alterar " + nomeEntidade);
        System.out.println("Favor informar o código do veiculo que deseja alterar, ou 0 para voltar.");
        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            Incidente entidade = dao.findByCodInc(id);

            if (entidade == null){
                System.out.println(nomeEntidade + " não encontrado.");
                return;
            }

            System.out.println("Informações atuais: ");
            System.out.println("Código deslocamento: " + entidade.getCodDes());
            System.out.println("Código incidente: " + entidade.getCodInc());
            System.out.println("Tipo: " + entidade.getTipoInc());
            System.out.println("Descrição: " + entidade.getDescricao());

            System.out.println("Novos valores: ");
            System.out.println("Descrição: ");
            String descricao = projetoUtil.scannString();
            System.out.println("Tipo (A - Acidente | F - Falha mecanica | M - Multa ): ");
            String tipo = projetoUtil.scannString();

            entidade.setTipoInc(tipo);
            entidade.setDescricao(descricao);

            if(dao.update(entidade)){
                System.out.println(nomeEntidade + " atualizado com sucesso! ");
            }
        }
    }

    @Override
    public void criar() {
        System.out.println("Criar nova " + nomeEntidade);
        System.out.println("Código deslocamento: ");
        long codDes = projetoUtil.scannInt();
        System.out.println("Tipo (A - Acidente | F - Falha mecanica | M - Multa ): ");
        String tipo = projetoUtil.scannString();
        System.out.println("Descrição: ");
        String descricao = projetoUtil.scannString();

        Incidente entidade = new Incidente();
        entidade.setCodDes(codDes);
        entidade.setTipoInc(tipo);
        entidade.setDescricao(descricao);

        if(dao.create(entidade)){
            System.out.println(nomeEntidade + " criada com sucesso! ");
        }
    }


    public void listarPorMotorista() {
        System.out.println("Listar incidente por motorista");
        System.out.println("Favor informar o código do motorista que deseja buscar, ou 0 para voltar.");

        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            List<DeslocamentoIncidente> list = dao.findAllDeslocamentoIncidenteByCodMot(id);
            System.out.println("Cód des.  Descrição deslocamento                   Cód. vei  Nome                  Cód mot.  Tipo inc.  Descriçao    ");
            for (DeslocamentoIncidente di : list){
                String s = String.format("%8d  %-39s  %8d  %-20s  %8d  %-9s  %-43s", di.getCodDes(), di.getDescricao(), di.getCodVeiculo(), di.getVeiculo(), di.getCodMot(), di.getTipoInc(), di.getDescricaoIncidente());
                System.out.println(s);
            }
        }
    }

    public void listarPorVeiculo() {
        System.out.println("Listar incidente por veiculo");
        System.out.println("Favor informar o código do veiculo que deseja buscar, ou 0 para voltar.");

        long id = (long)projetoUtil.scannInt();
        if (id > 0){
            List<DeslocamentoIncidente> list = dao.findAllDeslocamentoIncidenteByCodVeiculo(id);
            System.out.println("Cód des.  Descrição deslocamento                   Cód. vei  Cód mot.  Nome                  Tipo inc. Descriçao    ");
            for (DeslocamentoIncidente di : list){
                String s = String.format("%8d  %-39s  %8d  %8d  %-20s  %-9s  %-43s", di.getCodDes(), di.getDescricao(), di.getCodVeiculo(), di.getCodMot(), di.getMotorista(), di.getTipoInc(), di.getDescricaoIncidente());
                System.out.println(s);
            }
        }
    }
}
