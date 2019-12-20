Problema:
Técnicos em campo da OI perdem muito tempo postando print de protocolos e dando informações para Gerência de Mobilidade via Whats App, e assim serem repassadas a uma planilha. Todo dia, perdemos cerca de 1h fazendo planilha. 

Objetivo:
Reduzir drasticamente o tempo de criação de planilha e passar informações com maior facilidade. 

Ele deverá incluir: 
1. Número de protocolo AC;
2. Site afetado;
3. Local onde ocorreu;
4. Unidade Consumidora;
5. Data e hora da abertura do protocolo;
6. Observações;
7. Filtro de protocolos;
8. Exportar protocolos para tipo de arquivo XLS / XLSX .

## API
**ProtocoloApi**
- Esta classe faz a inclusão de dados do protocolo, deleta, atualiza e busca.

## DTO
**ProtocoloInputDTO**
- Onde é inserido pelo usuário as informações do site, número, unidade consumidora, concessionaria e observações.

**ProtocoloOutputDTO**
- Esta classe exibe as informações necessárias.

**ErroDTO**
- A classe exibe uma mensagem de erro escrita pelo desenvolvedor.

## MAPPER
**ProtocoloMapper**
- Faz o mapeamento das classes **Protocolo**, **ProtocoloInputDTO** e **ProtocoloOutputDTO**. Pegando as informações do **ProtocoloInputDTO** e salvando no **Protocolo** e em seguida, salvando no **ProtocoloOutputDTO**, onde será exibido as informações para o usuário.

## MODEL
**Protocolo**
- Classe com os atributos do protocolo.

## REPOSITORY
**ProtocoloRepository**
- Interface para salvar as informações no banco de dados

## SERVICE
**ProtocoloService**
- Classe onde fica as regras de negócio. É instanciado a classe **ProtocoloRepository** para ter conexao com o banco de dados. Possui métodos de validação, inclusão, exlusão, filtrar por ID, atualizar e buscar.

## SWAGGER
**SwaggerConfig**
- Ajuda na documentação legível e modelagem da aplicação, mostrando todos os métodos e classes do **Model**.

## TEST
**AtualizarProtocoloTest**
- Faz o teste ao atualizar um protocolo criado no *setUP*.

**ExcluirProtocoloTest**
- Faz o teste ao excluir um protocolo criado no *setUP*.

**IncluirProtocoloTest**
- Faz o teste ao incluir um protocolo e valida se ele foi incluido corretamente.

**RetornarProtocoloTest**
-  Faz o teste que retorna um protocolo criado no *setUP* e valida se foi retornado as informações corretas. Também, testa caso retorne vazio.


