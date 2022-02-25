import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class Suite  {
    public static void main(String[] args) {
        new TestRunner().doRun(new TestSuite(AdminTest.class, CompanyTest.class, CustomerTest.class));
    }
}
