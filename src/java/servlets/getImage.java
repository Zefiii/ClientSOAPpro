package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.me.imatge.ImatgeWS_Service;

/**
 *
 * @author oriol
 */
@WebServlet(urlPatterns = {"/getImage"})
public class getImage extends HttpServlet {

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
        String title = request.getParameter("title");
        
        System.out.println("Aquest es el titol que arriba" + title);
        //try (PrintWriter out = response.getWriter()) {
            BufferedImage bi = null;
            Image im = getFoto(title);
            if(im instanceof BufferedImage){
                bi = (BufferedImage) im;
            }
            else bi = new BufferedImage(im.getHeight(null), im.getWidth(null), BufferedImage.TYPE_INT_RGB);
            response.setContentType("img/jpeg");
            OutputStream os = response.getOutputStream();
            ImageIO.write(bi, "jpeg", os);
           /* WritableRaster raster = bi.getRaster();
            DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

            os.write(data.getData());*/
       // }
        /*catch(IOException e){
            System.out.println(e);
        }*/
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
        System.out.println(request.getParameter("title"));
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

    private Image getFoto(java.lang.String title) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.imatge.ImatgeWS port = service.getImatgeWSPort();
        System.out.println("Cridem amb " + title);
        return port.getFoto(title);
    }



 
}
