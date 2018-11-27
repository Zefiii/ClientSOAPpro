<%-- 
    Document   : buscarImatges
    Created on : 25-nov-2018, 20:49:51
    Author     : oriol
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Modificar imatge</h1>
        
            <div>
            
            
            <%
                String id_modificar = request.getParameter("id_imatge");
                String titol;
                String descripcio;
                String clau;
                String autor;
                
                out.println("<form method=\"post\" action=\"modificarImatge\" id=\"modificarImatge\">");
                out.println("<label for=\"titol_mod\">Títol: </label>");
                out.println("<input type=\"text\" name=\"titol_mod\" id=\"titol_mod\"><br>");
                
                out.println("<label for=\"descripcio_mod\">Descripció: </label><br>");
                out.println("<textarea cols='30' rows='10' name=\"descripcio_mod\" id=\"descripcio_mod\"><br>");
                
                out.println("<label for=\"autor_mod\">Autor: </label>");
                out.println("<input type=\"text\" name=\"autor_mod\" id=\"autor_mod\"><br>");
                
                out.println("<label for=\"clau_mod\">Paraules Clau: </label>");
                
                out.println("<input type=\"text\" name=\"clau_mod\" id=\"clau_mod\" value=\"<br>"
                        + "<input type=\"hidden\" value=\"" + id_modificar + "\" name=\"id_imatge\" id=\"id_imatge\">");
                
                out.println("<input type=\"submit\" id=\"modificar\" value=\"Modificar\">");
                out.println("</form>");
            %>
            
            </div>
            <div id="modificarImatge">

            </div>
          
                <%-- start web service invocation --%><hr/>
    <%
    try {
	org.me.imatge.ImatgeWS_Service service = new org.me.imatge.ImatgeWS_Service();
	org.me.imatge.ImatgeWS port = service.getImatgeWSPort();
	 // TODO initialize WS operation arguments here
	int id_imatge = 4;
	// TODO process result here
	int result = port.modifyImatge(id_imatge);
        
        if (result == 1) {
            out.println("Modificat amb èxit");
        } else {
            out.println("Error al modificat");
        }
        
    } catch (Exception ex) {
        out.println("Error al modificat");
    }
    %>
    <%-- end web service invocation --%><hr/>

         
    </body>
</html>

