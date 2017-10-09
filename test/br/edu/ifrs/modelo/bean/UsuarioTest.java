/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.bean;

import br.edu.ifrs.util.ValidacaoServidor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teste
 */
public class UsuarioTest {

    public UsuarioTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getCpf method, of class Usuario.
     */
    /*@Test
    public void testGetCpf() {
        System.out.println("getCpf");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getCpf();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setCpf method, of class Usuario.
     */
    @Test
    public void testSetCpf() throws Exception {
        System.out.println("Cpf Valido");
        String cpf = "29768130512";
        Usuario instance = new Usuario();
        instance.setCpf(cpf);
        boolean expResult = true;
        boolean result = ValidacaoServidor.validarCPF(cpf);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testSetCpfInvalidoBranco() throws Exception {
        System.out.println("Cpf Invalido Branco");
        String cpf = "";
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarCPF(cpf);
        assertEquals(expResult, result);
    }
    
    @Test
     public void testSetCpfInvalidoLetras() throws Exception {
        System.out.println("Cpf Invalido letras");
        String cpf = "asd";
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarCPF(cpf);
        assertEquals(expResult, result);
    }
     
    /* @Test
      public void testSetCpfInvalidoLetras2() throws Exception {
        System.out.println("Cpf Invalido letras 11 digitos");
        String cpf = "aaaaaaaaaaa";
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarCPF(cpf);
        assertEquals(expResult, result);
    }*/
     
     @Test
      public void testSetCpfInvalidoNumeros() throws Exception {
        System.out.println("Cpf Invalido");
        String cpf = "123456789";
        boolean expResult = false;
        boolean result = ValidacaoServidor.validarCPF(cpf);
        assertEquals(expResult, result);
    }
   
    
    /**
     * Test of getNome method, of class Usuario.
     */
    /*@Test
    public void testGetNome() {
        System.out.println("getNome");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of setNome method, of class Usuario.
     */
    /* @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "";
        Usuario instance = new Usuario();
        instance.setNome(nome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getMatricula method, of class Usuario.
     */
    /* @Test
    public void testGetMatricula() {
        System.out.println("getMatricula");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getMatricula();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setMatricula method, of class Usuario.
     */
    @Test
    public void testSetMatricula() throws Exception {
        System.out.println("Matricula Valida");
        String matricula = "10078965";
         boolean expResult = true;
        boolean result = ValidacaoServidor.validarMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
      @Test
    public void testSetMatriculaInvalida() throws Exception {
        System.out.println("Matricula Invalida");
        String matricula = "1234567";
         boolean expResult = false;
        boolean result = ValidacaoServidor.validarMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
      @Test
    public void testSetMatriculaInvalida2() throws Exception {
        System.out.println("Matricula quantidade de numeros invalidos");
        String matricula = "123456789";
         boolean expResult = false;
        boolean result = ValidacaoServidor.validarMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
      @Test
    public void testSetMatriculaLestras() throws Exception {
        System.out.println("Matricula invalida");
        String matricula = "abc";
         boolean expResult = false;
        boolean result = ValidacaoServidor.validarMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
    
   /*   @Test
    public void testSetMatriculaInvalidaLetras() throws Exception {
        System.out.println("Matricula Invalida Letras");
        String matricula = "abcdefgh";
         boolean expResult = false;
        boolean result = ValidacaoServidor.validarMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }*/
    
    
    
    
    /**
     * Test of getSexo method, of class Usuario.
     */
    /*@Test
    public void testGetSexo() {
        System.out.println("getSexo");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getSexo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setSexo method, of class Usuario.
     */
    /*@Test
    public void testSetSexo() {
        System.out.println("setSexo");
        String sexo = "";
        Usuario instance = new Usuario();
        instance.setSexo(sexo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getEndereco method, of class Usuario.
     */
    /* @Test
    public void testGetEndereco() {
        System.out.println("getEndereco");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getEndereco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setEndereco method, of class Usuario.
     */
    /* @Test
    public void testSetEndereco() {
        System.out.println("setEndereco");
        String endereco = "";
        Usuario instance = new Usuario();
        instance.setEndereco(endereco);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getEmail method, of class Usuario.
     */
    /*@Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setEmail method, of class Usuario.
     */
    /* @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Usuario instance = new Usuario();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getTelefoneResidencial method, of class Usuario.
     */
    /* @Test
    public void testGetTelefoneResidencial() {
        System.out.println("getTelefoneResidencial");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getTelefoneResidencial();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setTelefoneResidencial method, of class Usuario.
     */
    /* @Test
    public void testSetTelefoneResidencial() {
        System.out.println("setTelefoneResidencial");
        String telefoneResidencial = "";
        Usuario instance = new Usuario();
        instance.setTelefoneResidencial(telefoneResidencial);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getTelefoneProfissional method, of class Usuario.
     */
    /* @Test
    public void testGetTelefoneProfissional() {
        System.out.println("getTelefoneProfissional");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getTelefoneProfissional();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setTelefoneProfissional method, of class Usuario.
     */
    /*  @Test
    public void testSetTelefoneProfissional() {
        System.out.println("setTelefoneProfissional");
        String telefoneProfissional = "";
        Usuario instance = new Usuario();
        instance.setTelefoneProfissional(telefoneProfissional);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getTelefoneCelular method, of class Usuario.
     */
    /* @Test
    public void testGetTelefoneCelular() {
        System.out.println("getTelefoneCelular");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getTelefoneCelular();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setTelefoneCelular method, of class Usuario.
     */
    /* @Test
    public void testSetTelefoneCelular() {
        System.out.println("setTelefoneCelular");
        String telefoneCelular = "";
        Usuario instance = new Usuario();
        instance.setTelefoneCelular(telefoneCelular);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getPerfil method, of class Usuario.
     */
    /* @Test
    public void testGetPerfil() {
        System.out.println("getPerfil");
        Usuario instance = new Usuario();
        Perfil expResult = null;
        Perfil result = instance.getPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setPerfil method, of class Usuario.
     */
    /* @Test
    public void testSetPerfil() {
        System.out.println("setPerfil");
        Perfil perfil = null;
        Usuario instance = new Usuario();
        instance.setPerfil(perfil);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getSetor method, of class Usuario.
     */
    /*@Test
    public void testGetSetor() {
        System.out.println("getSetor");
        Usuario instance = new Usuario();
        Setor expResult = null;
        Setor result = instance.getSetor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setSetor method, of class Usuario.
     */
    /*@Test
    public void testSetSetor() {
        System.out.println("setSetor");
        Setor setor = null;
        Usuario instance = new Usuario();
        instance.setSetor(setor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getUsername method, of class Usuario.
     */
    /*@Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setUsername method, of class Usuario.
     */
    /*@Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        Usuario instance = new Usuario();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getSenha method, of class Usuario.
     */
    /*@Test
    public void testGetSenha() {
        System.out.println("getSenha");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getSenha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setSenha method, of class Usuario.
     */
    /*@Test
    public void testSetSenha_String() {
        System.out.println("setSenha");
        String senha = "";
        Usuario instance = new Usuario();
        instance.setSenha(senha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setSenha method, of class Usuario.
     */
    /*@Test
    public void testSetSenha_String_String() throws Exception {
        System.out.println("setSenha");
        String s = "";
        String cs = "";
        Usuario instance = new Usuario();
        instance.setSenha(s, cs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getSituacao method, of class Usuario.
     */
    /* @Test
    public void testGetSituacao() {
        System.out.println("getSituacao");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getSituacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setSituacao method, of class Usuario.
     */
    /*@Test
    public void testSetSituacao() {
        System.out.println("setSituacao");
        String situacao = "";
        Usuario instance = new Usuario();
        instance.setSituacao(situacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of getObservacoes method, of class Usuario.
     */
    /*@Test
    public void testGetObservacoes() {
        System.out.println("getObservacoes");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getObservacoes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    /**
     * Test of setObservacoes method, of class Usuario.
     */
    /*@Test
    public void testSetObservacoes() {
        System.out.println("setObservacoes");
        String observacoes = "";
        Usuario instance = new Usuario();
        instance.setObservacoes(observacoes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
}
