package index.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import index.service.IndexService;

/**
 * Servlet implementation class IndexTotalServlet
 */
@WebServlet(name = "IndexTotal", urlPatterns = { "/indexTotal" })
public class IndexTotalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexTotalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("utf-8");
				int rewardTotal = new IndexService().rewardTotal();
				int noticeTotal = new IndexService().noticeTotal(); 
				int eventTotal = new IndexService().eventTotal();
				request.setAttribute("rewardTotal", rewardTotal);
				request.setAttribute("noticeTotal", noticeTotal);
				request.setAttribute("eventTotal", eventTotal);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
