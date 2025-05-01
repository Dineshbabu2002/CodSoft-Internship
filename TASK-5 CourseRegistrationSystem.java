import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity;
    int enrolled;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public boolean hasSlot() {
        return enrolled < capacity;
    }

    public void enrollStudent() {
        enrolled++;
    }

    public void dropStudent() {
        enrolled--;
    }

    @Override
    public String toString() {
        return code + " - " + title + "\n" +
               "Description: " + description + "\n" +
               "Schedule: " + schedule + "\n" +
               "Slots: " + (capacity - enrolled) + "/" + capacity;
    }
}

class Student {
    String id, name;
    List<Course> registeredCourses = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.enrollStudent();
    }

    public void removeCourse(Course course) {
        registeredCourses.remove(course);
        course.dropStudent();
    }

    public void listCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            for (Course c : registeredCourses) {
                System.out.println("- " + c.code + ": " + c.title);
            }
        }
    }
}

public class CourseRegistrationSystem {
    static List<Course> courses = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = new Student(id, name);
        int choice;

        do {
            System.out.println("\n=== Student Course Registration System ===");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> displayCourses();
                case 2 -> registerCourse(student);
                case 3 -> dropCourse(student);
                case 4 -> student.listCourses();
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 5);
    }

    static void initializeCourses() {
        courses.add(new Course("M1", "Intro to Mathematics", "Algebra and Algorithms", 3, "Mon-Wed 10:00-11:30"));
        courses.add(new Course("CN1", "Computer Networks", "Intro to Computer Networks", 2, "Tue-Thu 12:00-13:30"));
        courses.add(new Course("OS1", "Operating Systems", "Operating Systems conepts", 2, "Fri 10:00-12:00"));
    }

    static void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println("\n" + course);
        }
    }

    static void registerCourse(Student student) {
        displayCourses();
        System.out.print("\nEnter Course Code to register: ");
        String code = scanner.nextLine();

        Course course = findCourse(code);
        if (course != null) {
            if (course.hasSlot()) {
                if (student.registeredCourses.contains(course)) {
                    System.out.println("Already registered.");
                } else {
                    student.registerCourse(course);
                    System.out.println("Registered successfully!");
                }
            } else {
                System.out.println("No available slots.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    static void dropCourse(Student student) {
        System.out.println("\nRegistered Courses:");
        student.listCourses();
        System.out.print("Enter Course Code to drop: ");
        String code = scanner.nextLine();

        Course course = findCourse(code);
        if (course != null && student.registeredCourses.contains(course)) {
            student.removeCourse(course);
            System.out.println("Course dropped.");
        } else {
            System.out.println("You are not registered in this course.");
        }
    }

    static Course findCourse(String code) {
        for (Course course : courses) {
            if (course.code.equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}
