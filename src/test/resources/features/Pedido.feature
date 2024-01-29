# language: pt
Funcionalidade: Gerenciamento de Pedidos

  Cenário: Buscar todos os pedidos
    Dado que tenho pedidos registrados
    Quando eu busco todos os pedidos
    Então deve retornar todos os pedidos

  Cenário: Incluir um novo pedido
    Dado que tenho um novo pedido
    Quando incluo o novo pedido
    Então deve retornar status ok

  Cenário: Buscar fila de pedidos
    Dado que tenho pedidos registrados
    Quando eu busco a fila de pedidos
    Então a resposta deve ser uma lista de pedidos na fila

  Cenário: Atualizar status de um pedido
    Dado que eu tenho um pedido existente
    E eu tenho um novo status para o pedido
    Quando eu faço uma requisição PATCH para "/pedidos/{id}" com o novo status
    Então a resposta deve ser o status atualizado do pedido