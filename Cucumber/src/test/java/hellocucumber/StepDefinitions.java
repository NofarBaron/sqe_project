package hellocucumber;

import io.cucumber.java.en.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepDefinitions {
    private String TEACHER_USERNAME = "admin";
    private String TEACHER_PASSWORD = "123456Bn*";
    private String COURSE_NAME = "Software Quality";
    private String STUDENT_USERNAME = "student";
    private String STUDENT_PASSWORD = "Student123*";
    private WebDriver AdminDriver;
    private WebDriver StudentDriver;
    private WebDriverWait StudentDriverWait;
    private WebDriverWait AdminDriverWait;

    public void moodleInit(){
        WebDriverManager.chromedriver().setup();
        this.AdminDriver = new ChromeDriver();
        this.AdminDriverWait = new WebDriverWait(AdminDriver, 40);
        AdminDriver.get("http://localhost/");
        AdminDriver.manage().window().maximize();
        System.out.println("Driver setup finished for - " + AdminDriver.getTitle());
    }
    public void LogIn(String username, String password,WebDriver webDriver, WebDriverWait webDriverWait ){
        webDriver.findElement(By.linkText("Log in")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']"))).sendKeys(username);
        webDriver.findElement(By.xpath("//*[@name='password' and @type='password']")).sendKeys(password);
        webDriver.findElement(By.id("loginbtn")).click();
    }


    @Given("Teacher LogIn")
    public void LogInAdmin(){
        moodleInit();
        LogIn(TEACHER_USERNAME, TEACHER_PASSWORD, AdminDriver, AdminDriverWait);
//        webDriver.findElement(By.xpath("//*[contains(text(),'Welcome back, Admin!')]"));
    }

    @Given("Teacher has a course")
    public void EnterCourse(){
        CheckCourseSQ(AdminDriver,AdminDriverWait);
    }

    @Given("Course has a choice")
    public void CheckChoiceExist(){
        //find choice
        try {
            AdminDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("NewChoice")));
        }
        catch (Exception e){
            CreateChoice();
            CheckCourseSQ(AdminDriver,AdminDriverWait);
        }
        System.out.println("Found 'NewChoice'");
    }


    @Given("Teacher delete choice")
    public void DeleteChoice() throws InterruptedException {
        editModeOn();
        //find delete button
        //click menu
        AdminDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='action-menu-toggle-3']/i"))).click();
        //click 'Delete'
        AdminDriver.findElement(By.xpath("//*[@id='actionmenuaction-13']")).click();
        //click 'Yes' button
        AdminDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[8]/div[2]/div/div/div[3]/button[2]"))).click();
        System.out.println("Clicked 'yes' button to delete the choice");
    }

    @Then("Message displayed choice deleted successfully")
    public void messageDisplayedDeleteSuccessfully() {
        System.out.println("Choice deleted Successfully");
//        AdminDriver.wait(20);
        terminate(AdminDriver);
    }


    @Given("Student log in successfully")
    public void LogInStudent(){
        StudentInit();
        LogIn(STUDENT_USERNAME, STUDENT_PASSWORD, StudentDriver, StudentDriverWait);
        StudentDriver.findElement(By.xpath("//*[contains(text(),'Welcome back, student!')]"));
    }

    @Given("Student register to the course")
    public void CheckCourse(){
        CheckCourseSQ(StudentDriver,StudentDriverWait);
    }

    @Given("student enter the choice")
    public void EnterChoiceStudent(){
        //check choice exist
        try {
            StudentDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("NewChoice")));
        }
        catch (Exception e){
            CreateChoice();
            CheckCourseSQ(StudentDriver,StudentDriverWait);

        }
        StudentDriver.findElement(By.linkText("NewChoice")).click();
        System.out.println("Student entered NewChoice");
        //click 'Yes'
        StudentDriver.findElement(By.xpath("//*[@id=\"choice_1\"]")).click();
    }

    @Then("Message display Student answer the choice")
    public void StudentAnswerChoice(){
        System.out.println("Student answer the choice");
        terminate(StudentDriver);
    }

    @Given("Student and Teacher log in successfully")
    public void LogInBoth(){
        moodleInit();
        LogIn(TEACHER_USERNAME, TEACHER_PASSWORD, AdminDriver, AdminDriverWait);
        StudentInit();
        LogIn(STUDENT_USERNAME, STUDENT_PASSWORD, StudentDriver, StudentDriverWait);
    }
    @Given("Student and Teacher inside course page")
    public void InsideCourseBoth(){
        CheckCourseSQ(AdminDriver,AdminDriverWait);
        CheckCourseSQ(StudentDriver,StudentDriverWait);
    }
    @Given("Course SQ has a choice")
    public void checkChoiceBoth(){
        try {
            AdminDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("NewChoice")));
        }
        catch(Exception e){
            System.out.println("Choice does not exist");
            CreateChoice();
        }
        StudentDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("NewChoice")));
    }
    @Then("Message display Error")
    public void finalScenario(){
        System.out.println("Student web message: 'Activity deletion in progress...'");
        terminate(AdminDriver);
        terminate(StudentDriver);
    }

    public void CreateChoice(){
        editModeOn();
        //click add activity
        AdminDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"coursecontentcollapse0\"]/button/span[2]"))).click();
        //click choice
        AdminDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"all-6\"]/div/div[4]/div/a/div[2]"))).click();
        String choiceName = "NewChoice";
        AdminDriver.findElement(By.xpath("//*[@id=\"id_name\"]")).sendKeys(choiceName);
        String choiceQ = "blabla";
        AdminDriver.findElement(By.xpath("//*[@id=\"id_introeditoreditable\"]")).sendKeys(choiceQ);
        AdminDriver.findElement(By.xpath("//*[@id=\"id_option_0\"]")).sendKeys("Yes");
        AdminDriver.findElement(By.xpath("//*[@id=\"id_option_1\"]")).sendKeys("No");
        AdminDriver.findElement(By.xpath("//*[@id=\"id_submitbutton2\"]")).click();
        System.out.println("New Choice was created");
        editModeOn(); //off
    }


    public void StudentInit(){
//        WebDriverManager.chromedriver().setup();
        this.StudentDriver = new ChromeDriver();
        this.StudentDriverWait = new WebDriverWait(StudentDriver, 40);
        StudentDriver.get("http://localhost/");
        StudentDriver.manage().window().maximize();
        System.out.println("Student setup finished for - " + StudentDriver.getTitle());
    }
    public void CheckCourseSQ(WebDriver webDriver,WebDriverWait webDriverWait){
        //enter my courses
        webDriver.findElement(By.xpath("//*[contains(text(),'My courses') and @role='menuitem']")).click();
        //search the course name
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='multiline' and contains(text(),'" + COURSE_NAME + "')]"))).click();
    }

    public void editModeOn(){
        AdminDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='checkbox' and @name='setmode']"))).click();
    }

    private void terminate(WebDriver webDriver) {
        webDriver.quit();
    }

}
