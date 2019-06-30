package controller;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public interface Handler {
    void handleIt(HashMap<String, Object> data, HttpServletResponse response);
}
