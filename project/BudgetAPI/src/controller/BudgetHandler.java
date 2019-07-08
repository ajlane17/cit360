package controller;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public interface BudgetHandler {
    void execute(HashMap<String, Object> data, HttpServletResponse response);
}
