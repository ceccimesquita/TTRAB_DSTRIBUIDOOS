package Stream.TCP.Servidor;

import modelos.Medico;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServidorMedico {

    private static final List<Medico> listaMedicos = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(5000)) {
            System.out.println("[SERVIDOR] Aguardando conexões na porta 5000...");

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("\n✅ Novo cliente conectado: " + socket.getInetAddress());

                new Thread(() -> tratarCliente(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("[ERRO] Servidor falhou: " + e.getMessage());
        }
    }

    private static void tratarCliente(Socket socket) {
        try (
                ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())
        ) {
            boolean ativo = true;

            while (ativo) {
                int opcao = entrada.readInt();

                switch (opcao) {
                    case 1: // LISTAR
                        System.out.println("[SERVIDOR] Cliente solicitou: LISTAR_MEDICOS");
                        saida.writeObject(new ArrayList<>(listaMedicos));
                        saida.flush();
                        break;

                    case 2: // ADICIONAR
                        Medico novo = (Medico) entrada.readObject();
                        listaMedicos.add(novo);
                        System.out.println("[SERVIDOR] Médico adicionado: " + novo.getNome() + ", CRM: " + novo.getCrm());
                        saida.writeObject("Médico " + novo.getNome() + " adicionado com sucesso.");
                        saida.flush();
                        break;

                    case 3: // BUSCAR POR NOME
                        String nomeBusca = entrada.readUTF();
                        System.out.println("[SERVIDOR] Buscando médicos com nome: " + nomeBusca);
                        List<Medico> encontrados = listaMedicos.stream()
                                .filter(m -> m.getNome().equalsIgnoreCase(nomeBusca))
                                .collect(Collectors.toList());
                        saida.writeObject(encontrados);
                        saida.flush();
                        break;

                    case 0:
                        System.out.println("[SERVIDOR] Cliente solicitou encerramento.");
                        ativo = false;
                        break;

                    default:
                        System.out.println("[SERVIDOR] Operação inválida: " + opcao);
                        saida.writeObject("Operação inválida.");
                        saida.flush();
                }
            }

            socket.close();
            System.out.println("❌ Cliente desconectado.");

        } catch (Exception e) {
            System.err.println("[ERRO] Falha ao tratar cliente: " + e.getMessage());
        }
    }
}
