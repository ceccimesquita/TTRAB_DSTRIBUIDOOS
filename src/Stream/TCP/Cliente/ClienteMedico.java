package Stream.TCP.Cliente;

import modelos.Medico;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClienteMedico {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("[CLIENTE] Conectado ao servidor.");

            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            boolean continuar = true;

            while (continuar) {
                System.out.println("\nMENU");
                System.out.println("1 - Listar médicos");
                System.out.println("2 - Adicionar médico");
                System.out.println("3 - Buscar por nome");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // limpa o \n

                saida.writeInt(opcao);
                saida.flush();

                switch (opcao) {
                    case 1:
                        // Servidor envia lista de médicos
                        List<Medico> medicos = (List<Medico>) entrada.readObject();
                        System.out.println("\nMÉDICOS CADASTRADOS:");
                        for (Medico m : medicos) {
                            System.out.println("Nome: " + m.getNome() + ", CRM: " + m.getCrm() + ", Contato: " + m.getContato());
                        }
                        break;

                    case 2:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CRM: ");
                        String crm = scanner.nextLine();
                        System.out.print("Contato: ");
                        String contato = scanner.nextLine();

                        Medico novo = new Medico(nome, crm, contato);
                        saida.writeObject(novo);
                        saida.flush();

                        String respostaAdd = (String) entrada.readObject();
                        System.out.println(">> " + respostaAdd);
                        break;

                    case 3:
                        System.out.print("Digite o nome do médico a buscar: ");
                        String nomeBusca = scanner.nextLine();
                        saida.writeUTF(nomeBusca);
                        saida.flush();

                        List<Medico> encontrados = (List<Medico>) entrada.readObject();
                        System.out.println("\nRESULTADO:");
                        for (Medico m : encontrados) {
                            System.out.println("Nome: " + m.getNome() + ", CRM: " + m.getCrm() + ", Contato: " + m.getContato());
                        }
                        break;

                    case 0:
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }

            System.out.println("Conexão encerrada.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
