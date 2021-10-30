package funding.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdatePaymentServlet
 */
@WebServlet(name = "UpdatePayment", urlPatterns = { "/updatePayment" })
public class UpdatePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rewardPrice = Integer.parseInt(request.getParameter("rewardPrice"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String shippingDate = request.getParameter("shippingDate");
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		String rewardTitle = request.getParameter("rewardTitle");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/funding/payment.jsp");
		request.setAttribute("rewardPrice", rewardPrice);
		request.setAttribute("amount", amount);
		request.setAttribute("shippingDate", shippingDate);
		request.setAttribute("projectNo", projectNo);
		request.setAttribute("rewardTitle",rewardTitle);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
