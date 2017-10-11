/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controle;

import br.edu.ifrs.modelo.bean.Perfil;
import br.edu.ifrs.modelo.bean.Setor;
import br.edu.ifrs.modelo.bean.Usuario;
import br.edu.ifrs.modelo.dao.PerfilDAO;
import br.edu.ifrs.modelo.dao.SetorDAO;
import br.edu.ifrs.modelo.dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DTI
 */
@WebServlet(name = "UsuarioControl", urlPatterns = {"/UsuarioControl"})
public class UsuarioControl extends HttpServlet {

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
                //relacionado a autenticação
                case "AUTENTICAR":
                    autenticar(request, response);
                    break;
                case "SAIR":
                    sair(request, response);
                    break;
                case "NOVO":
                    abrir(request, response);
                    break;
            }

        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void abrir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Perfil[] perfis = null;
            perfis = PerfilDAO.consultar("");
            request.setAttribute("perfis", perfis);

            Setor[] setores = null;
            setores = SetorDAO.consultar("");
            request.setAttribute("setores", setores);

            request.getSession().removeAttribute("usuario");
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("formUsuarios.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void inserir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usu = new Usuario();

        try {
            usu.setCpf(request.getParameter("cpf"));
            usu.setMatricula(request.getParameter("matricula"));
            usu.setNome(request.getParameter("nome"));
            usu.setSexo(request.getParameter("sexo"));
            usu.setEndereco(request.getParameter("endereco"));
            usu.setEmail(request.getParameter("email"));
            usu.setTelefoneResidencial(request.getParameter("telefone"));

            Perfil pf = new Perfil();
            pf.setId(Integer.parseInt(request.getParameter("perfil")));
            usu.setPerfil(pf);

            Setor set = new Setor();
            set.setId(Integer.parseInt(request.getParameter("setor")));
            usu.setSetor(set);

            usu.setUsername(request.getParameter("username"));
            usu.setSenha(request.getParameter("senha"), request.getParameter("confsenha"));
            usu.setSituacao(request.getParameter("situacao"));

            UsuarioDAO.adicionar(usu);

            //RequestDispatcher dispatcher = 
            //      request.getRequestDispatcher("index.html");
            //dispatcher.forward(request, response);
            consultar(request, response, "LISTA");
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usu = new Usuario();

        try {
            usu.setCpf(request.getParameter("cpf"));
            usu.setMatricula(request.getParameter("matricula"));
            usu.setNome(request.getParameter("nome"));
            usu.setSexo(request.getParameter("sexo"));
            usu.setEndereco(request.getParameter("endereco"));
            usu.setEmail(request.getParameter("email"));
            usu.setTelefoneResidencial(request.getParameter("telefone"));
            usu.setUsername(request.getParameter("username"));

            Perfil pf = new Perfil();
            pf.setId(Integer.parseInt(request.getParameter("perfil")));
            usu.setPerfil(pf);

            Setor set = new Setor();
            set.setId(Integer.parseInt(request.getParameter("setor")));
            usu.setSetor(set);

            usu.setSituacao(request.getParameter("situacao"));

            UsuarioDAO.atualizar(usu);

            //RequestDispatcher dispatcher = 
            //      request.getRequestDispatcher("index.html");
            //dispatcher.forward(request, response);
            consultar(request, response, "LISTA");
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void consultar(HttpServletRequest request, HttpServletResponse response, String op)
            throws ServletException, IOException {
        try {
            String pagina = "";
            if (op.equals("CONSULTA")) {
                Usuario[] usuarios = null;
                usuarios = UsuarioDAO.pesquisar(request.getParameter("nome"));
                pagina = "consultaUsuario.jsp";
                request.setAttribute("usuarios", usuarios);
            } else {
                if (op.equals("CONSATUALIZAR")) {
                    Usuario usuario = null;
                    usuario = UsuarioDAO.consultar(request.getParameter("cpf"));
                    pagina = "formUsuarios.jsp";
                    request.getSession().setAttribute("usuario", usuario);

                    Perfil[] perfis = null;
                    perfis = PerfilDAO.consultar("");
                    request.setAttribute("perfis", perfis);

                    Setor[] setores = null;
                    setores = SetorDAO.consultar("");
                    request.setAttribute("setores", setores);
                } else {
                    Usuario[] usuarios = null;
                    usuarios = UsuarioDAO.pesquisar("");
                    pagina = "consultaUsuario.jsp";
                    request.setAttribute("usuarios", usuarios);
                }
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

    protected void excluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usu = new Usuario();

        try {

            usu.setCpf(request.getParameter("cpf"));

            UsuarioDAO.deletar(usu);

            //RequestDispatcher dispatcher = 
            //      request.getRequestDispatcher("index.html");
            //dispatcher.forward(request, response);
            consultar(request, response, "LISTA");
        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
     * Autenticação do usuário
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void autenticar(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {
            //chamar o dao e passar o usuário e a senha
            Usuario userLogado = UsuarioDAO.autenticar(
                    new Usuario(request.getParameter("usuario"),
                            request.getParameter("senha")));

            if (userLogado != null) {//se ele existe
                //O objeto é colocado na sessão
                HttpSession session = request.getSession();
                session.setAttribute("usuario", userLogado);
                //redireciona para o index
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);

            } else {
                //manda mensagem para página de login
                request.setAttribute("msg_erro",
                        "ERRO: Login ou Senha não conferem/ Ou usuário inativo !!!");

                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);

            }

        } catch (Exception e) {
            request.setAttribute("msg_erro", e.getMessage());
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }

    }

    /**
     * Deslogar usuário
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void sair(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();//retirar sessão da memória

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

}
