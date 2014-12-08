<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="include" tagdir="/WEB-INF/tags/includes" %>
<t:main pageTitle="Job management">
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
            <h4 class="modal-title" id="dataModelTitle" target="Job">Add new Job</h4>
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
                  <input type="text" class="form-control" id="txtName" placeholder="Job name">
                </div>
              </div>

              <div class="form-group">
                <label for="txtDescription" class="col-sm-2 control-label">Desciption</label>
                <div class="col-xs-4">
                  <textarea class="form-control" id="txtDescription" placeholder="Job description"></textarea>
                </div>
              </div>

              <div class="form-group">
                <label for="txtEngineerNumber" class="col-sm-2 control-label">Engineer number</label>
                <div class="col-xs-4">
                  <input type="text" class="form-control" id="txtEngineerNumber" />
                </div>
              </div>
            <include:qualifications-list></include:qualifications-list>
             <div class="form-group">
                <label for="selStatus" class="col-sm-2 control-label">Status</label>
                <div class="col-xs-4">
                  <select class="form-control" id="selStatus">
                    <option value="1">Done</option>
                    <option value="2">Completed</option>
                    <option value="3">In Progress</option>
                    <option value="4" selected="selected">Open</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label for="selPriority" class="col-sm-2 control-label">Priority</label>
                <div class="col-xs-4">
                  <select class="form-control" id="selPriority">
                    <option value="1">Critical</option>
                    <option value="2">Major</option>
                    <option value="3">Minor</option>
                    <option value="4" selected="selected">Trivial</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="selEngineer" class="col-sm-2 control-label">Engineers</label>
                <div class="col-xs-4">
                  <select id="selEngineer" multiple="multiple" type="job-engineer">
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

    <table class="table table-striped table-bordered table-hover" id="data-table" table-data="job">
    </table>
  </div>
</t:main>
