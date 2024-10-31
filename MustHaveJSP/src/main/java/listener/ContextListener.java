package listener;

import java.util.Enumeration;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		Enumeration<String> apps=sce.getServletContext().getInitParameterNames();
		while(apps.hasMoreElements()) {
			System.out.println("리스너 컨텍스트 초기화 매개변수 소멸: "+apps.nextElement());
		}
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//xml의 매개변수에 접근
				Enumeration<String> apps=sce.getServletContext().getInitParameterNames();
				
				while(apps.hasMoreElements()) {
					System.out.println("[리스너] 컨테스트 초기화 매개변수 생서 : "+ apps.nextElement());
				}
	}
	
	

}
