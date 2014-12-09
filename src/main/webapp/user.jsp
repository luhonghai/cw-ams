<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:main pageTitle="User management" requireAdminRole="true">
  <div>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary table-add-new" data-toggle="modal" data-target="#dataModal">
      <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add new
    </button>

    <!-- Modal -->
    <div class="modal fade" id="dataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="dataModelTitle" target="User">Add new User</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" role="form" id="dataModelForm" act="add">
              <div class="form-group" id="dataModelID">
                <label for="txtId" class="col-sm-2 control-label">ID</label>
                <div class="col-xs-4">
                  <input type="text" class="form-control" id="txtId" value="" disabled="disabled">
                </div>
              </div>
              <div class="form-group">
                <label for="txtFirstname" class="col-sm-2 control-label">First name</label>
                <div class="col-xs-4">
                  <input type="text" class="form-control" id="txtFirstname" placeholder="First name">
                </div>
              </div>
              <div class="form-group">
                <label for="txtLastname" class="col-sm-2 control-label">Last name</label>
                <div class="col-xs-4">
                  <input type="text" class="form-control" id="txtLastname" placeholder="Last name">
                </div>
              </div>
              <div class="form-group">
                <label for="txtUsername" class="col-sm-2 control-label">Username</label>
                <div class="col-xs-4">
                  <input type="text" class="form-control" id="txtUsername" placeholder="Username">
                </div>
              </div>
              <div class="form-group">
                <label for="txtEmail" class="col-sm-2 control-label">Email</label>
                <div class="col-xs-4">
                  <input type="text" class="form-control" id="txtEmail" placeholder="example@mail.com">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Gender</label>
                <label class="checkbox-inline">
                  <input type="radio" name="gender" id="rdMale" value="male" checked>
                  Male
                </label>
                <label class="checkbox-inline">
                  <input type="radio" name="gender" id="rdFemale" value="female">
                  Female
                </label>
              </div>
              <div class="form-group">
                <label for="txtPassword" class="col-sm-2 control-label">Password</label>
                <div class="col-xs-4">
                  <input type="password" class="form-control" id="txtPassword">
                </div>
              </div>
              <div class="form-group">
                <label for="selRoles" class="col-sm-2 control-label">Roles</label>
                <div class="col-xs-4">
                  <select id="selRoles" multiple="multiple">
                    <option value="1">Administrator</option>
                    <option value="2">Engineer</option>
                  </select>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary dataModelSave">Save</button>
          </div>
        </div>
      </div>
    </div>

    <table class="table table-striped table-bordered table-hover" id="data-table" table-data="user">
    </table>
  </div>
</t:main>
