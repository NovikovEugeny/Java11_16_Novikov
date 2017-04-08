<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet">
    <title>client pharm group page</title>
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
                <form action="clientPharmGroups">
                    <button type="submit">заказать препарат</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command"
                           value="client_show_order_list">
                    <button type="submit">отменить заказ</button>
                </form>
                <form>
                    <button type="submit">сообщения</button>
                </form>
                <form>
                    <button type="submit">история покупок</button>
                </form>
                <form>
                    <button type="submit">баланс на карте</button>
                </form>
            </nav>
        </div>
        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-9">
            <section class="pharm-groups">
                <h3>группы препаратов</h3>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="antibiotics"/>
                    <button type="submit">antibiotics</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="antiviral"/>
                    <button type="submit">antiviral</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="antipyretics"/>
                    <button type="submit">antipyretics</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="allergy"/>
                    <button type="submit">allergy</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="antiseptics"/>
                    <button type="submit">antiseptics</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="cardiovascular"/>
                    <button type="submit">cardiovascular</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="immunity"/>
                    <button type="submit">immunity</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="gastrointestinal"/>
                    <button type="submit">gastrointestinal</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="otolaryngology"/>
                    <button type="submit">otolaryngology</button>
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="show_drugs_to_order"/>
                    <input type="hidden" name="group" value="nervous system"/>
                    <button type="submit">nervous system</button>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>