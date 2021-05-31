package io.ngss.taksitle.report.config;

import io.ngss.taksitle.report.security.util.SecurityTokenGenerator;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;


//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CorsConfig implements Filter {
//
//    public static final String BO_CORS_DOMAIN = "http://boffice.kredilink.ngss.io";
//    public static final String DEALER_CORS_DOMAIN = "https://test.ngss.io";
//
//    @Autowired
//    private SecurityTokenGenerator securityTokenGenerator;
//
//    @Override
//    public void init(FilterConfig fc) throws ServletException {
//    }
//
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//
//        HttpServletResponse response = (HttpServletResponse) resp;
//
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, Authorization, Content-Detail");
//        response.setHeader("Access-Control-Expose-Headers", "x-requested-with, Content-Type, Authorization");
//        /*response.setHeader(" Strict-Transport-Security", " max-age=16070400; includeSubDomains");
//        response.setHeader("X-Frame-Options", "deny");
//        response.setHeader("X-XSS-Protection", "1; mode=block");
//        response.setHeader("X-Content-Type-Options", "nosniff");
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");*/
//
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else if (request.getMethod().equalsIgnoreCase("POST") && !request.getRequestURI().contains("/integration/")) {
//            try {
//                String tokenString = request.getHeader("Content-Detail");
//                Long token = Long.valueOf(tokenString);
//                if (SecurityTokenGenerator.isUsedTokenBefore(token)) {
//                    System.out.println(token + " is used before.");
//                    response.setStatus(HttpServletResponse.SC_CONFLICT);
//                    return;
//                }
//                chain.doFilter(req, resp);
//            } catch (Exception ex) {
//                System.out.println("Chain exception" + ex.getMessage());
//                response.setStatus(HttpServletResponse.SC_CONFLICT);
//                return;
//            }
//        } else {
//            chain.doFilter(req, resp);
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//    private void doFilterAndArrangeHeaders(FilterChain chain, ServletRequest request, ServletResponse response) throws IOException, ServletException {
//        chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
//            public void setHeader(String name, String value) {
//                if (!name.equalsIgnoreCase(HttpHeaders.ETAG)) {
//                    super.setHeader(name, value);
//                }
//            }
//        });
//    }
//}