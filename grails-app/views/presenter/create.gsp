

<%@ page import="com.ig.demoApp.Presenter" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'presenter.label', default: 'Presenter')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${presenterInstance}">
            <div class="errors">
                <g:renderErrors bean="${presenterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
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
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
