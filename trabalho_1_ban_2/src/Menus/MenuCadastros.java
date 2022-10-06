package Menus;

import Entidades.Marca;
import Servicos.*;
import Utilitarios.ProjetoUtil;

public class MenuCadastros {

    static MotoristaService motoristaService = new MotoristaService();
    static MarcaService marcaService = new MarcaService();
    static ModeloService modeloService = new ModeloService();
    static VeiculoService veiculoService = new VeiculoService();
    static DeslocamentoService deslocamentoService = new DeslocamentoService();
    static IncidenteService incidenteService = new IncidenteService();

    public static void abrirMenuCadastros(){
        ProjetoUtil projetoUtil = new ProjetoUtil();
        int opcaoMenu = 0;
        do {
            System.out.println("Menu cadastros");
            System.out.println("1. Cadastrar motorista");
            System.out.println("2. Cadastrar marca");
            System.out.println("3. Cadastrar modelo");
            System.out.println("4. Cadastrar veiculo");
            System.out.println("5. Cadastrar deslocamentos");
            System.out.println("6. Cadastrar incidentes");
            System.out.println("7. Voltar.\n");

            System.out.println("Pressione o número da opção desejada.");
            opcaoMenu = projetoUtil.scannInt();

            switch (opcaoMenu){
                case 1:
                    ProjetoUtil.clearScreen();
                    motoristaService.crud();
                    break;
                case 2:
                    ProjetoUtil.clearScreen();
                    marcaService.crud();
                    break;
                case 3:
                    ProjetoUtil.clearScreen();
                    modeloService.crud();
                    break;
                case 4:
                    ProjetoUtil.clearScreen();
                    veiculoService.crud();
                    break;
                case 5:
                    ProjetoUtil.clearScreen();
                    deslocamentoService.crud();
                    break;
                case 6:
                    ProjetoUtil.clearScreen();
                    incidenteService.crud();
                    break;
                case 7:
                    ProjetoUtil.clearScreen();
                    break;
                default:
                    ProjetoUtil.clearScreen();
                    System.out.println("Opação invalida");
            }
        }while (opcaoMenu != 7);

    }

    public static void abrirConsultas() {
        ProjetoUtil projetoUtil = new ProjetoUtil();
        int opcaoMenu = 0;
        do {
            System.out.println("Menu consultas");
            System.out.println("1. Deslocamentos ativos");
            System.out.println("2. Incidentes por motorista");
            System.out.println("3. Incidentes por veiculo");
            System.out.println("4. Voltar.\n");

            System.out.println("Pressione o número da opção desejada.");
            opcaoMenu = projetoUtil.scannInt();

            switch (opcaoMenu){
                case 1:
                    ProjetoUtil.clearScreen();
                    deslocamentoService.listarPorStatus("EA");
                    break;
                case 2:
                    ProjetoUtil.clearScreen();
                    incidenteService.listarPorMotorista();
                    break;
                case 3:
                    ProjetoUtil.clearScreen();
                    incidenteService.listarPorVeiculo();
                    break;
                case 4:
                    ProjetoUtil.clearScreen();
                    break;
                default:
                    ProjetoUtil.clearScreen();
                    System.out.println("Opação invalida");
            }
        }while (opcaoMenu != 4);
    }
}
