package repository;

import model.ClienteExemplo;

import java.util.ArrayList;
import java.util.List;

    public class ClienteDAOExemplo {

        static List<ClienteExemplo> clientes = new ArrayList<>();

        public static void salvar(ClienteExemplo cliente) {
            clientes.add(cliente);
        }

        public static List<ClienteExemplo> buscaTodos() {
            return clientes;
        }

    }

