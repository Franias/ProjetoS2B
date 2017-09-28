/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controle;

import br.edu.ifrs.modelo.bean.Setor;
import br.edu.ifrs.modelo.dao.SetorDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DAP
 */
@WebServlet(name = "SetorControl", urlPatterns = {"/SetorControl"})
public class SetorControl extends HttpServlet {

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
            request.getSession().removeAttribute("setor");
            
            Setor[] setores = SetorDAO.consultar("");
            request.setAttribute("setores", setores);
            
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("formSetor.jsp");
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
        Setor s = new Setor();
        
        try {
            
            s.setNome(request.getParameter("nome"));
            
            Setor pai = new Setor();
            pai.setId(Integer.parseInt(request.getParameter("pai")));
            
            s.setPai(pai);
            
            SetorDAO.adicionar(s);

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

    protected void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Setor s = new Setor();
        
        try {
            
            s.setId(Integer.parseInt(request.getParameter("id")));
            s.setNome(request.getParameter("nome"));
            
            Setor pai = new Setor();
            pai.setId(Integer.parseInt(request.getParameter("pai")));
            
            s.setPai(pai);
            
            SetorDAO.atualizar(s);

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
                Setor[] setores = null;
                setores = SetorDAO.consultar(request.getParameter("nome"));
                pagina = "consultaSetor.jsp";
                request.setAttribute("setores", setores);
            } else { 
                if (op.equals("CONSATUALIZAR")) {
                    Setor setor = null;
                    setor = SetorDAO.consultar(Integer.parseInt(request.getParameter("id")));
                    pagina = "formSetor.jsp";
                    Setor[] setores = SetorDAO.consultar("");
                    request.setAttribute("setores", setores);
                    request.getSession().setAttribute("setor", setor);
                } else {
                    Setor[] setores = null;
                    setores = SetorDAO.consultar("");
                    pagina = "consultaSetor.jsp";
                    request.setAttribute("setores", setores);
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
        Setor s = new Setor();
        
        try {
            
            s.setId(Integer.parseInt(request.getParameter("id")));
            
            SetorDAO.deletar(s);

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
