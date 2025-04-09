import java.util.ArrayList;
import java.util.Scanner;

class StudentScore {
    private String name;
    private String studentId;
    private String course;
    private double score;

    public StudentScore(String name, String studentId, String course, double score) {
        this.name = name;
        this.studentId = studentId;
        this.course = course;
        this.score = score;
    }

    // Getters
    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public String getCourse() { return course; }
    public double getScore() { return score; }
}

public class ScoreManagementSystem {
    private static ArrayList<StudentScore> records = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();  // 修正：添加括号以正确调用方法
            try {
                switch (choice) {
                    case "1" -> addRecord();
                    case "2" -> queryRecord();
                    case "3" -> statistics();
                    case "4" -> System.exit(0);  // 退出程序
                    default -> System.out.println("无效的选择，请重新输入！");
                }
            } catch (Exception e) {
                System.out.println("发生错误: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("欢迎使用学生成绩管理系统");
        System.out.println("请选择操作：");
        System.out.println("1. 记录学生成绩");
        System.out.println("2. 查询学生成绩");
        System.out.println("3. 统计课程成绩");
        System.out.println("4. 退出系统");
        System.out.print("请输入选项序号：");
    }

    private static void addRecord() {
        System.out.println("===== 记录学生成绩 =====");
        System.out.print("请输入学生姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入学生学号：");
        String studentId = scanner.nextLine();
        System.out.print("请输入课程名称：");
        String course = scanner.nextLine();
        System.out.print("请输入成绩（0-100）：");
        double score = scanner.nextDouble();
        scanner.nextLine();  // 清除缓冲区中的换行符
        
        if (score < 0 || score > 100) {
            System.out.println("成绩超出合法范围，请输入0到100之间的数字！");
            return;
        }
        
        // 检查重复学号
        for (StudentScore record : records) {
            if (record.getStudentId().equals(studentId)) {
                System.out.println("该学号已存在，请检查后重试！");
                return;
            }
        }
        
        records.add(new StudentScore(name, studentId, course, score));
        System.out.println("成绩已成功记录！");
    }

    private static void queryRecord() {
        System.out.println("===== 查询学生成绩 =====");
        System.out.println("请选择查询方式：");
        System.out.println("1. 按学生姓名查询");
        System.out.println("2. 按学生学号查询");
        System.out.println("3. 按课程名称查询");
        System.out.print("请输入选项序号：");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> queryByName();
            case "2" -> queryByStudentId();
            case "3" -> queryByCourse();
            default -> System.out.println("无效的选择，请重新输入！");
        }
    }

    private static void queryByName() {
        System.out.print("请输入学生姓名：");
        String name = scanner.nextLine().trim();
        boolean found = false;
        for (StudentScore record : records) {
            if (record.getName().equals(name)) {
                System.out.println("姓名：" + record.getName() + ", 学号：" + record.getStudentId() + ", 课程：" + record.getCourse() + ", 成绩：" + record.getScore());
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到该学生的成绩记录！");
        }
    }

    private static void queryByStudentId() {
        System.out.print("请输入学生学号：");
        String studentId = scanner.nextLine().trim();
        boolean found = false;
        for (StudentScore record : records) {
            if (record.getStudentId().equals(studentId)) {
                System.out.println("姓名：" + record.getName() + ", 学号：" + record.getStudentId() + ", 课程：" + record.getCourse() + ", 成绩：" + record.getScore());
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到该学生的成绩记录！");
        }
    }

    private static void queryByCourse() {
        System.out.print("请输入课程名称：");
        String course = scanner.nextLine().trim();
        boolean found = false;
        for (StudentScore record : records) {
            if (record.getCourse().equals(course)) {
                System.out.println("姓名：" + record.getName() + ", 学号：" + record.getStudentId() + ", 课程：" + record.getCourse() + ", 成绩：" + record.getScore());
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到该课程的成绩记录！");
        }
    }

    private static void statistics() {
        System.out.println("===== 统计课程成绩 =====");
        System.out.print("请输入课程名称：");
        String course = scanner.nextLine().trim();

        double sum = 0;
        double max = -1;
        double min = 101;
        int count = 0;

        for (StudentScore record : records) {
            if (record.getCourse().equals(course)) {
                double score = record.getScore();
                sum += score;
                if (score > max) {
                    max = score;
                }
                if (score < min) {
                    min = score;
                }
                count++;
            }
        }

        if (count == 0) {
            System.out.println("未找到该课程的成绩记录！");
            return;
        }

        double average = sum / count;
        System.out.printf("课程：%s\n", course);
        System.out.printf("平均分：%.2f\n", average);
        System.out.printf("最高分：%.2f\n", max);
        System.out.printf("最低分：%.2f\n", min);
    }
}