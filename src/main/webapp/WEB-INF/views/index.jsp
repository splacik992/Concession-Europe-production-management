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
        <h1 class="h3 mb-0 text-gray-800">Panel główny</h1>

    </div>
    <!-- Content Row -->
    <div class="row">
        <div class="container-fluid">

            <!-- Page Heading -->


            <!-- DataTales Example -->
            <div class="row d-flex justify-content-center d-none">
                <div class="col-lg-12 d-flex justify-content-center">
                    <div class="p-3 w-100">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Nowe zamówienie</h1>
                        </div>
                        <form:form name="form" class="user" method="post" action="/" modelAttribute="order" id="form">
                            <div class="form-group row">
                                <div class="col-sm-3">
                                    <input type="hidden" id="a" name="orderListener">
                                    <form:input path="materialName" type="text" class="form-control form-control-user"
                                                id="material"
                                                placeholder="Materiał" name="material"/>
                                </div>
                                <div class="col-sm-3">
                                    <form:input path="productName" type="text" class="form-control form-control-user"
                                                id="productName"
                                                placeholder="Nazwa produktu" name="product"/>
                                </div>
                                <div class="col-sm-2">
                                    <form:input path="clientName" type="text" class="form-control form-control-user"
                                                id="clientName"
                                                placeholder="Klient" name="client"/>
                                </div>
                                <div class="col-sm-1">
                                    <form:input path="count" type="number" class="form-control form-control-user"
                                                id="count" placeholder="Ilość" name="count" min="1" value="1"/>
                                </div>
                                <div class="col-sm-2">
                                    <form:input path="principal" type="text" class="form-control form-control-user"
                                                id="principal"
                                                placeholder="Zleceniodawca" name="principal"/>
                                </div>
                                <button type="submit" id="addOrderButton" class="btn btn-lg btn-primary shadow-sm">
                                    Dodaj
                                </button>

                            </div>
                            <section>

                                <c:forEach items="${processesList}" var="processes" varStatus="counter">
                                    <c:set var="string2" value="${fn:split(processes, ' ')}"/>
                                    <c:set var="string3" value="${fn:join(string2, '-')}"/>
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label containerForInputs" for="${processes}"
                                               id="${string3}">
                                            <input class="form-check-input" type="checkbox" name="processName"
                                                   value="${processes}"/>
                                            <span class="checkmark">
                                                            <i class="fas fa-check"></i>
                                                        </span>
                                                ${processes}
                                        </label>
                                    </div>
                                </c:forEach>
                            </section>
                            <p>Kolejność:</p>

                            <div id="result">

                            </div>
                        </form:form>
                        <hr>

                    </div>
                </div>
            </div>
            <div class="text-center">
                <h5 class="text-gray-900 mb-4">Wyszukiwarka</h5>
            </div>
            <div class="input-group">
                <input type="text" class="form-control" aria-label="Text input with dropdown button"
                       placeholder="Wyszukaj zamówienie po..." id="searchBar">
                <div class="input-group-append">
                    <select id="searchSelect" class="dropdown">

                        <option class="selectOption" value="number">numer zamówienia</option>
                        <option class="selectOption" value="material">materiał</option>
                        <option class="selectOption" value="name">nazwa produktu</option>
                        <option class="selectOption" value="client">klient</option>
                        <option class="selectOption" value="principal">zleceniodawca</option>
                        <option class="selectOption" value="date">data</option>
                        <option class="selectOption" value="status">status zamówienia</option>

                    </select>
                </div>
            </div>
            <hr>
            <div class="card shadow mb-4">
                <div class="card-header py-3 row d-flex justify-content-between">
                    <h4 class="m-0 font-weight-bold text-primary">Zamówienia</h4>
                    <a href="/order/export/excel" class="btn btn-sm btn-primary shadow-sm">Zapisz na dysk</a>
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
                                <th>Status zamówienia</th>
                                <th>Aktualny etap</th>
                                <th>Kolejka</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orders}" var="order">
                                <tr class="tableRow rowOfData">
                                    <td>${order.id}</td>
                                    <td>${order.materialName}</td>
                                    <td>${order.productName}</td>
                                    <td>${order.clientName}</td>
                                    <td>${order.count}</td>
                                    <td>${order.principal}</td>
                                    <td>${order.date}</td>

                                    <td>${order.orderStatus.name}</td>
                                    <c:if test="${order.processes.size() != 0}">
                                        <c:set var="string6" value="${fn:split(order.processes.get(0).name, ' ')}"/>
                                        <c:set var="string7" value="${fn:join(string6, '-')}1"/>
                                    </c:if>
                                    <td>
                                        <c:if test="${order.processes.size() != 0}">
                                            <div class="actualState"
                                                 id="${string7}">${order.processes.get(0).name}</div>
                                        </c:if>
                                    </td>
                                    <td class="tableRow">
                                        <div class="d-flex">
                                            <c:forEach items="${order.processes}" var="process">
                                                <c:set var="string4" value="${fn:split(process.name, ' ')}"/>
                                                <c:set var="string5" value="${fn:join(string4, '-')}1"/>
                                                <span class="processOrder" id="${string5}"></span>
                                            </c:forEach>
                                        </div>
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

    <!-- Earnings (Monthly) Card Example -->
    <!--                        <div class="col-xl-3 col-md-6 mb-4">-->
    <!--                            <div class="card border-left-primary shadow h-100 py-2">-->
    <!--                                <div class="card-body">-->
    <!--                                    <div class="row no-gutters align-items-center">-->
    <!--                                        <div class="col mr-2">-->
    <!--                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">-->
    <!--                                                Earnings (Monthly)</div>-->
    <!--                                            <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>-->
    <!--                                        </div>-->
    <!--                                        <div class="col-auto">-->
    <!--                                            <i class="fas fa-calendar fa-2x text-gray-300"></i>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                            </div>-->
    <!--                        </div>-->

    <!--                        &lt;!&ndash; Earnings (Monthly) Card Example &ndash;&gt;-->
    <!--                        <div class="col-xl-3 col-md-6 mb-4">-->
    <!--                            <div class="card border-left-success shadow h-100 py-2">-->
    <!--                                <div class="card-body">-->
    <!--                                    <div class="row no-gutters align-items-center">-->
    <!--                                        <div class="col mr-2">-->
    <!--                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">-->
    <!--                                                Earnings (Annual)</div>-->
    <!--                                            <div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>-->
    <!--                                        </div>-->
    <!--                                        <div class="col-auto">-->
    <!--                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                            </div>-->
    <!--                        </div>-->

    <!--                        &lt;!&ndash; Earnings (Monthly) Card Example &ndash;&gt;-->
    <!--                        <div class="col-xl-3 col-md-6 mb-4">-->
    <!--                            <div class="card border-left-info shadow h-100 py-2">-->
    <!--                                <div class="card-body">-->
    <!--                                    <div class="row no-gutters align-items-center">-->
    <!--                                        <div class="col mr-2">-->
    <!--                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tasks-->
    <!--                                            </div>-->
    <!--                                            <div class="row no-gutters align-items-center">-->
    <!--                                                <div class="col-auto">-->
    <!--                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>-->
    <!--                                                </div>-->
    <!--                                                <div class="col">-->
    <!--                                                    <div class="progress progress-sm mr-2">-->
    <!--                                                        <div class="progress-bar bg-info" role="progressbar"-->
    <!--                                                            style="width: 50%" aria-valuenow="50" aria-valuemin="0"-->
    <!--                                                            aria-valuemax="100"></div>-->
    <!--                                                    </div>-->
    <!--                                                </div>-->
    <!--                                            </div>-->
    <!--                                        </div>-->
    <!--                                        <div class="col-auto">-->
    <!--                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                            </div>-->
    <!--                        </div>-->

    <!--                        &lt;!&ndash; Pending Requests Card Example &ndash;&gt;-->
    <!--                        <div class="col-xl-3 col-md-6 mb-4">-->
    <!--                            <div class="card border-left-warning shadow h-100 py-2">-->
    <!--                                <div class="card-body">-->
    <!--                                    <div class="row no-gutters align-items-center">-->
    <!--                                        <div class="col mr-2">-->
    <!--                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">-->
    <!--                                                Pending Requests</div>-->
    <!--                                            <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>-->
    <!--                                        </div>-->
    <!--                                        <div class="col-auto">-->
    <!--                                            <i class="fas fa-comments fa-2x text-gray-300"></i>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                            </div>-->
    <!--                        </div>-->
    <!--                    </div>-->

    <!-- Content Row -->

    <!--                    <div class="row">-->

    <!--                        &lt;!&ndash; Area Chart &ndash;&gt;-->
    <!--                        <div class="col-xl-8 col-lg-7">-->
    <!--                            <div class="card shadow mb-4">-->
    <!--                                &lt;!&ndash; Card Header - Dropdown &ndash;&gt;-->
    <!--                                <div-->
    <!--                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">-->
    <!--                                    <h6 class="m-0 font-weight-bold text-primary">Earnings Overview</h6>-->
    <!--                                    <div class="dropdown no-arrow">-->
    <!--                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"-->
    <!--                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">-->
    <!--                                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>-->
    <!--                                        </a>-->
    <!--                                        <div class="dropdown-menu dropdown-menu-right shadow animated&#45;&#45;fade-in"-->
    <!--                                            aria-labelledby="dropdownMenuLink">-->
    <!--                                            <div class="dropdown-header">Dropdown Header:</div>-->
    <!--                                            <a class="dropdown-item" href="#">Action</a>-->
    <!--                                            <a class="dropdown-item" href="#">Another action</a>-->
    <!--                                            <div class="dropdown-divider"></div>-->
    <!--                                            <a class="dropdown-item" href="#">Something else here</a>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                                &lt;!&ndash; Card Body &ndash;&gt;-->
    <!--                                <div class="card-body">-->
    <!--                                    <div class="chart-area">-->
    <!--                                        <canvas id="myAreaChart"></canvas>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                            </div>-->
    <!--                        </div>-->

    <!--                        &lt;!&ndash; Pie Chart &ndash;&gt;-->
    <!--                        <div class="col-xl-4 col-lg-5">-->
    <!--                            <div class="card shadow mb-4">-->
    <!--                                &lt;!&ndash; Card Header - Dropdown &ndash;&gt;-->
    <!--                                <div-->
    <!--                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">-->
    <!--                                    <h6 class="m-0 font-weight-bold text-primary">Revenue Sources</h6>-->
    <!--                                    <div class="dropdown no-arrow">-->
    <!--                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"-->
    <!--                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">-->
    <!--                                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>-->
    <!--                                        </a>-->
    <!--                                        <div class="dropdown-menu dropdown-menu-right shadow animated&#45;&#45;fade-in"-->
    <!--                                            aria-labelledby="dropdownMenuLink">-->
    <!--                                            <div class="dropdown-header">Dropdown Header:</div>-->
    <!--                                            <a class="dropdown-item" href="#">Action</a>-->
    <!--                                            <a class="dropdown-item" href="#">Another action</a>-->
    <!--                                            <div class="dropdown-divider"></div>-->
    <!--                                            <a class="dropdown-item" href="#">Something else here</a>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                                &lt;!&ndash; Card Body &ndash;&gt;-->
    <!--                                <div class="card-body">-->
    <!--                                    <div class="chart-pie pt-4 pb-2">-->
    <!--                                        <canvas id="myPieChart"></canvas>-->
    <!--                                    </div>-->
    <!--                                    <div class="mt-4 text-center small">-->
    <!--                                        <span class="mr-2">-->
    <!--                                            <i class="fas fa-circle text-primary"></i> Direct-->
    <!--                                        </span>-->
    <!--                                        <span class="mr-2">-->
    <!--                                            <i class="fas fa-circle text-success"></i> Social-->
    <!--                                        </span>-->
    <!--                                        <span class="mr-2">-->
    <!--                                            <i class="fas fa-circle text-info"></i> Referral-->
    <!--                                        </span>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                            </div>-->
    <!--                        </div>-->
    <!--                    </div>-->

    <!--                    &lt;!&ndash; Content Row &ndash;&gt;-->
    <!--                    <div class="row">-->

    <!--                        &lt;!&ndash; Content Column &ndash;&gt;-->
    <!--                        <div class="col-lg-6 mb-4">-->

    <!--                            &lt;!&ndash; Project Card Example &ndash;&gt;-->
    <!--                            <div class="card shadow mb-4">-->
    <!--                                <div class="card-header py-3">-->
    <!--                                    <h6 class="m-0 font-weight-bold text-primary">Projects</h6>-->
    <!--                                </div>-->
    <!--                                <div class="card-body">-->
    <!--                                    <h4 class="small font-weight-bold">Server Migration <span-->
    <!--                                            class="float-right">20%</span></h4>-->
    <!--                                    <div class="progress mb-4">-->
    <!--                                        <div class="progress-bar bg-danger" role="progressbar" style="width: 20%"-->
    <!--                                            aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>-->
    <!--                                    </div>-->
    <!--                                    <h4 class="small font-weight-bold">Sales Tracking <span-->
    <!--                                            class="float-right">40%</span></h4>-->
    <!--                                    <div class="progress mb-4">-->
    <!--                                        <div class="progress-bar bg-warning" role="progressbar" style="width: 40%"-->
    <!--                                            aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>-->
    <!--                                    </div>-->
    <!--                                    <h4 class="small font-weight-bold">Customer Database <span-->
    <!--                                            class="float-right">60%</span></h4>-->
    <!--                                    <div class="progress mb-4">-->
    <!--                                        <div class="progress-bar" role="progressbar" style="width: 60%"-->
    <!--                                            aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>-->
    <!--                                    </div>-->
    <!--                                    <h4 class="small font-weight-bold">Payout Details <span-->
    <!--                                            class="float-right">80%</span></h4>-->
    <!--                                    <div class="progress mb-4">-->
    <!--                                        <div class="progress-bar bg-info" role="progressbar" style="width: 80%"-->
    <!--                                            aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>-->
    <!--                                    </div>-->
    <!--                                    <h4 class="small font-weight-bold">Account Setup <span-->
    <!--                                            class="float-right">Complete!</span></h4>-->
    <!--                                    <div class="progress">-->
    <!--                                        <div class="progress-bar bg-success" role="progressbar" style="width: 100%"-->
    <!--                                            aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                            </div>-->

    <!--                            &lt;!&ndash; Color System &ndash;&gt;-->
    <!--                            <div class="row">-->
    <!--                                <div class="col-lg-6 mb-4">-->
    <!--                                    <div class="card bg-primary text-white shadow">-->
    <!--                                        <div class="card-body">-->
    <!--                                            Primary-->
    <!--                                            <div class="text-white-50 small">#4e73df</div>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                                <div class="col-lg-6 mb-4">-->
    <!--                                    <div class="card bg-success text-white shadow">-->
    <!--                                        <div class="card-body">-->
    <!--                                            Success-->
    <!--                                            <div class="text-white-50 small">#1cc88a</div>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                                <div class="col-lg-6 mb-4">-->
    <!--                                    <div class="card bg-info text-white shadow">-->
    <!--                                        <div class="card-body">-->
    <!--                                            Info-->
    <!--                                            <div class="text-white-50 small">#36b9cc</div>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                                <div class="col-lg-6 mb-4">-->
    <!--                                    <div class="card bg-warning text-white shadow">-->
    <!--                                        <div class="card-body">-->
    <!--                                            Warning-->
    <!--                                            <div class="text-white-50 small">#f6c23e</div>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                                <div class="col-lg-6 mb-4">-->
    <!--                                    <div class="card bg-danger text-white shadow">-->
    <!--                                        <div class="card-body">-->
    <!--                                            Danger-->
    <!--                                            <div class="text-white-50 small">#e74a3b</div>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                                <div class="col-lg-6 mb-4">-->
    <!--                                    <div class="card bg-secondary text-white shadow">-->
    <!--                                        <div class="card-body">-->
    <!--                                            Secondary-->
    <!--                                            <div class="text-white-50 small">#858796</div>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                                <div class="col-lg-6 mb-4">-->
    <!--                                    <div class="card bg-light text-black shadow">-->
    <!--                                        <div class="card-body">-->
    <!--                                            Light-->
    <!--                                            <div class="text-black-50 small">#f8f9fc</div>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                                <div class="col-lg-6 mb-4">-->
    <!--                                    <div class="card bg-dark text-white shadow">-->
    <!--                                        <div class="card-body">-->
    <!--                                            Dark-->
    <!--                                            <div class="text-white-50 small">#5a5c69</div>-->
    <!--                                        </div>-->
    <!--                                    </div>-->
    <!--                                </div>-->
    <!--                            </div>-->

    <!--                        </div>-->

    <!--                        <div class="col-lg-6 mb-4">-->

    <!--                            &lt;!&ndash; Illustrations &ndash;&gt;-->
    <!--                            <div class="card shadow mb-4">-->
    <!--                                <div class="card-header py-3">-->
    <!--                                    <h6 class="m-0 font-weight-bold text-primary">Illustrations</h6>-->
    <!--                                </div>-->
    <!--                                <div class="card-body">-->
    <!--                                    <div class="text-center">-->
    <!--                                        <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"-->
    <!--                                             src="../../theme/img/undraw_posting_photo.svg" alt="">-->
    <!--                                    </div>-->
    <!--                                    <p>Add some quality, svg illustrations to your project courtesy of <a-->
    <!--                                            target="_blank" rel="nofollow" href="https://undraw.co/">unDraw</a>, a-->
    <!--                                        constantly updated collection of beautiful svg images that you can use-->
    <!--                                        completely free and without attribution!</p>-->
    <!--                                    <a target="_blank" rel="nofollow" href="https://undraw.co/">Browse Illustrations on-->
    <!--                                        unDraw &rarr;</a>-->
    <!--                                </div>-->
    <!--                            </div>-->

    <!--                            &lt;!&ndash; Approach &ndash;&gt;-->
    <!--                            <div class="card shadow mb-4">-->
    <!--                                <div class="card-header py-3">-->
    <!--                                    <h6 class="m-0 font-weight-bold text-primary">Development Approach</h6>-->
    <!--                                </div>-->
    <!--                                <div class="card-body">-->
    <!--                                    <p>SB Admin 2 makes extensive use of Bootstrap 4 utility classes in order to reduce-->
    <!--                                        CSS bloat and poor page performance. Custom CSS classes are used to create-->
    <!--                                        custom components and custom utility classes.</p>-->
    <!--                                    <p class="mb-0">Before working with this theme, you should become familiar with the-->
    <!--                                        Bootstrap framework, especially the utility classes.</p>-->
    <!--                                </div>-->
    <!--                            </div>-->

    <!--                        </div>-->
    <!--                    </div>-->

    <!--                </div>-->
    <!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<jsp:include page="footer.jsp"/>

