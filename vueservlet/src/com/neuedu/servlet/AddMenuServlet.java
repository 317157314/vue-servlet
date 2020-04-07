package com.neuedu.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.exception.DbException;
import com.neuedu.pojo.SysMenu;
import com.neuedu.service.IMenuService;
import com.neuedu.service.impl.MenuServiceImpl;

/**
 * Servlet implementation class AddMenuServlet
 */
@WebServlet("/addMenu")
public class AddMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parentName=request.getParameter("parentName");
		String menuName=request.getParameter("menuName");
		String orderNum=request.getParameter("orderNum");
		String visible=request.getParameter("visible");
		String path=request.getParameter("path");
		String icon=request.getParameter("icon");
		
		IMenuService ms=new MenuServiceImpl();
		try {
			
			int parentId=ms.getParentIdByParentName(parentName);
			
			SysMenu webmenu=new SysMenu();
			if(parentId>0){
				webmenu.setParentId(parentId);
			}else{
				
			}
			webmenu.setIcon(icon);
			webmenu.setMenuName(menuName);
			webmenu.setOrderNum(orderNum);
			webmenu.setParentName(parentName);
			webmenu.setPath(path);
			webmenu.setVisible(visible);
					
			int i = ms.addMenu(webmenu);
			if(i>0){
				response.getWriter().print("success");
				}else{
					response.getWriter().print("failure");
				}
			} catch (SQLException | DbException e) {
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
