<%-- 
    Document   : llistarImatges
    Created on : 25-nov-2018, 20:46:53
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
        <h1>Llista imatges</h1>
            <%-- start web service invocation --%><hr/>
    <%
        
    try {
	org.me.imatge.ImatgeWS_Service service = new org.me.imatge.ImatgeWS_Service();
	org.me.imatge.ImatgeWS port = service.getImatgeWSPort();
	// TODO process result here
	java.util.List<org.me.imatge.Imatge> result = port.listImatges();
	 // TODO initialize WS operation arguments here
        
        
        
        for (int i = 0; i < result.size(); i++) {
            org.me.imatge.Imatge imatge = (org.me.imatge.Imatge) result.get(i);
            String titol = imatge.getTitol();
            int id_imatge = imatge.getId();
            String descripcio = imatge.getDescripcio();
            String clau = imatge.getKeywords();
            String autor = imatge.getAutor();
            String creacio = imatge.getDataCreacio();
            
            
            java.lang.String title = titol;
	// TODO process result here
            //java.awt.Image foto = port.getFoto(title);
            
            System.out.println("Aquest es el titol" + title);
            out.println("<h2>" + titol +"</h2>");
            out.println("<p>Descripció: " + descripcio +"</p>");
            out.println("<p>Autor: " + autor +"</p>");
            out.println("<p>Data de creació: " + creacio +"</p>");
            out.println("<img src=\"/ImatgeWSClientPro/getImage?title="+title+"\">");
            out.println("<form action=\"modificarImagen.jsp\" method=\"post\">"
                                + "<input type=\"hidden\" value=\"" + id_imatge + "\" name=\"id_imatge\" id=\"id_imatge\">"
                                + "<input type=\"submit\" value=\"Modificar\">"
                                        + "</form>");
        }
        
        
    } catch (Exception ex) {
	System.out.println(ex);
    }
    %>
    <%-- end web service invocation --%><hr/>

        
    </body>
</html>
