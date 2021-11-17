package kr.or.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardWithFile;
import kr.or.board.model.vo.FileVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/boardList.do")
	public String boardList(Model model) {
		ArrayList<Board> list = service.selectBoardList();
		
		model.addAttribute("list",list);
		
		return "board/boardList";

	}
//	@RequestMapping(value="/boardView.do")
//	public String boardView(String boardNo, Model model) {
//		Board board = service.boardView(boardNo);
//		
//		model.addAttribute("board",board);
//		
//		return "board/boardView";
//	};
//	
	@RequestMapping(value="/boardWriteFrm1.do")
	public String boardWriteFrm1() {
		return "board/boardWriteFrm1";
	}; 
	
//	@RequestMapping(value="/insertBoard.do")
//	public String insertBoard1(Board board, Model model) {
//		
//		int result = service.insertBoard1(board);
//		if(result > 0) {
//			model.addAttribute("msg","작성 성공");
//		}else {
//			model.addAttribute("msg","작성 실패");			
//		}
//		model.addAttribute("loc","/boardList.do");
//		
//		return "common/msg";
//	};

	@RequestMapping(value="/boardWrite1.do")
	public String insertBoard1(Board board) {
		
		int result = service.insertBoard1(board);
		
		if(result > 0) {
			return "redirect:/boardList.do";
		}else {
			return "board/boardWriteFrm1";
		}
		
	};	
	
	@RequestMapping(value="/boardView.do")
	public String boardView(String strBoardNo, Model model) {
		
		int boardNo = -1;
		
		try {
			boardNo = Integer.parseInt(strBoardNo);
		
			if(boardNo < 0) {
				throw new NumberFormatException();
			}

		}catch (NumberFormatException e) {
			return "redirect:/boardList.do";

		}
		
		Board board = service.selectOneBoard(boardNo);
		
		model.addAttribute("board",board);
		
		return "board/boardView";
	};
	
	@RequestMapping(value="/boardWriteFrm2.do")
	public String boardWriteFrm2() {
		return "board/boardWriteFrm2";
	}
	
	@RequestMapping(value="/boardWrite2.do")
	public String boardWrite2(Board b, MultipartFile[] files, HttpServletRequest request, Model model){
		//파일목록을 저장할 List		
		ArrayList<FileVO> list = new ArrayList<>();
		System.out.println(b);
		Stream.of(files).forEach(System.out::println);
		
		//MultipartFile[] 배열은 파일을 첨부하지 않더라도 무조건 길이가 1인 배열
		//배열의 첫번재 파일이 비어있는지 체크하는 방식으로 파일 첨부 여부 확인
		if(files[0].isEmpty()) {
			//첨부파일이 없는 경우
		}else {
			//첨부파일이 있는 경우
			//MultipartFile을 이용해서 파일업로드 작업을 수행해야함
			//파일을 업로드할 경로 설정
			//request .... getRealPath -> /webapp/폴더
			String savePath = request
								.getSession()
								.getServletContext()
								.getRealPath("/resources/upload/board/");
			
			//반복문을 이용해 파일 처리
			for(MultipartFile file: files) {
				//유저가 올린 파일명이 이미 업로드 폴더에 존재하면 덮어쓰기가 되어 이전 파일이 삭제
				
				//파일명 중복 처리
				
				
				//사용자가 올린 파일명
				String filename = file.getOriginalFilename();
				
				//test.txt ->  test_1.txt, test_2.txt
				//test.txt -> test			.txt -> 파일명을 확장자 기준으로 나눔
				String onlyFilename = filename.substring(0,filename.indexOf("."));//test
				String extention = filename.substring(filename.indexOf("."));//.txt
				
				//실제 업로드할 파일명을 저장할 변수
				String filepath = null;

				int count = 0;
				while(true) {
					if(count==0) {
						filepath = onlyFilename+extention;//test.txt
					}else {
						filepath = onlyFilename+"_"+count+extention;//test_3.txt
					}
					File checkFile = new File(savePath+filepath);//java.io.File
					if(!checkFile.exists()) {
						break;
					}
					count++;					
				}
				//파일명 중복처리가 끝나면 파일 업로드
				
				try {
					//중복처리가 끝난 파일명(filepath)으로 파일을 업로드
					FileOutputStream fos = new FileOutputStream(new File(savePath+filepath));
					//업로드 속도 증가를 위한 보조스트림
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					
					//파일 업로드
					byte[] bytes = file.getBytes();
					bos.write(bytes);
					bos.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				FileVO fv = new FileVO();
				fv.setFilename(filename);
				fv.setFilepath(filepath);
				list.add(fv);
				
				
			}
			
			
		}
		
		int result = service.insertBoard2(b,list);
		
		if(result == -1 || result != list.size()) {
			model.addAttribute("msg","작성 실패");			
		}else {
			model.addAttribute("msg","작성 성공");
		}
		model.addAttribute("loc","/");
		
		return "common/msg";
		
	}
	
	@RequestMapping(value="/boardView1.do")
	public String boardView2(int boardNo, Model model) {
		//boardNo를 이용하여 조회한 board객체, ArrayList<FileVO>
		
		//contoller는 service 한번만 호출
		//service에서 필요할때 dao 여러번 호출
		//데이터는 하나로 묶어서 리턴
				
		Board board = service.selectBoard1(boardNo);
		
		model.addAttribute("board",board);
		
		return "board/boardView";
	}
	

	
}
