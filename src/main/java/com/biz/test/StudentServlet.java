package com.biz.test;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.entity.Student;
import com.biz.util.RedisDriver;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
		if(request.getParameter("method").endsWith("listByDesc"))
			listByDesc(request,response);
		else if(request.getParameter("method").endsWith("update"))
				update(request,response);
		else if(request.getParameter("method").endsWith("delete"))
			delete(request, response);
		else if(request.getParameter("method").endsWith("add"))
			add(request, response);
		else
			listByDesc(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			listByDesc(request, response);
		}catch(NullPointerException e){
			listByDesc(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void listByDesc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int pageSize=10;
		int pageNum=1;
		try{
		pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}catch(Exception e){
			pageNum=1;
		}
		try {
			int totalPage=1;
			if(RedisDriver.getRedisDriver().getStudentNum()%10==0&&RedisDriver.getRedisDriver().getStudentNum()!=0)
			request.setAttribute("totalPage", totalPage=RedisDriver.getRedisDriver().getStudentNum()/10);
			else
				request.setAttribute("totalPage", totalPage=RedisDriver.getRedisDriver().getStudentNum()/10+1);
			if(pageNum>totalPage)
				pageNum=totalPage;
			System.out.println(pageNum);
			request.setAttribute("stu", RedisDriver.getRedisDriver().listStudentByPagingAndDesc(pageSize, pageNum));
			request.setAttribute("pageNum", pageNum);
		} catch (ParseException e) {
			request.setAttribute("stu", null);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException{
		Student stu=new Student();
		stu.setId(request.getParameter("id"));
		stu.setName(request.getParameter("name"));
		stu.setBirthday(request.getParameter("birthday"));
		stu.setDescription(request.getParameter("description"));
		stu.setAvgscore(request.getParameter("avgscore"));
		RedisDriver.getRedisDriver().updateStudent(stu);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Student stu=new Student();
		stu.setName(request.getParameter("name"));
		try {
			stu.setBirthday(request.getParameter("birthday"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stu.setDescription(request.getParameter("description"));
		stu.setAvgscore(request.getParameter("avgscore"));
		RedisDriver.getRedisDriver().addStudent(stu);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RedisDriver.getRedisDriver().delStudent(Integer.parseInt(request.getParameter("id")));
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
