<%@tag pageEncoding="UTF-8" %>
<form role="form" action="<%=request.getContextPath()%>handler/login">
  <fieldset>
    <div class="form-group ">
      <input class="form-control" placeholder="Username" name="username" type="text" autofocus>
    </div>
    <div class="form-group">
      <input class="form-control" placeholder="Password" name="password" type="password" value="">
    </div>
    <div class="checkbox">
      <label>
        <input name="remember" type="checkbox" value="Remember Me">Remember Me
      </label>
    </div>
    <button type="submit"  class="btn btn-lg btn-success btn-block">Login</button>
  </fieldset>
</form>