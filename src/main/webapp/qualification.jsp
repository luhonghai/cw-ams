<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:main pageTitle="Qualification management">
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
            <h4 class="modal-title" id="dataModelTitle" target="Qualification">Add new Qualification</h4>
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
                <label for="txtName" class="col-sm-2 control-label">Name</label>
                <div class="col-xs-4">
                  <input type="text" class="form-control" id="txtName" placeholder="Name">
                </div>
              </div>
              <div class="form-group">
                <label for="txtDescription" class="col-sm-2 control-label">Description</label>
                <div class="col-xs-4">
                  <textarea class="form-control" id="txtDescription" placeholder="Description"></textarea>
                </div>
              </div>
              <div class="form-group">
                <label for="selEngineer" class="col-sm-2 control-label">Engineers</label>
                <div class="col-xs-4">
                  <select id="selEngineer" multiple="multiple" type="qualification-engineer">
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

    <table class="table table-striped table-bordered table-hover" id="data-table" table-data="qualification">
    </table>
  </div>
</t:main>
