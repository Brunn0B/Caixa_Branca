## ETAPA 1
sem identificaçoes de erros:

As cláusulas catch estão vazias, o que pode dificultar a identificação de problemas em tempo de execução.
Correção sugerida: Adicionar mensagens de erro ou registrar logs nos blocos de catch.


Consulta Sql vulneravel:

A construção da query SQL usa concatenação de strings com os valores de entrada (login e senha), tornando o sistema vulnerável a ataques de SQL Injection.
Correção sugerida: Usar consultas preparadas (PreparedStatement) para evitar esse tipo de vulnerabilidade.

Conexão aberta sem fechamento:

A conexão com o banco de dados não é fechada após a execução, podendo causar vazamento de recursos.
Correção sugerida: Utilizar um bloco finally ou o recurso try-with-resources para garantir o fechamento da conexão, Statement e ResultSet.

O código tenta carregar o driver MySQL diretamente (com.mysql.cj.jdbc.Driver) sem verificar se ele está disponível.
Correção sugerida: Adicionar uma verificação explícita para tratar possíveis falhas ao carregar o driver.

Ausência de validação de entrada:

Os parâmetros login e senha não são validados antes de serem usados na query.
Correção sugerida: Verificar e sanitizar os dados de entrada.

Ausência de mensagens de retorno no método conectarBD:

Em caso de falha na conexão, o método não retorna informações úteis, o que dificulta o diagnóstico.
Correção sugerida: Adicionar mensagens detalhadas ou lançar exceções específicas.

Boa prática: Nome do método e atributos:

O método conectarBD poderia ter um nome mais semântico como getConnection.
As variáveis nome e result poderiam ser declaradas dentro do método verificarUsuario, já que não são usadas em outros contextos.





## Grafo de Fluxo de Controle

Abaixo está o grafo de fluxo de controle para o método `verificarUsuario`:

![Grafo de Fluxo de Controle](/ETAPA-3-GRAFO.jpeg)