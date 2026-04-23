<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:htmlEscape defaultHtmlEscape="true" />

<template:page pageTitle="List of bands">

    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <h1 class="page-header">Bands</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <c:choose>
                    <c:when test="${empty bands}">
                        <p>No bands found.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Code</th>
                                    <th>Name</th>
                                    <th>Album Sales</th>
                                    <th>Genres</th>
                                    <th>History</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${bands}" var="band">
                                    <tr>
                                        <td><c:out value="${band.code}"/></td>
                                        <td><c:out value="${band.name}"/></td>
                                        <td><c:out value="${band.albumSales}"/></td>
                                        <td>
                                            <c:forEach items="${band.genres}" var="genre" varStatus="status">
                                                <c:out value="${genre}"/><c:if test="${!status.last}">, </c:if>
                                            </c:forEach>
                                        </td>
                                        <td><c:out value="${band.history}"/></td>
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
