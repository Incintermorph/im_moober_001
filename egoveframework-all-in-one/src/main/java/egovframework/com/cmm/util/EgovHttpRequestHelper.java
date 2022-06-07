package egovframework.com.cmm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.FacesRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.intermorph.cmmn.util.IMStringUtil;

/**
 * @Class Name : EgovHttpRequestHelper.java
 * @Description : HTTP Request 정보 취득 Helper 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2014.09.11	표준프레임워크		최초생성
* @author Vincent Han
 * @since 2014.09.11
 * @version 3.5
 * @see <pre>
 * web.xml 상에 다음과 같은 Listener 등록 필요
 * &lt;listener&gt;
 *	  &lt;listener-class&gt;org.springframework.web.context.request.RequestContextListener&lt;/listener-class&gt;
 * &lt;/listener&gt;
 * </pre>
 */
public class EgovHttpRequestHelper {
	
	public static boolean isInHttpRequest() {
		try {
			if(getCurrentRequest() == null ) {
				return false;
			}
		} catch (IllegalStateException ise) {
			return false;
		}
		
		return true;
	}
	
	public static HttpServletRequest getCurrentRequest() {
		
		
		if(RequestContextHolder.currentRequestAttributes().getClass().getName().equals("org.springframework.web.context.request.FacesRequestAttributes")){
			//FacesRequestAttributes rs=	(FacesRequestAttributes)RequestContextHolder.currentRequestAttributes();
			return null;
		}else {
		
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return sra.getRequest();
		}
	}
	
	public static String getRequestIp() {
		return IMStringUtil.getRemoteAddr(getCurrentRequest());
	}
	
	public static String getRequestURI() {
		return getCurrentRequest().getRequestURI();
	}
	
	public static HttpSession getCurrentSession() {
		return getCurrentRequest().getSession();
	}
}

