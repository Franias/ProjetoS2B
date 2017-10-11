/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teste
 */
public class ValidacaoServidorTest {
    
    public ValidacaoServidorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of validarAnoBissexto method, of class ValidacaoServidor.
     */
    /*@Test
    public void testValidarAnoBissexto() {
        System.out.println("validarAnoBissexto");
        int ano = 0;
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarAnoBissexto(ano);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of validarData method, of class ValidacaoServidor.
     */
    @Test
    public void testValidarData() {
        System.out.println("Data Inválida");
        int dia = 31;
        int mes = 11;
        int ano = 2017;
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarData(dia, mes, ano);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testValidarData2(){
        System.out.println("Data Inválida");
        int dia = 31;
        int mes = 9;
        int ano = 2017;
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarData(dia, mes, ano);
        assertEquals(expResult, result);
        
        
    }
    
    @Test
    public void testValidarData3(){
        System.out.println("Data Inválida");
        int dia = 29;
        int mes = 02;
        int ano = 2017;
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarData(dia, mes, ano);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidarData4(){
        System.out.println("Data Inválida");
        int dia = 30;
        int mes = 02;
        int ano = 2017;
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarData(dia, mes, ano);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidarData5(){
        System.out.println("Data Inválida");
        int dia = 31;
        int mes = 2;
        int ano = 2017;
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarData(dia, mes, ano);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidarData6(){
        System.out.println("Data Válida");
        int dia = 29;
        int mes = 2;
        int ano = 2016;
        boolean expResult = true;
        boolean result = ValidacaoServidor.validarData(dia, mes, ano);
        assertEquals(expResult, result);
    }
    /**
     * Test of validarDataSolicitacao method, of class ValidacaoServidor.
     */
   /* @Test
    public void testValidarDataSolicitacao() {
        System.out.println("validarDataSolicitacao");
        int dia = 0;
        int mes = 0;
        int ano = 0;
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarDataSolicitacao(dia, mes, ano);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
