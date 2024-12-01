package com.caixa.branca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe User responsável pela conexão com o banco de dados e verificação de usuários.
 */
public class User {
    // Ponto 1: Início
    /** 
     * Método que conecta ao banco de dados MySQL.
     * @return A conexão com o banco de dados, ou null se não conseguir estabelecer a conexão.
     */
    public Connection conectarBD() {
        Connection conn = null;
        // Ponto 2: Tenta conectar ao banco de dados
        try {
             // Erro: O código tenta carregar o driver MySQL diretamente sem verificação.
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            
           // Erro: O URL do banco de dados está fixo e pode ser suscetível a falhas.
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url); // Conecta ao banco de dados
        } catch (Exception e) { 
            // Ponto 3: Exceção de conexão
            // Erro: O bloco catch está vazio e não fornece nenhum diagnóstico em caso de erro.
        }
        // Ponto 4: Retorna a conexão, que pode ser null se falhar
        return conn; // Erro: Retorno de 'null' sem verificação de falha.
    }
    
    /** Nome do usuário autenticado */
    public String nome = ""; 
    
    /** Resultado da verificação de usuário (true ou false) */
    public boolean result = false;

    /**
     * Verifica se o usuário existe no banco de dados e tem a senha correta.
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return true se o usuário for encontrado e a senha estiver correta, caso contrário false.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
         // Ponto 5: Chama conectarBD() para estabelecer a conexão
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
            // O bloco catch está vazio e não fornece nenhum diagnóstico em caso de erro.
        }
        // Ponto 11: Retorna o resultado
        return result; // O método retorna 'result' sem garantir que a conexão foi bem-sucedida.
    }
}
