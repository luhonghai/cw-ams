<%@tag description="Header" pageEncoding="UTF-8" %>
<%@attribute name="pageTitle" required="true" %>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header"><%=pageTitle%></h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- Modal -->
<div class="modal fade" id="modalProfile" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Profile</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="pTxtId" class="col-sm-2 control-label">ID</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="pTxtId" value="" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pTxtFirstname" class="col-sm-2 control-label">First name</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="pTxtFirstname" placeholder="First name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pTxtLastname" class="col-sm-2 control-label">Last name</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="pTxtLastname" placeholder="Last name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pTxtUsername" class="col-sm-2 control-label">Username</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="pTxtUsername" placeholder="Username" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pTxtEmail" class="col-sm-2 control-label">Email</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="pTxtEmail" placeholder="example@mail.com">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Gender</label>
                        <label class="checkbox-inline">
                            <input type="radio" name="pGender" id="pRdMale" value="male" checked>
                            Male
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio" name="pGender" id="pRdFemale" value="female">
                            Female
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="pTxtPassword" class="col-sm-2 control-label">Password</label>
                        <div class="col-xs-4">
                            <input type="password" class="form-control" id="pTxtPassword">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary modalProfileSave">Save</button>
            </div>
        </div>
    </div>
</div>