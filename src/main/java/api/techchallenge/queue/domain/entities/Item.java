package api.techchallenge.queue.domain.entities;

import api.techchallenge.queue.domain.enums.CategoriaProduto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Item {
    public UUID id;
    public UUID idProduto;
    public String nome;
    public String descricao;
    public CategoriaProduto categoria;
    public String observacoes;
    public Integer quantidade;
}