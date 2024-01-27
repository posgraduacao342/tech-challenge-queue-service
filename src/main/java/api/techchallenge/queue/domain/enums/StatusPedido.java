package api.techchallenge.queue.domain.enums;

import java.util.ArrayList;
import java.util.List;

public enum StatusPedido {
    RECEBIDO, EM_PREPARACAO, PRONTO, FINALIZADO, CANCELADO;

    public static List<StatusPedido> statusAtivos(){
        var statusAtivos = new ArrayList<StatusPedido>();
        statusAtivos.add(RECEBIDO);
        statusAtivos.add(EM_PREPARACAO);
        statusAtivos.add(PRONTO);

        return statusAtivos;
    }
}
