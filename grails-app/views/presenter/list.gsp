
<%@ page import="com.ig.demoApp.Presenter" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'presenter.label', default: 'Presenter')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'presenter.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="age" title="${message(code: 'presenter.age.label', default: 'Age')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'presenter.email.label', default: 'Email')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'presenter.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="password" title="${message(code: 'presenter.password.label', default: 'Password')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${presenterInstanceList}" status="i" var="presenterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${presenterInstance.id}">${fieldValue(bean: presenterInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: presenterInstance, field: "age")}</td>
                        
                            <td>${fieldValue(bean: presenterInstance, field: "email")}</td>
                        
                            <td>${fieldValue(bean: presenterInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: presenterInstance, field: "password")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${presenterInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
