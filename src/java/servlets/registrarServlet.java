/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import org.me.imatge.ImatgeWS_Service;

/**
 *
 * @author oriol
 */
@WebServlet(name = "registrarServlet", urlPatterns = {"/registrarServlet"})
@MultipartConfig
public class registrarServlet extends HttpServlet {
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/SOAPFileUPload/ImatgeWS.wsdl")
    private ImatgeWS_Service service;

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
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                org.me.imatge.ImatgeWS_Service service = new org.me.imatge.ImatgeWS_Service();
                org.me.imatge.ImatgeWS port = service.getImatgeWSPort();
                 // TODO initialize WS operation arguments here
                Part file = request.getPart("file");
                Image image = ImageIO.read(file.getInputStream());
                String  rtitol = request.getParameter("titol");
                String  rdescripcio = request.getParameter("textArea");
                String  rautor = request.getParameter("autor");
                String  rkeywords = request.getParameter("clau");
                String  rdata = request.getParameter("creacio");
                Random rand = new Random();
                 int  id = rand.nextInt(1000) + 1;
                 
                org.me.imatge.Imatge imatge = new org.me.imatge.Imatge();
                // TODO process result here
                
                imatge.setId(id);
                imatge.setTitol(rtitol);
                imatge.setDescripcio(rdescripcio);
                imatge.setAutor(rautor);
                imatge.setKeywords(rkeywords);
                imatge.setDataCreacio(rdata);
                
                int result = registreImatge(imatge,image);
                
                if (result == 1) {
                    out.println("<h1>Usuari registrat amb èxit</h1>");
                } else {
                    out.println("<h1>Error al registrar l'usuari</h1>");
                }
                
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    private int registreImatge(org.me.imatge.Imatge imatge, java.awt.Image foto) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.imatge.ImatgeWS port = service.getImatgeWSPort();
        return port.registreImatge(imatge, foto);
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
