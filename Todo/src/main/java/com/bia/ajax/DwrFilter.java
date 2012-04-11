package com.bia.ajax;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;

import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;

/**
 *
 * @author intesar
 *
 * Filter for checking user has session
 */
public class DwrFilter implements AjaxFilter {

    @Override
    public Object doFilter(Object obj, Method method, Object[] params, AjaxFilterChain chain) throws Exception {
        try {
//                long startTime = Calendar.getInstance().getTimeInMillis();
                Object object = chain.doFilter(obj, method, params);
//                long totalTime = Calendar.getInstance().getTimeInMillis() - startTime;
//                if (totalTime > 1000 && logger.isInfoEnabled()) {
//                    logger.info(" Execution of : " + method.getName() + " took : " + totalTime + " ms");
//                } 
                return object;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        } 
    }
     private static final Logger log = Logger.getLogger(DwrFilter.class);
}
