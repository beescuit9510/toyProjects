package api.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class MovieRankServlet
 */
@WebServlet(name = "MovieRank", urlPatterns = { "/movieRank" })
public class MovieRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieRankServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		String key = "2d1960a325f72e312330c46c40440bb2";
		String targetDt = request.getParameter("targetDt");

		String result = Jsoup.connect(url) // 접속할 url
				.data("key", key) // 매개변수 key 설정
				.data("targetDt", targetDt) // 매개변수 targetDt 설정
				.userAgent("Mozilla") // 접속 브라우저 설정/IE인지 확인할때(mozilla는 일단적으로 표준 브라우저)
				.ignoreContentType(true) // 리턴하는 데이터 타입은 (json이든 xml이든) 상관없음!
				.execute() // 실행
				.body(); // 실행결과를 문자열 형태로 변환
//https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?
//key=2d1960a325f72e312330c46c40440bb2
//&targetDt=20211012		

		System.out.println(result);

		JsonParser parser = new JsonParser(); // 문자열을 json객체로 변환해줄 객체
		JsonObject movieRank = (JsonObject) parser.parse(result); // result문자열을 json타입으로 변환

//		JsonObject boxOfficeResult = (JsonObject)movieRank.getAsJsonObject("boxOfficeResult"); 

		JsonObject boxOfficeResult = movieRank.getAsJsonObject("boxOfficeResult");
		// movieRank객체에서 키가 boxOfficeResult 인 값을 JsonObject타입으로 추출;
		// boxOfficeResult 키의 값이 오브라젝라 JsonObject가 자료형;

		JsonArray dailyBoxOfficeList = boxOfficeResult.getAsJsonArray("dailyBoxOfficeList");
		// boxOfficeResult 객체에서 키가 dailyBoxOfficeList 인 값을 JsonArray타입으로 추출;

		// 가져온 데이터를 자바에서 사용하기위해 자바형식 객체로 변환
		ArrayList<Movie> movies = new ArrayList<Movie>();
		for (int i = 0; i < dailyBoxOfficeList.size(); i++) {
			// dailyBoxOfficeList에서 0번째 데이터를 꺼내서 JsonObject타입으로 변환;
			JsonObject movie = (JsonObject)dailyBoxOfficeList.get(i);
			
			Movie movieToAdd = new Movie();
			
			movieToAdd.setMovieNm(movie.get("movieNm").getAsString());
			movieToAdd.setRank(movie.get("rank").getAsString());
			movieToAdd.setOpenDt(movie.get("openDt").getAsString());
			movieToAdd.setSalesAcc(movie.get("salesAcc").getAsString());
			movieToAdd.setAudiAcc(movie.get("audiAcc").getAsString());
			
			movies.add(movieToAdd);
		}
		
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("application/json");
		
		new Gson().toJson(movies, response.getWriter());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
