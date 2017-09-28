/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controle;

import br.edu.ifrs.modelo.bean.Perfil;
import br.edu.ifrs.modelo.dao.PerfilDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DTI
 */
@WebServlet(name = "PerfilControl", urlPatterns = {"/PerfilControl"})
public class PerfilControl extends HttpServlet {

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
                case "LISTA":
                case "CONSULTA":
                case "CONSATUALIZAR":
                    consultar(request, response, op);
                    break;
                case "INSERIR":
                    inserir(request, response);
                    break;
                case "EXCLUIR":
                    excluir(request, response);
                    break;
                case "ATUALIZAR":
                    atualizar(request, response);
                    break;
                case "NOVO":
                    abrir(request, response);
                    break;
            }
            
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
        
    }
    
    protected void abrir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getSession().removeAttribute("perfil");
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("formPerfil.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
        
    }
    
    protected void inserir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Perfil p = new Perfil();
        
        try {
            
            p.setNome(request.getParameter("nome"));
            p.setDescricao(request.getParameter("descricao"));
            p.setSituacao(request.getParameter("situacao"));

            PerfilDAO.adicionar(p);

            //RequestDispatcher dispatcher = 
             //request.getRequestDispatcher("index.html");
            //dispatcher.forward(request, response);
            consultar(request, response, "LISTA");
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
        
    }

    protected void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Perfil p = new Perfil();
        
        try {
            
            p.setId(Integer.parseInt(request.getParameter("id")));
            p.setNome(request.getParameter("nome"));
            p.setDescricao(request.getParameter("descricao"));
            p.setSituacao(request.getParameter("situacao"));

            PerfilDAO.atualizar(p);

            //RequestDispatcher dispatcher = 
              //      request.getRequestDispatcher("index.html");
            //dispatcher.forward(request, response);
            consultar(request, response, "LISTA");
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
        
    }
    
    protected void consultar(HttpServletRequest request, HttpServletResponse response, String op)
            throws ServletException, IOException {
        try {           
            String pagina = "";
            if (op.equals("CONSULTA")) {
                Perfil[] perfis = null;
                perfis = PerfilDAO.consultar(request.getParameter("nome"));
                pagina = "consultaPerfil.jsp";
                request.setAttribute("perfis", perfis);
            } else { 
                if (op.equals("CONSATUALIZAR")) {
                    Perfil perfil = null;
                    perfil = PerfilDAO.consultar(Integer.parseInt(request.getParameter("id")));
                    pagina = "formPerfil.jsp";
                    request.getSession().setAttribute("perfil", perfil);
                    //request.setAttribute("perfil", perfil);
                } else {
                    Perfil[] perfis = null;
                    perfis = PerfilDAO.consultar("");
                    pagina = "consultaPerfil.jsp";
                    request.setAttribute("perfis", perfis);
                }
            }
            
            
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher(pagina);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
        
    }
    
    protected void excluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Perfil p = new Perfil();
        
        try {
            
            p.setId(Integer.parseInt(request.getParameter("id")));
            
            PerfilDAO.deletar(p);

            //RequestDispatcher dispatcher = 
              //      request.getRequestDispatcher("index.html");
            //dispatcher.forward(request, response);
            consultar(request, response, "CONSULTA");
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("erro.jsp");
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
