/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.71
 * Generated at: 2021-10-04 07:30:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import member.model.vo.Member;
import member.model.vo.Member;

public final class mypage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1633329321605L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1633070613953L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("member.model.vo.Member");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>my page</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    ");

    	Member member = (Member)session.getAttribute("member");
    
      out.write("\r\n");
      out.write("	<!-- 폰트어썸(아이콘) -->\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"/fontawesome/css/all.css\">\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/fontawesome/js/all.js\"></script>\r\n");
      out.write("	<!-- 부트스트랩 CSS -->\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"/css/bootstrap.css\">\r\n");
      out.write("	<!-- 글꼴적용(NotoSans 폰트) -->\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"/css/font.css\">\r\n");
      out.write("	<!-- 기본 CSS -->\r\n");
      out.write("	<link rel=\"stylesheet\" href=\"/css/default.css\">\r\n");
      out.write("	<!-- jQuery라이브러리 추가(2개) -->\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/js/jquery-3.3.1.js\"></script>\r\n");
      out.write("	<!-- 부트스트랩용 jQuery -->\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("	\r\n");
      out.write("	<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary\">\r\n");
      out.write("		<a class=\"navbar-brand\" href=\"/\">Byunduck's World</a>\r\n");
      out.write("		<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" \r\n");
      out.write("		data-target=\"#navbarColor01\" aria-controls=\"navbarColor01\" aria-expanded=\"false\"\r\n");
      out.write("		aria-label=\"Toggle navigation\">\r\n");
      out.write("			<span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("		</button>\r\n");
      out.write("		<div class=\"collapse navbar-collapse\" id=\"navbarColor01\">\r\n");
      out.write("			<ul class=\"navbar-nav mr-auto\">\r\n");
      out.write("				<li class=\"nav-item\">\r\n");
      out.write("					<a class=\"nav-link\" href=\"#\">Menu1</a>\r\n");
      out.write("				</li>\r\n");
      out.write("				<li class=\"nav-item\">\r\n");
      out.write("					<a class=\"nav-link\" href=\"#\">Menu2</a>\r\n");
      out.write("				</li>\r\n");
      out.write("				<li class=\"nav-item\">\r\n");
      out.write("					<a class=\"nav-link\" href=\"#\">Menu3</a>\r\n");
      out.write("				</li>\r\n");
      out.write("				<li class=\"nav-item dropdown\">\r\n");
      out.write("					<a class=\"nav-link dropdown-toggle\" data-toggle=\"dropdown\"\r\n");
      out.write("					href=\"#\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Menu4</a>\r\n");
      out.write("					<div class=\"dropdown-menu\">\r\n");
      out.write("						<a class=\"dropdown-item\" href=\"#\">sub-1</a>\r\n");
      out.write("						<a class=\"dropdown-item\" href=\"#\">sub-2</a>\r\n");
      out.write("						<div class=\"dropdown-divider\"></div>\r\n");
      out.write("						<a class=\"dropdown-item\" href=\"#\">sub-3</a>\r\n");
      out.write("						<a class=\"dropdown-item\" href=\"#\">sub-4</a>\r\n");
      out.write("					</div>\r\n");
      out.write("				</li>\r\n");
      out.write("			</ul>\r\n");
      out.write("			\r\n");
      out.write("			");
if(member == null){ 
      out.write("\r\n");
      out.write("				<button class=\"btn btn-secondary my-2 my-0\" data-toggle=\"modal\" data-target=\".modal\">로그인</button>\r\n");
      out.write("				<a href=\"/joinFrm\" class=\"btn btn-secondary my-2 my-sm-0\">회원가입</a>\r\n");
      out.write("			");
} else { 
      out.write("\r\n");
      out.write("				<a href=\"/mypage\" class=\"btn btn-secondary my-2 my-sm-0\">");
      out.print(member.getMemberName() );
      out.write("</a>\r\n");
      out.write("				<a href=\"/logout\" class=\"btn btn-secondary my-2 my-sm-0\">로그아웃</a>\r\n");
      out.write("			");
} 
      out.write("\r\n");
      out.write("			\r\n");
      out.write("		</div>\r\n");
      out.write("	</nav>\r\n");
      out.write("	");
if(member == null){ 
      out.write("\r\n");
      out.write("	<div class=\"modal\">\r\n");
      out.write("		<div class=\"modal-dialog\" role=\"documnet\">\r\n");
      out.write("			<div class=\"modal-content\">\r\n");
      out.write("				<div class=\"modal-header\">\r\n");
      out.write("					<h5 class=\"modal-title\">로그인</h5>\r\n");
      out.write("					<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n");
      out.write("						<span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("					</button>\r\n");
      out.write("				</div>\r\n");
      out.write("				<form action=\"/login\" method=\"post\" name=\"loginFrm\">\r\n");
      out.write("					<div class=\"modal-body\">\r\n");
      out.write("						<div class=\"form-group\">-\r\n");
      out.write("							<label for=\"memberId\">아이디</label>\r\n");
      out.write("							<input type=\"text\" name=\"memberId\" id=\"memberId\" class=\"form-control\" placeholder=\"아이디입력\">\r\n");
      out.write("						</div>\r\n");
      out.write("						<div class=\"form-group\">\r\n");
      out.write("							<label for=\"memberPw\">아이디</label>\r\n");
      out.write("							<input type=\"password\" name=\"memberPw\" id=\"memberPw\" class=\"form-control\" placeholder=\"비밀번호 입력\">\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"modal-footer\">\r\n");
      out.write("						<button type=\"submit\" class=\"btn btn-primary\">로그인</button>\r\n");
      out.write("						<button type=\"button\" class=\"btn btn-secondary cls\" data-dismiss=\"modal\" onclick=\"initInput();\">닫기</button>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"modal-footer\">\r\n");
      out.write("						<a href=\"#\">아이디/비밀번호 찾기</a>\r\n");
      out.write("					</div>\r\n");
      out.write("				</form>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("	");
} 
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	<script>\r\n");
      out.write("		function initInput(){\r\n");
      out.write("			$(\"[name=loginFrm] input\").val(\"\");\r\n");
      out.write("		}\r\n");
      out.write("	</script>\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	");
      out.write('\r');
      out.write('\n');
      out.write('	');

//		Member member = (Member)session.getAttribute("member");
	// header에서 이미 member가 있기 때문에 Duplicate local variable member 오류가 남;
	
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("	<form action=\"/updateMember\" method=\"post\">\r\n");
      out.write("		<legend>내정보</legend>\r\n");
      out.write("		<div class=\"form-group\">\r\n");
      out.write("			<fieldset>\r\n");
      out.write("				<label class=\"control-label\" for=\"memberId\">아이디</label>\r\n");
      out.write("				<input type=\"text\" id=\"memberId\" name=\"memberId\" class=\"form-control\"\r\n");
      out.write("				value='");
      out.print(member.getMemberId() );
      out.write("' readonly>\r\n");
      out.write("			</fieldset>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"form-group\">\r\n");
      out.write("			<fieldset>\r\n");
      out.write("				<label class=\"control-label\" for=\"memberPw\">비밀번호</label>\r\n");
      out.write("				<input type=\"text\" id=\"memberPw\" name=\"memberPw\" class=\"form-control\"\r\n");
      out.write("				value='");
      out.print(member.getMemberPw() );
      out.write("' >\r\n");
      out.write("			</fieldset>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"form-group\">\r\n");
      out.write("			<fieldset>\r\n");
      out.write("				<label class=\"control-label\" for=\"memberName\">이름</label>\r\n");
      out.write("				<input type=\"text\" id=\"memberName\" name=\"memberName\" class=\"form-control\"\r\n");
      out.write("				value='");
      out.print(member.getMemberName() );
      out.write("' readonly>\r\n");
      out.write("			</fieldset>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"form-group\">\r\n");
      out.write("			<fieldset>\r\n");
      out.write("				<label class=\"control-label\" for=\"phone\">전화번호</label>\r\n");
      out.write("				<input type=\"text\" id=\"phone\" name=\"phone\" class=\"form-control\"\r\n");
      out.write("				value='");
      out.print(member.getPhone() );
      out.write("' >\r\n");
      out.write("			</fieldset>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"form-group\">\r\n");
      out.write("			<fieldset>\r\n");
      out.write("				<label class=\"control-label\" for=\"address\">주소</label>\r\n");
      out.write("				<input type=\"text\" id=\"address\" name=\"address\" class=\"form-control\"\r\n");
      out.write("				value='");
      out.print(member.getAddress() );
      out.write("' >\r\n");
      out.write("			</fieldset>\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"form-group\">\r\n");
      out.write("			<fieldset>\r\n");
      out.write("				<label class=\"control-label\" for=\"enrollDate\">가입일자</label>\r\n");
      out.write("				<input type=\"text\" id=\"enrollDate\" name=\"enrollDate\" class=\"form-control\"\r\n");
      out.write("				value='");
      out.print(member.getEnrollDate() );
      out.write("' >\r\n");
      out.write("			</fieldset>\r\n");
      out.write("		</div>\r\n");
      out.write("		\r\n");
      out.write("		<div class=\"form-grop\">\r\n");
      out.write("			<fieldset style=\"text-align:center;\">\r\n");
      out.write("				<button type=\"submit\" class=\"btn btn-outline-primary\">정보수정</button>\r\n");
      out.write("				");
if(member.getMemberLevel() == 1) {
      out.write("\r\n");
      out.write("				<a href=\"/adminPage\" class=\"btn btn-outline-danger\">회원관리</a>\r\n");
      out.write("				");
} else{ 
      out.write("\r\n");
      out.write("				<a href=\"/deleteMember\" class=\"btn btn-outline-danger\">회원탈퇴</a>\r\n");
      out.write("				");
} 
      out.write("\r\n");
      out.write("			</fieldset>\r\n");
      out.write("		</div>\r\n");
      out.write("	</form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("	<footer id=\"footer\">\r\n");
      out.write("		<div class=\"container\">\r\n");
      out.write("			<div class=\"row\">\r\n");
      out.write("				<div class=\"col-lg-12\">\r\n");
      out.write("					<ul class=\"list-unstyled\">\r\n");
      out.write("						<li><a href=\"#\">이용약관</a></li>\r\n");
      out.write("						<li><a href=\"#\">개인정보취급방침</a></li>\r\n");
      out.write("						<li><a href=\"#\">인재채용</a></li>\r\n");
      out.write("						<li><a href=\"#\">제휴문의</a></li>\r\n");
      out.write("					</ul>\r\n");
      out.write("					<p>Made by Lee</p>\r\n");
      out.write("					<p>KH정보교육원 수업자료입니다. 무단복제하지마세요!!!!!!!</p>\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</footer>");
      out.write("\r\n");
      out.write("	\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
