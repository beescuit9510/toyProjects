package project.contoller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project.service.ProjectService;
import table.model.vo.MakerInfo;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;

/**
 * Servlet implementation class ProjectFrm1Servlet
 */
@WebServlet(name = "ProjectFrm1", urlPatterns = { "/projectFrm1" })
public class ProjectFrm1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectFrm1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("utf-8");
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/signUp/msg.jsp");
			request.setAttribute("msg", "작성오류");
			request.setAttribute("loc", "/");
			view.forward(request , response);
			return;
		} 
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/project";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest
		(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		ProjectBasicInfo pjInfo = new ProjectBasicInfo();
		pjInfo.setBusinessNo(Integer.parseInt(mRequest.getParameter("busiNo")));
		pjInfo.setProjectTitle(mRequest.getParameter("projTitle"));
		pjInfo.setTargetPrice(Integer.parseInt(mRequest.getParameter("target")));
		pjInfo.setStartDate(mRequest.getParameter("startDate"));
		pjInfo.setEndDate(mRequest.getParameter("endDate"));
		String shipDate = pjInfo.getEndDate();
		request.setAttribute("shipDate", shipDate);
		pjInfo.setFundingCategory(mRequest.getParameter("category"));
		pjInfo.setFilepath(mRequest.getFilesystemName("uploadImg"));
		pjInfo.setProjectStory(mRequest.getParameter("projStory"));
		Reward re = new Reward();
		re.setRewardPrice(Integer.parseInt(mRequest.getParameter("rePrice")));
		re.setRewardTitle(mRequest.getParameter("reTitle"));
		re.setRewardContent(mRequest.getParameter("reContent"));
		re.setCancelPolicy(mRequest.getParameter("cancel"));
		re.setShippingDate(mRequest.getParameter("shipDate"));
		re.setqPhone(mRequest.getParameter("qPhone"));
		re.setqEmail(mRequest.getParameter("qEmail"));
		MakerInfo mInfo = new MakerInfo();
		mInfo.setTradeBank(mRequest.getParameter("bank"));
		mInfo.setAccountNumber(Integer.parseInt(mRequest.getParameter("accoNum")));
		mInfo.setDepositName(mRequest.getParameter("makerName"));
		int result = new ProjectService().insertProject(pjInfo,re,mInfo);
		if(result>0) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/signUp/msg.jsp");
			request.setAttribute("msg", "작성완료");
			request.setAttribute("loc", "/");
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/signUp/msg.jsp");
			request.setAttribute("msg", "등록실패");
			request.setAttribute("loc", "/");
			view.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
