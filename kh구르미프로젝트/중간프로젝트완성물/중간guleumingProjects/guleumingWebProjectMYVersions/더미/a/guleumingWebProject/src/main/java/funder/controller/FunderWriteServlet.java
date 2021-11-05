package funder.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import funder.model.service.FunderService;
import table.model.vo.MakerBoard;

/**
 * Servlet implementation class FunderWriteServlet
 */
@WebServlet(name = "FunderWrite", urlPatterns = { "/funderWrite" })
public class FunderWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FunderWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. view
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/funder";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		MakerBoard mb = new MakerBoard();
		mb.setWriterNo(Integer.parseInt(mRequest.getParameter("writerNo")));
		mb.setOpenDate(mRequest.getParameter("openDate"));
		mb.setBoardEmail(mRequest.getParameter("boardEmail"));
		mb.setSearchTag(mRequest.getParameter("tagadd"));
		mb.setSkillName(mRequest.getParameter("skillName"));
		mb.setSkillLevel(mRequest.getParameter("skillLevel"));
		mb.setCompanyAddr(mRequest.getParameter("companyAddr"));
		mb.setCompanyIntro(mRequest.getParameter("companyIntro"));
		mb.setProfileFilepath(mRequest.getFilesystemName("profileFilepath"));
		mb.setFunderCategory(mRequest.getParameter("funderCategory"));
		mb.setBusinessName(mRequest.getParameter("businessName"));
		
		System.out.println(mb);
		//비즈니스 로직
		int result = new FunderService().insertFunder(mb);
		
		RequestDispatcher view = request.getRequestDispatcher("/funder/funderList?reqPage=1");			
		//결과처리
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
