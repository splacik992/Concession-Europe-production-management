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
        <h1 class="h3 mb-0 text-gray-800">Okleiniarka:</h1>
    </div>

    <!-- Content Row -->
    <div class="row">
        <div class="container-fluid sawPanel">

            <!-- Page Heading -->


            <!-- DataTales Example -->
            <%--            <div class="row d-flex justify-content-center d-none">--%>
            <%--                <div class="col-lg-12 d-flex justify-content-center">--%>
            <%--                    <div class="p-3 w-100">--%>
            <%--                        <div class="text-center">--%>
            <%--                            <h1 class="h4 text-gray-900 mb-4">Nowe zamówienie</h1>--%>
            <%--                        </div>--%>
            <%--                        <form:form class="user" method="post" action="/" modelAttribute="order" id="form">--%>
            <%--                            <div class="form-group row">--%>
            <%--                                <div class="col-sm-3">--%>
            <%--                                    <input type="hidden" id="a" name="orderListener">--%>
            <%--                                    <form:input path="materialName" type="text" class="form-control form-control-user"--%>
            <%--                                                id="material"--%>
            <%--                                                placeholder="Materiał"/>--%>
            <%--                                </div>--%>
            <%--                                <div class="col-sm-3">--%>
            <%--                                    <form:input path="productName" type="text" class="form-control form-control-user"--%>
            <%--                                                id="productName"--%>
            <%--                                                placeholder="Nazwa produktu"/>--%>
            <%--                                </div>--%>
            <%--                                <div class="col-sm-3">--%>
            <%--                                    <form:input path="clientName" type="text" class="form-control form-control-user"--%>
            <%--                                                id="clientName"--%>
            <%--                                                placeholder="Klient"/>--%>
            <%--                                </div>--%>
            <%--                                <div class="col-sm-2">--%>
            <%--                                    <form:input path="count" type="number" class="form-control form-control-user"--%>
            <%--                                                id="count" placeholder="Ilość"/>--%>
            <%--                                </div>--%>
            <%--                                <button type="submit" class="btn btn-lg btn-primary shadow-sm">Dodaj</button>--%>

            <%--                            </div>--%>
            <%--                            <section>--%>

            <%--                                <c:forEach items="${processesList}" var="processes" varStatus="counter">--%>
            <%--                                    <c:set var="string2" value="${fn:split(processes, ' ')}"/>--%>
            <%--                                    <c:set var="string3" value="${fn:join(string2, '-')}"/>--%>
            <%--                                    <div class="form-check form-check-inline">--%>
            <%--                                        <label class="form-check-label containerForInputs" for="${processes}"--%>
            <%--                                               id="${string3}">--%>
            <%--                                            <input class="form-check-input" type="checkbox" name="processName"--%>
            <%--                                                   value="${processes}"/>--%>
            <%--                                            <span class="checkmark">--%>
            <%--                                                            <i class="fas fa-check"></i>--%>
            <%--                                                        </span>--%>
            <%--                                                ${processes}--%>
            <%--                                        </label>--%>
            <%--                                    </div>--%>
            <%--                                </c:forEach>--%>
            <%--                            </section>--%>
            <%--                            <p>Kolejność:</p>--%>

            <%--                            <div id="result">--%>

            <%--                            </div>--%>
            <%--                        </form:form>--%>
            <%--                        <hr>--%>

            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </div>--%>
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
                            <c:forEach items="${edgebanderOrdersPerDay}" var="edgebanderOrder">

                                <tr class="tableRowDay">
                                    <td>${edgebanderOrder.id}</td>
                                    <td>${edgebanderOrder.materialName}</td>
                                    <td>${edgebanderOrder.productName}</td>
                                    <td>${edgebanderOrder.clientName}</td>
                                    <td>${edgebanderOrder.count}</td>
                                    <td>${edgebanderOrder.principal}</td>
                                    <td>${edgebanderOrder.date}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${!edgebanderOrder.notes.equals('')}">
                                                ${edgebanderOrder.notes}
                                            </c:when>
                                            <c:otherwise>
                                                BRAK
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <c:set var="string6"
                                           value="${fn:split(edgebanderOrder.processes.get(0).name, ' ')}"/>
                                    <c:set var="string7" value="${fn:join(string6, '-')}1"/>
                                    <td>
                                        <div class="actualState"
                                             id="${string7}">${edgebanderOrder.processes.get(0).name}</div>
                                    </td>
                                    <td class="tableRow">
                                        <div class="d-flex">
                                            <c:forEach items="${edgebanderOrder.processes}" var="process">
                                                <c:set var="string4" value="${fn:split(process.name, ' ')}"/>
                                                <c:set var="string5" value="${fn:join(string4, '-')}1"/>
                                                <span class="processOrder" id="${string5}"></span>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <form action="/edgebanderRemove" method="post">
                                            <input name="id" type="hidden" value="${edgebanderOrder.id}">
                                            <button class="btn btn-sm btn-primary shadow-sm addToDay" type="submit"
                                                    value="${edgebanderOrder.id}">Usuń
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="/edgebanderSelect" method="post">
                                            <input type="hidden" value="${edgebanderOrder.id}">
                                            <select name="nextStep">
                                                <c:set var="orderId" value="${edgebanderOrder.id}"/>
                                                <c:forEach items="${edgebanderOrder.processes}" var="process" begin="1">
                                                    <option value="${process.name}">${process.name}</option>
                                                </c:forEach>
                                                <option value="Zgłoś uwagi">Zgłoś uwagi</option>
                                            </select>

                                            <input name="id" type="hidden" value="${edgebanderOrder.id}">
                                            <button class="btn btn-sm btn-primary shadow-sm goToNextProcessButton"
                                                    type="submit" value="${edgebanderOrder.id}">Prześlij dalej
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
                            <c:forEach items="${edgebanderOrders}" var="edgebanderOrder">

                                <tr class="tableRow">
                                    <td>${edgebanderOrder.id}</td>
                                    <td>${edgebanderOrder.materialName}</td>
                                    <td>${edgebanderOrder.productName}</td>
                                    <td>${edgebanderOrder.clientName}</td>
                                    <td>${edgebanderOrder.count}</td>
                                    <td>${edgebanderOrder.principal}</td>
                                    <td>${edgebanderOrder.date}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${!edgebanderOrder.notes.equals('')}">
                                                ${edgebanderOrder.notes}
                                            </c:when>
                                            <c:otherwise>
                                                BRAK
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <c:set var="string6"
                                           value="${fn:split(edgebanderOrder.processes.get(0).name, ' ')}"/>
                                    <c:set var="string7" value="${fn:join(string6, '-')}1"/>
                                    <td>
                                        <div class="actualState"
                                             id="${string7}">${edgebanderOrder.processes.get(0).name}</div>
                                    </td>
                                    <td class="tableRow">
                                        <div class="d-flex">
                                            <c:forEach items="${edgebanderOrder.processes}" var="process">
                                                <c:set var="string4" value="${fn:split(process.name, ' ')}"/>
                                                <c:set var="string5" value="${fn:join(string4, '-')}1"/>
                                                <span class="processOrder" id="${string5}"></span>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <form action="/edgebanderAdd" method="post">
                                            <input name="id" type="hidden" value="${edgebanderOrder.id}">
                                            <button class="btn btn-sm btn-primary shadow-sm addToDay" type="submit"
                                                    value="${edgebanderOrder.id}">Dodaj
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
              action="/edgebanderSelectPop">
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

