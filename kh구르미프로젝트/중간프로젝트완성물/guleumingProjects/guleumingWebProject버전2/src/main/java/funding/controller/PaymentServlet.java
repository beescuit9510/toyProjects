package funding.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funding.model.service.fundingService;
import table.model.vo.Member;
import table.model.vo.PaymentInfo;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet(name = "Payment", urlPatterns = { "/payment" })
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿 들어옴");
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute("member");
		int cMemberNo = member.getcMemberNo();
		Long paymentNo = Long.parseLong(request.getParameter("merchant_uid"));
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		String roadAddr = request.getParameter("roadAddr");
		String detailAddr = request.getParameter("detailAddr");
		String receiveName = request.getParameter("receiveName");
		String addr = roadAddr + detailAddr;
		String receivePhone = request.getParameter("receivePhone");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		PaymentInfo pi = new PaymentInfo();
		pi.setPaymentNo(paymentNo);
		pi.setProjectNo(projectNo);
		pi.setQuantity(quantity);
		pi.setReceiveAddr(addr);
		pi.setReceiveName(receiveName);
		pi.setReceivePhone(receivePhone);
		pi.setcMemberNo(cMemberNo);
		int result = new fundingService().insertPaymentInfo(pi);
		System.out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
