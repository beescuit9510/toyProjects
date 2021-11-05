package funding.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import funding.model.service.fundingService;

/**
 * Servlet implementation class FundingLikeUpServlet
 */
@WebServlet(name = "FundingLikeUp", urlPatterns = { "/fundingLikeUp" })
public class FundingLikeUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingLikeUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서버요청 성공~~");
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		System.out.println(projectNo);
		int cMemberNo = Integer.parseInt(request.getParameter("cMemberNo"));
		System.out.println(cMemberNo);
		int result = new fundingService().updateFundingLike(projectNo,cMemberNo);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
