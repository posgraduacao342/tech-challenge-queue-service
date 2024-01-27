package api.techchallenge.queue.domain.entities;

import api.techchallenge.queue.domain.enums.CategoriaProduto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Item {
    private UUID id;
    private UUID idProduto;
    private String nome;
    private String descricao;
    private CategoriaProduto categoria;
    private String observacoes;
    private Integer quantidade;
}