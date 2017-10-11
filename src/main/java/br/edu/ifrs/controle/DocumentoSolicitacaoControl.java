/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controle;

import br.edu.ifrs.modelo.bean.DocumentoSolicitado;
import br.edu.ifrs.modelo.bean.Usuario;
import br.edu.ifrs.modelo.dao.DocumentoSolicitadoDAO;
import br.edu.ifrs.util.Email;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo e Eduarda
 */
@WebServlet(name = "DocumentoSolicitacaoControl", urlPatterns = {"/DocumentoSolicitacaoControl"})
public class DocumentoSolicitacaoControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //fazer o teste no controle
            String op = request.getParameter("op");

            switch (op) {
                case "solicitaEmissaoDocumento":
                    solicitarEmissaoDocumento(request, response);
                    break;
                case "pesquisaSolicitacao":
                case "buscar":
                    pesquisaSolicitacao(request, response);
                    break;
                case "buscarSolicitacaoDocumento":
                case "consultarSolicitacaoDocumento":
                case "emitirDocumento":
                    buscarSolicitacaoDocumento(request, response);
                    break;
                case "alterarSolicitacaoDocumento":
                    alterarSolicitacaoDocumento(request, response);
                    break;
            }

        } catch (Exception e) {
            //se der erro vai para página de erro
            request.setAttribute("msg_erro", "Nenhuma opção selecionada!");
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    /**
     * Cadastrar a solicitacao
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void solicitarEmissaoDocumento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DocumentoSolicitado d = new DocumentoSolicitado();

        try {
            //cria um novo objeto da solicitação
            d.setObjetivoDocumento(request.getParameter("objetivo"));
            //data
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String ano = request.getParameter("ano");
            //chamamos o setData
            d.setDataDocumento(dia, mes, ano);

            //d.setDataDocumento(data);
            d.setConteudoDocumento(request.getParameter("conteudoDocumento"));
            //d.setServidorSolicitante(request.getParameter("servidorSolicitante"));
            d.setAnexos(request.getParameter("anexos"));
            //cpf do servidor solicitante (servidor logado no sistema)
            Usuario cpfSolicitado = new Usuario();
            //colocamos o cpf em um objeto do usuário
            cpfSolicitado.setCpf(request.getParameter("cpf"));
            //Email do usuário 
            cpfSolicitado.setEmail(request.getParameter("email"));
            //adicionamos o objeto do usuário na solicitação
            d.setServidorSolicitante(cpfSolicitado);
            //envia para o banco de dados
            DocumentoSolicitadoDAO.enviarSolicitacao(d);
            //Após cadastro o gabinete é notificado
            notificarGabinete(request, response, d);
            //consultar(request, response, "CONSULTA");
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    /**
     * Notificação de gabinete
     *
     * @param request
     * @param response
     * @param documentoSolicitado objeto para envio de email
     * @throws ServletException
     * @throws IOException
     */
    protected void notificarGabinete(HttpServletRequest request,
            HttpServletResponse response,
            DocumentoSolicitado documentoSolicitado) throws ServletException, IOException {

        try {
            //Executa o envio do email para o gabinete e para o usuário solicitante
            Email.enviarEmail("Solicitação de documento foi computada", "Os dados: "
                    + documentoSolicitado.toString(), 
                    documentoSolicitado.getServidorSolicitante().getEmail(),
                    "gabinete@restinga.ifrs.edu.br");

            //manda para página de notificação
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("notificacao.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            //manda mensagem caso email não seja enviado
            request.setAttribute("erro_email", "Não foi possível enviar email ao gabinete "
                    + "por favor entre em contato pelo seguinte número de telefone: 4787878");

            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("notificacao.jsp");
            dispatcher.forward(request, response);

        }

    }

    protected void pesquisaSolicitacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagina;
        try {
            //se a opção for igual a pesquisa
            if (request.getParameter("op").equals("pesquisaSolicitacao")) {

                //pesquisa com campos
                String dataInicial = (request.getParameter("anoInicial") + "/" + request.getParameter("mesInicial")
                        + "/" + request.getParameter("diaInicial"));
                String dataFinal = (request.getParameter("anoFinal") + "/"
                        + request.getParameter("mesFinal") + "/" + request.getParameter("diaFinal"));
                
                String servidorSolicitante = request.getParameter("servidorSolicitante");
                String situacao = request.getParameter("situacao");
                //monta vetor com os documentos
                DocumentoSolicitado[] documentos = DocumentoSolicitadoDAO.consultar(servidorSolicitante,
                        dataInicial, dataFinal, situacao);
                //manda para página da consulta
                pagina = "consultaSolicitacaoDocumentos.jsp";
                request.setAttribute("documentos", documentos);

            } else {

                //pesquisa sem os campos
                DocumentoSolicitado[] documentos = DocumentoSolicitadoDAO.consultar(
                        "", "2000/01/01", "2020/01/01", "");
                //manda para página da consulta
                pagina = "consultaSolicitacaoDocumentos.jsp";
                request.setAttribute("documentos", documentos);

            }
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher(pagina);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    /**
     * Seleciona no banco de dados
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void buscarSolicitacaoDocumento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pagina;

        String op = request.getParameter("op");
        //busca apenas uma solicitação
        DocumentoSolicitado documentoSolicitado = new DocumentoSolicitado(
                Integer.parseInt(request.getParameter("id")));

        try {
            //testa opção
            if (op.equalsIgnoreCase("buscarSolicitacaoDocumento")) {
                pagina = "dados_solicitacao.jsp";//página que vai mandar
            } else if(op.equalsIgnoreCase("consultarSolicitacaoDocumento")){
                pagina = "formSolicitacao.jsp";
                //request.getSession().setAttribute("documento", documento);
            }else{
                pagina = "formDocumentos.jsp";
            }
            //manda para tela de detalhe
            request.setAttribute("sol",
                    DocumentoSolicitadoDAO.consultar(documentoSolicitado));

            RequestDispatcher dispatcher
                    = request.getRequestDispatcher(pagina);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Alterar a solicitação
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    protected void alterarSolicitacaoDocumento(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        DocumentoSolicitado d = new DocumentoSolicitado();
        //volta para consulta
        String pagina = "DocumentoSolicitacaoControl?op=buscar";

        try {

            d.setId(Integer.parseInt(request.getParameter("id")));

            d.setObjetivoDocumento(request.getParameter("objetivo"));

            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String ano = request.getParameter("ano");
            d.setDataDocumento(dia, mes, ano);

            //d.setDataDocumento(data);
            d.setConteudoDocumento(request.getParameter("conteudoDocumento"));
            //d.setServidorSolicitante(request.getParameter("servidorSolicitante"));
            d.setAnexos(request.getParameter("anexos"));
            //executar alteração
            DocumentoSolicitadoDAO.alterarSolicitacao(d);

            RequestDispatcher dispatcher
                    = request.getRequestDispatcher(pagina);
            dispatcher.forward(request, response);
            //consultar(request, response, "CONSULTA");
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

}
