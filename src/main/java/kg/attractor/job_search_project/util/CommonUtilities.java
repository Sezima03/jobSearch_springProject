package kg.attractor.job_search_project.util;

import jakarta.servlet.http.HttpServletRequest;

public class CommonUtilities {
    public CommonUtilities() {}

    public static String getSiteUrl(HttpServletRequest request) {
        String siteUrl = request.getRequestURL().toString();
        return siteUrl.replace(request.getServletPath(), "");
    }
}
