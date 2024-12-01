package com.caixa.branca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    // Ponto 1: Início
    
    public Connection conectarBD() {
        Connection conn = null;
        // Ponto 2: Tenta conectar ao banco de dados
        try {
            // Erro: O código tenta carregar o driver MySQL diretamente sem verificação.
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            
            // Erro: O URL do banco de dados está fixo e pode ser suscetível a falhas.
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url); // Erro: Falta de tratamento de falha ao tentar estabelecer a conexão.
        } catch (Exception e) { 
            // Ponto 3: Exceção de conexão
            // Erro: O bloco catch está vazio e não fornece nenhum diagnóstico em caso de erro.
        }
        // Ponto 4: Retorna conexão (null ou válida)
        return conn; // Erro: Retorno de 'null' sem verificação de falha.
    }
    
    public String nome = ""; 
    public boolean result = false;

    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
         // Ponto 5: Chama conectarBD()
        Connection conn = conectarBD(); // Erro: A conexão não é verificada antes de ser usada.

        // Ponto 6: Montagem da query SQL
        // Erro: A construção da query SQL usa concatenação de strings, tornando o sistema vulnerável a SQL Injection.
        sql += "SELECT nome FROM usuarios ";
        sql += "WHERE login = '" + login + "' ";
        sql += "AND senha = '" + senha + "'";

        try {
            // Ponto 7: Criação de Statement e execução da query
            Statement st = conn.createStatement(); // Erro: O Statement não é fechado após o uso.
            ResultSet rs = st.executeQuery(sql);  // Erro: O ResultSet não é fechado.

            // Ponto 8: Verifica se a query retornou resultados
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
            // Ponto 10: Tratamento de exceção
            // Erro: O bloco catch está vazio e não fornece nenhum diagnóstico em caso de erro.
        }
        // Ponto 11: Retorna o resultado
        return result; // Erro: O método retorna 'result' sem garantir que a conexão foi bem-sucedida.
    }
}
