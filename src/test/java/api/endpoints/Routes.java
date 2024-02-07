package api.endpoints;

/**
 * Create user(Post) : http://10.1.0.163:8039/api/v1.0/members
 * Update user(Patch) : http://10.1.0.163:8039/api/v1.0/members/{uuid}
 * Get user(Get) : http://10.1.0.163:8039/api/v1.0/members/{uuid}
 * Routes for maintain all the endpoints URL
 */
public class Routes {

    public static String base_url = "http://10.1.0.163:8039/api/v1.0/members";
    public static String update_user = base_url + "/{uuid}";
    public static String get_user = base_url + "/{uuid}";
}
