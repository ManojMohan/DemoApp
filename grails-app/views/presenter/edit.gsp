

<%@ page import="com.ig.demoApp.Presenter" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'presenter.label', default: 'Presenter')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${presenterInstance}">
            <div class="errors">
                <g:renderErrors bean="${presenterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${presenterInstance?.id}" />
                <g:hiddenField name="version" value="${presenterInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="age"><g:message code="presenter.age.label" default="Age" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: presenterInstance, field: 'age', 'errors')}">
                                    <g:textField name="age" value="${fieldValue(bean: presenterInstance, field: 'age')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="bootCampSessions"><g:message code="presenter.bootCampSessions.label" default="Boot Camp Sessions" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: presenterInstance, field: 'bootCampSessions', 'errors')}">
                                    
<ul>
<g:each in="${presenterInstance?.bootCampSessions?}" var="b">
    <li><g:link controller="bootCampSession" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="bootCampSession" action="create" params="['presenter.id': presenterInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'bootCampSession.label', default: 'BootCampSession')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="email"><g:message code="presenter.email.label" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: presenterInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${presenterInstance?.email}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="presenter.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: presenterInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${presenterInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="password"><g:message code="presenter.password.label" default="Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: presenterInstance, field: 'password', 'errors')}">
                                    <g:textField name="password" value="${presenterInstance?.password}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
