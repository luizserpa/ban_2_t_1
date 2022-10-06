import Menus.MenuCadastros;
import Servicos.DeslocamentoService;
import Utilitarios.ProjetoUtil;


public class Main {



    public static void main(String[] args) {
        int opcaoMenu = 0;
        ProjetoUtil projetoUtil = new ProjetoUtil();
        ProjetoUtil.clearScreen();

        DeslocamentoService deslocamentoService = new DeslocamentoService();

        do{
            System.out.println("Menu");
            System.out.println("1. Nova saída.");
            System.out.println("2. Nova entrada.");
            System.out.println("3. Cadastros.");
            System.out.println("4. Consultas.");
            System.out.println("5. Sair.\n");

            System.out.println("Pressione o número da opção desejada.");
            opcaoMenu = projetoUtil.scannInt();

            try {
                switch (opcaoMenu){
                    case 1:
                        ProjetoUtil.clearScreen();
                        deslocamentoService.novaSaida();
                        break;
                    case 2:
                        ProjetoUtil.clearScreen();
                        deslocamentoService.novaEntrada();
                        break;
                    case 3:
                        ProjetoUtil.clearScreen();
                        MenuCadastros.abrirMenuCadastros();
                        break;
                    case 4:
                        ProjetoUtil.clearScreen();
                        MenuCadastros.abrirConsultas();
                        break;
                    case 5:
                        ProjetoUtil.clearScreen();
                        break;
                    default:
                        ProjetoUtil.clearScreen();
                        System.out.println("Opação invalida");
                }
            }catch (Exception ignore){}
        }while (opcaoMenu != 5);
    }

}