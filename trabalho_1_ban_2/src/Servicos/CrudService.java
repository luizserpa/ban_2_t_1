package Servicos;

import Utilitarios.ProjetoUtil;

public abstract class CrudService {
    public void crud(String tabela){
        ProjetoUtil projetoUtil = new ProjetoUtil();
        int opcaoMenu = 0;
        do {
            System.out.println("Cadastro " + tabela);
            System.out.println("1. Novo");
            System.out.println("2. Alterar");
            System.out.println("3. Consulta");
            System.out.println("4. Excluir");
            System.out.println("5. Lista todos");
            System.out.println("6. Voltar.\n");

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
                    break;
                default:
                    System.out.println("Opção invalida. Favor entre outro valor.");
            }
        }while (opcaoMenu != 6);
    }

    public void listar() {
    }

    public void excluir() {
    }

    public void consultar() {
    }

    public void alterar() {
    }

    public void criar() {

    }
}
