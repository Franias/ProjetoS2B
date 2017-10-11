/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gustavo
 */
public class ValidacaoServidor {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    /**
     *
     * @param ano
     * @return valida ano bissexto
     */
    public static boolean validarAnoBissexto(int ano) {
        return ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0);
    }

    /**
     *
     * @param dia dia digitado
     * @param mes para array de meses
     * @param ano ano bissexto
     * @return
     */
    public static boolean validarData(int dia, int mes, int ano) {
        //monta array com os término dos dias
        int[] meses = {31,
            validarAnoBissexto(ano) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //retorna de o dia escolhio é menor que o último dia do mês, e se é maior que um.
        return (dia <= meses[mes - 1] && dia >= 1);

    }

    /**
     * Validar data do documento não deve ser inferior a data corrente da
     * solicitação.
     *
     * @param dia dia digitado
     * @param mes digitado
     * @param ano digitado
     * @return true se ele for maior ou igual a data do sistema
     */
    public static boolean validarDataSolicitacao(int dia, int mes, int ano) {
        //Criando uma nova data
        Date dataAtual = new Date();
        //definindo o formado da data
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        //armazena em uma string a data autual do sistema
        String data = formatador.format(dataAtual);
        //separa o ano, mes e o dia em três variáveis
        int anoAtual = Integer.parseInt(data.substring(6, 10));
        int mesAtual = Integer.parseInt(data.substring(3, 5));
        int diaAtual = Integer.parseInt(data.substring(0, 2));
        //testa se o mês informado é menor que o mês atual e testa o dia
        boolean flagMes = (mes == mesAtual && dia >= diaAtual);
        //verifica o ano
        return (ano >= anoAtual && (flagMes || mes > mesAtual));
    }

    //metodos criados para validar CPF, Matricula, Email
    //Metodo Validar CPF
    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int i = str.length() - 1, digito; i >= 0; i--) {
            digito = Integer.parseInt(str.substring(i, i + 1));
            soma += digito * peso[peso.length - str.length() + i];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }
    
    //metodos usados no teste

    public static boolean validarCPF(String cpf) {
        if (!cpf.matches("^[0-9]{11}$") || (cpf == null) || (cpf.length() != 11)) {
            return false;
        }
        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean validarMatricula(String matricula) {
        if (matricula.matches("^[0-9]{8}$")) {
            return true;
        } else {
            return false;

        }
    }

    public static boolean validarEmail(String email) {
        if (email.matches("^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$")) {
            return true;
        } else {
            return false;
        }

    }
    
    public static boolean validarSenha(String senha){
         if (!senha.matches("^[0-9]+$")) {
            return false;
        }else{
             return true;
         }
        
    }
}
