<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<!-- End of Topbar -->

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4 container-fluid">
        <h1 class="h3 mb-0 text-gray-800">Dział drzewny:</h1>
    </div>

    <!-- Content Row -->
    <div class="row">
        <div class="container-fluid sawPanel">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Plan na dziś:</h6>
                </div>

                <div class="card-body">
                    <div class="table-responsive">

                        <table class="table table-bordered" id="dataTable2" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Numer</th>
                                <th>Materiał</th>
                                <th>Nazwa produktu</th>
                                <th>Klient</th>
                                <th>Ilość</th>
                                <th>Zleceniodawca</th>
                                <th>Data</th>
                                <th>Uwagi</th>
                                <th>Aktualny etap</th>
                                <th>Kolejka</th>
                                <th>Usuń z planu</th>
                                <th>Kolejny proces</th>
                            </tr>
                            </thead>
                            <tbody id="sawDay">
                            <c:forEach items="${woodyOrdersPerDay}" var="woodyOrder">

                                <tr class="tableRowDay perDayTable2">
                                    <td>${woodyOrder.id}</td>
                                    <td>${woodyOrder.materialName}</td>
                                    <td>${woodyOrder.productName}</td>
                                    <td>${woodyOrder.clientName}</td>
                                    <td>${woodyOrder.count}</td>
                                    <td>${woodyOrder.principal}</td>
                                    <td>${woodyOrder.date}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${!woodyOrder.notes.equals('')}">
                                                ${woodyOrder.notes}
                                            </c:when>
                                            <c:otherwise>
                                                BRAK
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <c:set var="string6"
                                           value="${fn:split(woodyOrder.processes.get(0).name, ' ')}"/>
                                    <c:set var="string7" value="${fn:join(string6, '-')}1"/>
                                    <td>
                                        <div class="actualState"
                                             id="${string7}">${woodyOrder.processes.get(0).name}</div>
                                    </td>
                                    <td class="tableRow">
                                        <div class="d-flex">
                                            <c:forEach items="${woodyOrder.processes}" var="process">
                                                <c:set var="string4" value="${fn:split(process.name, ' ')}"/>
                                                <c:set var="string5" value="${fn:join(string4, '-')}1"/>
                                                <span class="processOrder" id="${string5}"></span>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <form action="/woodyRemove" method="post">
                                            <input name="id" type="hidden" value="${woodyOrder.id}">
                                            <button class="btn btn-sm btn-primary shadow-sm addToDay" type="submit"
                                                    value="${woodyOrder.id}">Usuń
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="/woodySelect" method="post">
                                            <input type="hidden" value="${woodyOrder.id}">
                                            <select name="nextStep">
                                                <c:set var="orderId" value="${woodyOrder.id}"/>
                                                <c:if test="${woodyOrder.processes.size() == 1}">
                                                    <option value="End">Zakończ</option>
                                                </c:if>
                                                <c:forEach items="${woodyOrder.processes}" var="process" begin="1">
                                                    <option value="${process.name}">${process.name}</option>
                                                </c:forEach>
                                                <option value="Zgłoś uwagi">Zgłoś uwagi</option>
                                            </select>

                                            <input name="id" type="hidden" value="${woodyOrder.id}">
                                            <button class="btn btn-sm btn-primary shadow-sm goToNextProcessButton"
                                                    type="submit" value="${woodyOrder.id}">Prześlij dalej
                                            </button>
                                        </form>

                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>


            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Zamówienia</h6>
                </div>

                <div class="card-body">
                    <div class="table-responsive">

                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Numer</th>
                                <th>Materiał</th>
                                <th>Nazwa produktu</th>
                                <th>Klient</th>
                                <th>Ilość</th>
                                <th>Zleceniodawca</th>
                                <th>Data</th>
                                <th>Uwagi</th>
                                <th>Aktualny etap</th>
                                <th>Kolejka</th>
                                <th>Dodaj na dziś</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${woodyOrdersDesc}" var="woodyOrder">

                                <tr class="tableRow perDayTable">
                                    <td>${woodyOrder.id}</td>
                                    <td>${woodyOrder.materialName}</td>
                                    <td>${woodyOrder.productName}</td>
                                    <td>${woodyOrder.clientName}</td>
                                    <td>${woodyOrder.count}</td>
                                    <td>${woodyOrder.principal}</td>
                                    <td>${woodyOrder.date}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${!woodyOrder.notes.equals('')}">
                                                ${woodyOrder.notes}
                                            </c:when>
                                            <c:otherwise>
                                                BRAK
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <c:set var="string6"
                                           value="${fn:split(woodyOrder.processes.get(0).name, ' ')}"/>
                                    <c:set var="string7" value="${fn:join(string6, '-')}1"/>
                                    <td>
                                        <div class="actualState"
                                             id="${string7}">${woodyOrder.processes.get(0).name}</div>
                                    </td>
                                    <td class="tableRow">
                                        <div class="d-flex">
                                            <c:forEach items="${woodyOrder.processes}" var="process">
                                                <c:set var="string4" value="${fn:split(process.name, ' ')}"/>
                                                <c:set var="string5" value="${fn:join(string4, '-')}1"/>
                                                <span class="processOrder" id="${string5}"></span>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <form action="/woodyAdd" method="post" class="formAddToDay">
                                            <input name="id" type="hidden" value="${woodyOrder.id}">
                                            <button class="btn btn-sm btn-primary shadow-sm addToDay" type="submit"
                                                    value="${woodyOrder.id}">Dodaj
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container-fluid -->

    </div>

</div>
<div class="popDivToHide d-none">
    <div class="p-5 popDiv d-flex flex-column align-items-center">
        <div class="text-center">
            <h1 class="h4 text-gray-900 mb-4">Uwagi do zamówienia:</h1>
        </div>
        <form class="user d-flex flex-column align-items-center w-50 popForm" method="post"
              action="/woodySelectPop">
            <div class="form-group row w-100">
                    <textarea type="text" class="w-100 textarea"
                              id="material"
                              placeholder="Uwagi" name="comments"></textarea>
            </div>
            <input name="id" type="hidden" value="${orderId}">
            <jsp:text>Wyślij zamówienie na:</jsp:text>
            <select name="nextStep" class="mb-3">
                <c:forEach items="${processesList}" var="process">
                    <option value="${process}">${process}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-lg btn-primary shadow-sm uwagiBtn">Prześlij</button>
        </form>

        <i class="fas fa-times closeButton"></i>
    </div>
    <div class="divAbovePopDiv">
    </div>
</div>
<!-- End of Main Content -->
<jsp:include page="footer.jsp"/>

