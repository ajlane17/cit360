import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import entity.Category;
import entity.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ServletTests {


    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCategory() throws IOException, ServletException {

        // Control value
        Category testCategory = new Category();
        testCategory.setId((long)24);
        testCategory.setBudget(250.0);
        testCategory.setName("Groceries");

        // Result value
        Category resultCategory;

        String[] command = {"getCategory"};
        String[] categoryId = {"24"};

        Map params = new HashMap();
        params.put("command", command);
        params.put("categoryId", categoryId);

        when(request.getParameterMap()).thenReturn(params);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        BudgetServlet budgetServlet = new BudgetServlet();
        budgetServlet.init();
        budgetServlet.doGet(request, response);

        String result = sw.getBuffer().toString().trim();
        resultCategory = new Gson().fromJson(result, Category.class);

        Assertions.assertEquals(testCategory, resultCategory);
    }

    @Test
    public void getExpense() throws IOException, ServletException, ParseException {

        // Control value
        Expense testExpense = new Expense();
        testExpense.setId((long)19);
        testExpense.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-02"));
        testExpense.setMerchant("Walmart");
        testExpense.setAmount(54.34);
        testExpense.setCategoryId((long)1);

        // Result value
        Expense resultExpense;

        String[] command = {"getExpense"};
        String[] categoryId = {"19"};

        Map params = new HashMap();
        params.put("command", command);
        params.put("expenseId", categoryId);

        when(request.getParameterMap()).thenReturn(params);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        BudgetServlet budgetServlet = new BudgetServlet();
        budgetServlet.init();
        budgetServlet.doGet(request, response);

        String result = sw.getBuffer().toString().trim();
        resultExpense = new Gson().fromJson(result, Expense.class);

        Assertions.assertEquals(testExpense, resultExpense);
    }
}
