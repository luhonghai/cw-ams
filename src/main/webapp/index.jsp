<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="section" tagdir="/WEB-INF/tags/layouts/sections" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<t:main pageTitle="Shopping online">
    <section:slider></section:slider>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <layout:left-sidebar></layout:left-sidebar>
                </div>
                <div class="col-sm-9 padding-right">
                    <layout:features-items></layout:features-items>

                    <layout:category-tab></layout:category-tab>

                    <layout:recommened-items></layout:recommened-items>
                </div>
            </div>
        </div>
    </section>
</t:main>

