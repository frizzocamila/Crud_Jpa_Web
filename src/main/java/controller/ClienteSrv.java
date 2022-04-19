/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.dao.ClienteDaoJpa;
import model.dao.DaoFactory;
import model.dao.InterfaceDao;

/**
 *
 * @author frizz
 */
public class ClienteSrv extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        try {

            String acao = request.getParameter("acao");
            
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String idade = request.getParameter("idade");
            String credito = request.getParameter("credito");

            InterfaceDao dao = DaoFactory.novoClienteDao();
            Cliente c = null;
            RequestDispatcher rd = null;

            switch (acao) {

                case "inclusao":
                    c = new Cliente(nome, Integer.parseInt(idade), Float.parseFloat(credito));
                    try {
                        dao.incluir(c);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem(""));
                    rd.forward(request, response);
                    break;

                case "pre-edicao":
                    c = (Cliente) dao.pesquisarPorId(Integer.parseInt(id));
                    rd = request.getRequestDispatcher("Formulario.jsp?acao=edicao"
                            + "&id=" + c.getId()
                            + "&nome=" + c.getNome()
                            + "&idade=" + c.getIdade()
                            + "&credito=" + c.getCredito());
                    rd.forward(request, response);
                    break;

                case "edicao":
                    c = new Cliente(nome, Integer.parseInt(idade), Float.parseFloat(credito));
                    c.setId(Integer.parseInt(id));
                    try {
                        dao.editar(c);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem(""));
                    rd.forward(request, response);
                    break;

                case "exclusao":
                    try {
                        c = new Cliente();
                        c.setId(Integer.parseInt(id));
                        dao.excluir(c);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem(""));
                    rd.forward(request, response);
                    break;

                case "listagem":
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem(""));
                    rd.forward(request, response);
                    break;
                    
                case "listagem-busca":
                    String param = request.getParameter("nomeBusca");
                    rd = request.getRequestDispatcher("Listagem.jsp?lista=" + listagem(param));
                    rd.forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteSrv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String listagem(String param) {
        InterfaceDao dao = new ClienteDaoJpa();
        List<Cliente> lista = null;
        try {
            lista = dao.listar(param);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String listaHTML = "";
        for (Cliente cliente : lista) {
            listaHTML = listaHTML
                    + "<div class='card'>"
                    + "<img src='imgs/user.png' alt='Usuario'>"
                    + "<h4 class='nome-card'>Cliente: " + cliente.getNome() + "</h4>"
                    + "<div class='idade-card'>Idade: " + cliente.getIdade() + "</div>"
                    + "<div class='credito-card'>Credito: " + cliente.getCredito() + "</div>"
                    + "<div class='footer'>"
                    + "<div><form action=ClienteSrv?acao=pre-edicao method='POST'>"
                    + "<input type='hidden' name='id' value=" + cliente.getId() + "><input type='submit' value=Editar></form></div>"
                    + "<div><form action=ClienteSrv?acao=exclusao method='POST'>"
                    + "<input type='hidden' name='id' value=" + cliente.getId() + "><input type='submit' value=Excluir></form></div>"
                    + "</div>"
                    + "</div>";
        }
        return listaHTML;
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
