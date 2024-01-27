package api.techchallenge.queue.builders;

import api.techchallenge.queue.domain.entities.Item;
import api.techchallenge.queue.domain.enums.CategoriaProduto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {
    public static Item build(){
        return Item.builder()
                .id(UUID.randomUUID())
                .idProduto(UUID.randomUUID())
                .nome("Classic Burger")
                .descricao("Pão, hamburguer, queijo, alface")
                .observacoes("Sem alface")
                .categoria(CategoriaProduto.LANCHE)
                .build();
    }

    public static List<Item> buildList(){
        return Arrays.asList(
                build(),
                build()
        );
    }
}
