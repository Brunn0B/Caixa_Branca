# Explicação dos Erros Encontrados no Código

Este arquivo contém a explicação dos erros encontrados no código de conexão com o banco de dados MySQL. Os problemas identificados podem comprometer a segurança, a confiabilidade e a manutenção do sistema. A seguir, os detalhes dos erros encontrados:

## 1. Tentativa de Carregar o Driver MySQL Sem Verificação

O código tenta carregar o driver MySQL diretamente com a seguinte linha: ```java
Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
Problema: O código não verifica se o driver MySQL está disponível ou se o carregamento foi bem-sucedido. Caso o driver não esteja presente, o erro não é tratado, o que pode levar a falhas inesperadas durante a execução.

## 2. URL do Banco de Dados Fixada
A URL para a conexão com o banco de dados está fixa no código:

java
Copiar código
String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
Problema: A URL está hardcoded, o que torna o sistema suscetível a falhas. Mudanças no ambiente de execução, como o banco de dados ser hospedado em outro lugar, exigiriam modificações no código. Além disso, as credenciais (usuário e senha) ficam expostas diretamente no código-fonte.

## 3. Falta de Tratamento de Falha ao Estabelecer a Conexão
O método de conexão com o banco de dados retorna null se ocorrer um erro, mas não há nenhum tratamento de erro adequado. Caso a conexão falhe, o código não fornece informações sobre o motivo da falha.

Problema: Isso dificulta a depuração do sistema e torna a identificação da falha mais difícil. Se a conexão falhar, a execução continuará normalmente, sem indicar que houve um erro.

## 4. Bloco catch Vazio
Os blocos catch no código estão vazios:

java
Copiar código
catch (Exception e) { }
Problema: Não há tratamento de exceções. O erro é capturado, mas não é registrado ou tratado de forma que o desenvolvedor ou o sistema possa identificar o que causou a falha. Isso é uma má prática de programação, pois torna difícil diagnosticar problemas e entender por que o código falhou.

## 5. Retorno de null Sem Verificação de Falha
Quando ocorre uma falha na conexão, o método conectarBD retorna null sem realizar nenhuma verificação adicional.

Problema: A conexão não é verificada adequadamente antes de ser usada em outras partes do código. Isso pode resultar em uma exceção NullPointerException se o código tentar acessar ou usar a conexão quando ela não foi estabelecida corretamente.

## 6. A Conexão Não é Verificada Antes de Ser Usada
No método verificarUsuario, a conexão com o banco de dados é criada com:

java
Copiar código
Connection conn = conectarBD();
Problema: Não há verificação para garantir que a conexão foi bem-sucedida. Se a conexão falhar e retornar null, o código tentará usá-la, o que pode resultar em exceções, como NullPointerException.

## 7. Uso de Concatenação de Strings para Construir a Consulta SQL
A consulta SQL é construída diretamente com concatenação de strings:

java
Copiar código
sql += "WHERE login = '" + login + "' ";
sql += "AND senha = '" + senha + "' ";
Problema: Isso cria uma vulnerabilidade conhecida como SQL Injection. Um atacante pode manipular os valores de entrada (login e senha) para injetar comandos SQL maliciosos, comprometendo a segurança do banco de dados.

## 8. O Statement e ResultSet Não São Fechados
Os objetos Statement e ResultSet não são fechados após o uso:

java
Copiar código
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery(sql);
Problema: Não fechar esses objetos pode resultar em vazamento de recursos, o que impacta negativamente a performance do sistema. Conexões abertas, Statement e ResultSet não liberados podem esgotar os recursos do sistema, como conexões de banco de dados, causando problemas de desempenho.

## 9. Bloco catch no Método verificarUsuario Vazio
Assim como no método conectarBD, o bloco catch no método verificarUsuario também está vazio:

java
Copiar código
catch (Exception e) { }
Problema: Falta tratamento de exceções no método. Isso impede que erros sejam diagnosticados e pode tornar a aplicação difícil de manter. Capturar exceções sem tratá-las adequadamente pode esconder erros reais do sistema.

## 10. O Método Retorna result Sem Garantir Sucesso na Conexão
O método verificarUsuario retorna o valor de result sem garantir que a conexão foi estabelecida corretamente:

java
Copiar código
return result;
Problema: O código pode retornar um valor incorreto (por exemplo, true) mesmo quando a conexão ao banco de dados não foi bem-sucedida ou a consulta falhou. Isso pode levar a resultados errados e enganosos, dificultando a identificação de falhas na lógica do programa.

//--------------------------------------------------------------------------------------------------------------------------------//



## Grafo de Fluxo de Controle

Abaixo está o grafo de fluxo de controle para o método `verificarUsuario`:

![Grafo de Fluxo de Controle](/ETAPA-3-GRAFO.jpeg)



## Complexidade Ciclomática

A complexidade ciclomática do código é calculada como **3**, conforme a seguinte fórmula:

\[V(G) = E - N + 2P\]

Onde:
- **E** é o número de arestas no grafo de fluxo de controle: 12
- **N** é o número de nodos: 11
- **P** é o número de componentes conexos: 1

Com isso, temos que a **complexidade ciclomática** do código é **3**.

---

## Caminhos Independentes

Com base na complexidade ciclomática, identificamos os seguintes **caminhos independentes** no código:

### Caminho 1:
**N1 → N5 → N6 → N7 → N8 → N9 → N11**

Este caminho segue sem passar por exceções.

### Caminho 2:
**N1 → N5 → N6 → N7 → N10 → N11**

Este caminho segue pelo bloco de exceção no `try/catch`.

### Caminho 3:
**N1 → N5 → N6 → N7 → N8 → N10 → N11**

Este caminho segue após uma falha na autenticação ou banco de dados, passando pela exceção.

---

Esses caminhos independentes ajudam a entender as diferentes trajetórias que o código pode tomar durante a execução.
