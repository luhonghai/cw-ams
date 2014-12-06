<%@ tag import="java.util.Collection" %>
<%@ tag import="uk.ac.gre.cw.aircraft.jdo.entities.Product" %>
<%@ tag import="uk.ac.gre.cw.aircraft.service.ProductService" %>
<%@tag description="Features items" pageEncoding="UTF-8" %>
<%
    ProductService productService = new ProductService();
    Collection<Product> products = productService.findAll();
%>
<div class="features_items"><!--features_items-->
    <h2 class="title text-center">Features Items</h2>
    <%
        if (products != null && products.size() > 0) {
        for (Product product : products) {
            if (product.isFeatured()) {
    %>
    <div class="col-sm-4">
        <div class="product-image-wrapper">
            <div class="single-products">
                <div class="productinfo text-center">
                    <img src="<%=product.getImageUrl()%>" alt="" />
                    <h2>£<%=(int) product.getUnitPrice()%></h2>
                    <p><%=product.getName()%></p>
                    <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                </div>
                <div class="product-overlay">
                    <div class="overlay-content">
                        <h2>£<%=(int) product.getUnitPrice()%></h2>
                        <p><%=product.getName()%></p>
                        <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                    </div>
                </div>
                <%=(product.isNew() ? "<img src=\"images/home/new.png\" class=\"new\" alt=\"\" />" : "")%>
                <%=(product.isSale() ? "<img src=\"images/home/sale.png\" class=\"new\" alt=\"\" />" : "")%>
            </div>
            <div class="choose">
                <ul class="nav nav-pills nav-justified">
                    <li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
                    <li><a href="#"><i class="fa fa-plus-square"></i>Add to compare</a></li>
                </ul>
            </div>
        </div>
    </div>
    <%
                }
        }
        }
    %>
</div><!--features_items-->