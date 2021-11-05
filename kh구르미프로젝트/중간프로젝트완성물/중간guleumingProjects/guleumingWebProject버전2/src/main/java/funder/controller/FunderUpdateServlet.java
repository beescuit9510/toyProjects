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
 * Servlet implementation class FunderUpdateServlet
 */
@WebServlet(name = "FunderUpdate", urlPatterns = { "/funderUpdate" })
public class FunderUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FunderUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String oldFilepath = mRequest.getParameter("oldFilepath");
		if(oldFilepath != null) {
			mb.setProfileFilepath(oldFilepath);
		}
		int result = new FunderService().updateFunder(mb);
		RequestDispatcher view = request.getRequestDispatcher("/funderView?writerNo="+mb.getWriterNo());			
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
