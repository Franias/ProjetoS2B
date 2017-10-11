/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controle;

import br.edu.ifrs.modelo.bean.DocumentoOficial;
import br.edu.ifrs.modelo.bean.DocumentoSolicitado;
import br.edu.ifrs.modelo.bean.Usuario;
import br.edu.ifrs.modelo.dao.DocumentoOficialDAO;
import br.edu.ifrs.modelo.dao.DocumentoSolicitadoDAO;
import br.edu.ifrs.util.PDF;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cassiano
 */
@WebServlet(name = "DocumentoOficialControl", urlPatterns = {"/DocumentoOficialControl"})
public class DocumentoOficialControl extends HttpServlet {

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

            String op = request.getParameter("op");

            switch (op) {
                case "emissaoDocumentoOficial":
                    emissaoDocumentoOficial(request, response);
                    break;
                case "pesquisaDocumentosOficiais":
                    pesquisaDocumentosOficiais(request, response);
                    break;
            }

        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    /**
     * Alterar situação da solicitação
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void emissaoDocumentoOficial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            //objeto do documento oficial
            DocumentoOficial d = new DocumentoOficial();

            d.setNumero(Integer.parseInt(request.getParameter("numero")));
            d.setAno(Integer.parseInt(request.getParameter("ano")));
            d.setTipo(request.getParameter("tipo"));
            d.setConteudo(request.getParameter("conteudo"));
            d.setAnexo(request.getParameter("anexos"));
            //cpf do usuário logado no sistema
            Usuario cpfEmitente = new Usuario();
            cpfEmitente.setCpf(request.getParameter("cpf"));
            d.setUsuario(cpfEmitente);

            //altera a situação para concluída
            DocumentoOficialDAO.enviarDocumento(d);
            DocumentoSolicitadoDAO.alterarSituacao(new DocumentoSolicitado(d.getNumero()));

            //gerar pdf após emissão
            gerarPDFDocumentoOficial(request, response, d);

        } catch (Exception e) {
            //erro se ouver
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    //pesquisaDocumentosOficiais
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void pesquisaDocumentosOficiais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagina = "";
        try {

            String periodo1 = (request.getParameter("anoInicial") + "/" + request.getParameter("mesInicial")
                    + "/" + request.getParameter("diaInicial"));

            String periodo2 = (request.getParameter("anoFinal") + "/" + request.getParameter("mesFinal")
                    + "/" + request.getParameter("diaFinal"));

            String palavraChave = request.getParameter("chave");
            String tipo = request.getParameter("tipo");

            DocumentoOficial[] documentos = DocumentoOficialDAO.consultar(palavraChave, periodo1, periodo2, tipo);
            pagina = "consultaDocumentoOficial.jsp";
            request.setAttribute("documentos", documentos);

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
     * Gerar PDF
     *
     * @param request
     * @param response
     * @param documentoOficial objeto do documento oficial
     * @throws ServletException
     * @throws IOException
     */
    protected void gerarPDFDocumentoOficial(HttpServletRequest request,
            HttpServletResponse response, DocumentoOficial documentoOficial) throws ServletException, IOException {

        try {

            //manda para página de geração de PDF
            request.setAttribute("link", PDF.gerarPDF(documentoOficial));
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("geracaoPDF.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {

            request.setAttribute("msg_erro",
                    "Não foi possível gerar pdf entre em contato com a TI no seguinte número de telefone 44455445"
                    + "Infornações técnicas: " + e.getMessage());

            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("geracaoPDF.jsp");
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

}
