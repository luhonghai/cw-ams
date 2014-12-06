<%@ tag import="uk.ac.gre.cw.aircraft.jdo.entities.Customer" %>
<%@tag pageEncoding="UTF-8" %>
<%
    Object sCustomer = session.getAttribute("customer");
    Customer customer = null;
    if (sCustomer != null) {
        customer = (Customer) sCustomer;
    }
%>
<div class="header-middle"><!--header-middle-->
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="logo pull-left">
                    <a href="<%=request.getContextPath()%>"><img src="images/home/logo.png" alt="" /></a>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="shop-menu pull-right">
                    <ul class="nav navbar-nav">
                        <li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li>
                        <li><a href="checkout"><i class="fa fa-crosshairs"></i> Checkout</a></li>
                        <li><a href="cart"><i class="fa fa-shopping-cart"></i> Cart</a></li>
                        <%
                            if (customer != null) {
                        %>
                            <li><a href="#"><i class="fa fa-user"></i> <%=customer.getName()%></a></li>
                            <li><a href="user/logout"><i class="fa fa-lock"></i> Logout</a></li>
                        <% } else {%>
                            <li><a href="login"><i class="fa fa-lock"></i> Login</a></li>
                        <% }%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div><!--/header-middle-->
