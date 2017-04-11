<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet">
    <script src="../../js/drugFormValidator.js"></script>
    <title>add drug</title>
</head>
<body>
<header>
    <div class="header-topline">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="logOut">
                        <a href="controller?command=log_out">выйти</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="greeting">
        <div class="row">
            <div class="col-md-12">
                <h1>${user.surname} ${user.name} ${user.patronymic}</h1>
                <hr>
            </div>
        </div>
    </div>
</header>
<div class="center">
    <div class="row">
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-3">
            <nav class="service-list">
                <form action="controller" method="get">
                    <input type="hidden" name="command"
                           value="pharmacist_show_order_list">
                    <button type="submit">Список заказов</button>
                </form>
                <form action="pharmacistGroupsToUpdate">
                    <button type="submit">Список препаратов</button>
                </form>
                <form action="pharmacistAddDrug">
                    <button type="submit">Добавить препарат</button>
                </form>
                <form>
                    <button type="submit">Отчет по продажам</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9">
            <section class="drug-form">
                <h3>Заполните форму</h3>
                <p><br></p>
                <form action="controller" method="post" onsubmit="return validate()">
                    <input type="hidden" name="command" value="add_new_drug"/>
                    <div class="form-group">
                        <label for="name">название:</label>
                        <p id="nameErr"><c:out value="${requestScope.errorMap['invalidDrugName']}"/></p>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="group">фармакологическая группа:</label>
                        <select class="form-control" id="group" name="group">
                            <option>antibiotics</option>
                            <option>antipyretics</option>
                            <option>antiviral</option>
                            <option>allergy</option>
                            <option>antiseptics</option>
                            <option>cardiovascular</option>
                            <option>immunity</option>
                            <option>gastrointestinal</option>
                            <option>otolaryngology</option>
                            <option>nervous system</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="form">форма:</label>
                        <select class="form-control" id="form" name="form">
                            <option>pills</option>
                            <option>capsules</option>
                            <option>powder</option>
                            <option>drops</option>
                            <option>solution</option>
                            <option>mixture</option>
                            <option>suspension</option>
                            <option>aerosol</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="amount">количество препарата:</label>
                        <p id="amountErr"><c:out value="${requestScope.errorMap['invalidDrugAmount']}"/></p>
                        <input type="text" class="form-control" id="amount" name="drugAmount">
                    </div>
                    <div class="form-group">
                        <label for="as">активные вещества:</label>
                        <p id="activeSubstancesErr"><c:out value="${requestScope.errorMap['invalidAS']}"/></p>
                        <textarea class="form-control" rows="5" id="as" name="activeSubstances"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="country">страна производитель:</label>
                        <p id="countryErr"><c:out value="${requestScope.errorMap['invalidCountry']}"/></p>
                        <input type="text" class="form-control" id="country"
                               name="country">
                    </div>
                    <div class="form-group">
                        <label for="dispensing">отпуск:</label>
                        <select class="form-control" id="dispensing" name="dispensing">
                            <option>without prescription</option>
                            <option>on prescription</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="price">стоимость:</label>
                        <p id="priceErr"><c:out value="${requestScope.errorMap['invalidPrice']}"/><c:out value="${requestScope.ENI}"/></p>
                        <input type="text" class="form-control" id="price" name="price">
                    </div>
                    <div class="form-group">
                        <label for="quantity">количество:</label>
                        <p id="quantityErr"><c:out value="${requestScope.ENI}"/></p>
                        <input type="number" class="form-control" id="quantity" name="quantity" min="1">
                    </div>
                    <button type="submit">add</button>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>