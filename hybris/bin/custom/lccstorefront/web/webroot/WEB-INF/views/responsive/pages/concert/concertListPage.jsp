<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<template:page pageTitle="${pageTitle}">


    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <h1 class="page-header">Concerts</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <c:choose>
                    <c:when test="${empty concerts}">
                        <p>No concerts found.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Code</th>
                                    <th>Venue</th>
                                    <th>Date</th>
                                    <th>Type</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${concerts}" var="concert">
                                    <tr>
                                        <td><c:out value="${concert.code}"/></td>
                                        <td><c:out value="${concert.venue}"/></td>
                                        <td><fmt:formatDate value="${concert.date}" pattern="dd MMM yyyy"/></td>
                                        <td><c:out value="${concert.concertType}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

</template:page>
