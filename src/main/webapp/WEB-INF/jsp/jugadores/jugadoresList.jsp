<petclinic:layout pageName="owners">
    <h2>Jugadores</h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre de Usuario</th>
            <th style="width: 200px;">email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="jugador">
            <tr>
                <td>
                    <spring:url value="/jugador/{jugadorId}" var="jugadorUrl">
                        <spring:param name="jugadorId" value="${juagdor.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(jugadorUrl)}"><c:out value="${jugador.nombredeUsuario} ${jugador.email}"/></a>
                </td>
                
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>