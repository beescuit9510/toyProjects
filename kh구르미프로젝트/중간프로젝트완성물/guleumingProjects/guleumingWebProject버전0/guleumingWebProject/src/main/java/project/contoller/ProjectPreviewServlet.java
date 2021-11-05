package project.contoller;

import java.io.IOException;
import java.time.temporal.ChronoUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.service.ProjectService;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;

/**
 * Servlet implementation class ProjectPreviewServlet
 */
@WebServlet(name = "ProjectPreview", urlPatterns = { "/projectPreview" })
public class ProjectPreviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectPreviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			int target = Integer.parseInt(request.getParameter("preTarget"));
			String projTitle = request.getParameter("preProjTitle");
			String endDate = request.getParameter("preEndDate");
			String shipDate = request.getParameter("preShipDate");
			String reTitle = request.getParameter("preReTitle");
			String projStory = request.getParameter("preProjStory");
			String payDate = new ProjectService().selectPayDate(endDate);
			if(payDate != null) {
				 long period = new ProjectService().selectPeriod(endDate);
				 request.setAttribute("period", period);
			}
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/project/preview.jsp");
			request.setAttribute("projTitle", projTitle);
			request.setAttribute("target", target);
			request.setAttribute("endDate", endDate);
			request.setAttribute("reTitle", reTitle);
			request.setAttribute("shipDate", shipDate);
			request.setAttribute("projStory", projStory);
			request.setAttribute("payDate", payDate);
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
