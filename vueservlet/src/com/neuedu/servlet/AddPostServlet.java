package com.neuedu.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.exception.DbException;
import com.neuedu.pojo.Post;
import com.neuedu.service.IPostService;
import com.neuedu.service.impl.PostServiceImpl;
import com.neuedu.utils.UUIDUtil;

/**
 * Servlet implementation class AddPostServlet
 */
@WebServlet("/addPost")
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postName=request.getParameter("postName");
		String postEncoding=request.getParameter("postEncoding");
		String postSort=request.getParameter("postSort");
		String postStatus=request.getParameter("postStatus");
		String postId=UUIDUtil.generateUUID();
		
		Post post=new Post();
		post.setPostId(postId);
		post.setPostEncoding(postEncoding);
		post.setPostName(postName);
		post.setPostSort(postSort);
		post.setPostStatus(postStatus);
		
		IPostService ps=new PostServiceImpl();
		int i;
		try {
			i = ps.add(post);
			if(i>0){
				response.getWriter().print("success");
				}else{
					response.getWriter().print("failure");
				}
		} catch (DbException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
