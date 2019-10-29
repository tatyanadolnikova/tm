package com.dolnikova.tm;

import java.util.*;

public class Test {
    private static List<Project> projects = new ArrayList<>();

    private static final String HELP = "help";
    private static final String CREATE_PROJECT = "create project";
    private static final String READ_PROJECT = "read project";
    private static final String UPDATE_PROJECT = "update project";
    private static final String DELETE_PROJECT = "delete project";

    private static final String CREATE_TASK = "create task";
    private static final String READ_TASK = "read task";
    private static final String UPDATE_TASK = "update task";
    private static final String DELETE_TASK = "delete task";
    private static final String EXIT = "exit";
    private static final String HELP_STRING = HELP + "\n"
            + CREATE_PROJECT + "\n" + READ_PROJECT + "\n" + UPDATE_PROJECT + "\n" + DELETE_PROJECT + "\n"
            + CREATE_TASK + "\n" + READ_TASK + "\n" + UPDATE_TASK + "\n" + DELETE_TASK + "\n" + EXIT;

    public static void main(String[] args) {
        System.out.println("* * * Welcome to Task Manager * * *");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            switch (input) {
                case EXIT:
                    System.exit(0);
                case HELP:
                    showHelp();
                    break;
                case CREATE_PROJECT:
                    createProject();
                    break;
                case READ_PROJECT:
                    readProject();
                    break;
                case UPDATE_PROJECT:
                    updateProject();
                    break;
                case DELETE_PROJECT:
                    deleteProject();
                    break;
                case CREATE_TASK:
                    createTask();
                    break;
                case READ_TASK:
                    readTask();
                    break;
                case UPDATE_TASK:
                    updateTask();
                    break;
                case DELETE_TASK:
                    deleteTask();
                    break;
                default:
                    System.out.println("Такой команды не существует");
                    break;
            }
        }
    }

    private static void showHelp() {
        System.out.println(HELP_STRING);
    }

    private static void createProject() {
        System.out.println("Введите название нового проекта.");
        boolean found = false;
        while (!found) {
            Scanner scanner = new Scanner(System.in);
            String projectName = scanner.nextLine();
            for (Project project : projects) {
                if (projectName.equals(project.getProjectName())) {
                    System.out.println("Проект с таким названием уже существует.");
                    found = true;
                    break;
                }
            }
            if (!found && !projectName.equals("")) {
                projects.add(new Project(projectName));
                System.out.println("Проект " + projectName + " создан.");
                break;
            }
        }
    }

    private static void readProject() {
        if (projects.isEmpty()) {
            System.out.println("Проектов нет.");
            return;
        }
        System.out.println("Введите название проекта");
        while (true) {
            Scanner projectScanner = new Scanner(System.in);
            String projectName = projectScanner.nextLine();

            if (projectName.equals(EXIT))
                break;

            boolean fileFound = false;
            boolean tasksFound = false;
            for (Project project : projects) {
                if (projectName.equals(project.getProjectName())) {
                    fileFound = true;
                    if (project.getTasks().size() != 0) tasksFound = true;
                    for (int i = 0; i < project.getTasks().size(); i++) {
                        System.out.println("#" + (i + 1) + " " + project.getTasks().get(i));
                    }
                    break;
                }
            }

            if (!fileFound)
                System.out.println("Проекта " + projectName + " не существует. Попробуйте еще раз.");
            else if (!tasksFound)
                System.out.println("В проекте " + projectName + " нет задач");
            else
                break;
        }
    }

    private static void createTask() {
        System.out.println("Выберите проект");
        while (true) {
            Scanner projectScanner = new Scanner(System.in);
            String projectName = projectScanner.nextLine();
            boolean fileFound = false;
            boolean taskCreated = false;
            for (Project project : projects) {
                if (projectName.equals(project.getProjectName())) {
                    fileFound = true;
                    System.out.println("Введите задачу: ");
                    while (true) {
                        Scanner taskScanner = new Scanner(System.in);
                        String taskText = taskScanner.nextLine();
                        if (taskText.equals(EXIT)) {
                            break;
                        } else if (!taskText.equals("")) {
                            taskCreated = true;
                            project.addTask(taskText);
                            System.out.println("Задача " + taskText + " добавлена.\nВведите exit, если больше не будете добавлять задачи");
                        }
                    }
                }
            }
            if (!fileFound) {
                System.out.println("Проекта с таким названием не существует.");
                break;
            }
            if (taskCreated)
                break;
        }
    }

    private static void updateProject() {
        System.out.println("Введите название проекта.");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String projectName = scanner.nextLine();

            boolean fileFound = false;
            for (Project project : projects) {
                if (projectName.equals(project.getProjectName())) {
                    fileFound = true;
                    System.out.println("Введите новое название проекта.");
                    while (true) {
                        Scanner newScanner = new Scanner(System.in);
                        String newProjectName = newScanner.nextLine();
                        if (!newProjectName.equals("")) {
                            project.setProjectName(newProjectName);
                            System.out.println("Проект " + newProjectName + " обновлен.");
                            break;
                        }
                    }
                }
            }
            if (!fileFound) {
                System.out.println("Проекта " + projectName + " не существует. Попробуйте еще раз.");
                break;
            }
        }
    }

    private static void deleteProject() {
        if (projects.isEmpty()) {
            System.out.println("Проектов нет.");
            return;
        }
        System.out.println("Какой проект хотите удалить?");
        Project project = selectProject();
        if (project != null) {
            projects.remove(project);
            System.out.println("Проект удален.");
        }
    }

    private static Project selectProject() {
        String projectName;
        while (true) {
            Scanner projectScanner = new Scanner(System.in);
            projectName = projectScanner.nextLine();
            if (projectName.equals(EXIT)) return null;
            for (Project project : projects) {
                if (projectName.equals(project.getProjectName()))
                    return project;
            }
            System.out.println("Проекта " + projectName + " не существует. Попробуйте еще раз.");
        }
    }

    private static void readTask() {
        System.out.println("Введите название проекта.");
        Project project = selectProject();
        if (project != null) {
            System.out.println("В проекте " + project.getProjectName() + " " + project.getTasks().size() + " задач. Введите номер задачи.");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String taskNumber = scanner.nextLine();
                if (taskNumber.equals(EXIT)) break;
                if (isNumber(taskNumber)) {
                    int num = Integer.parseInt(taskNumber);
                    System.out.println("#" + num + " " + project.readTask(num));
                } else
                    System.out.println("Некорректный ввод числа. Для выхода введите exit.");
            }
        }
    }

    private static boolean isNumber(String strNum) {
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private static void updateTask() {
        System.out.println("Введите название проекта.");
        Project project = selectProject();
        if (project != null) {
            int taskNumber = project.getTasks().size();
            System.out.println("В проекте " + project.getProjectName() + " "
                    + taskNumber + " задач. Введите номер задачи для редактирования.");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String number = scanner.nextLine();
                if (number.equals(EXIT)) break;
                if (isNumber(number)) {
                    int num = Integer.parseInt(number);
                    if (num > 0 && num <= taskNumber) {
                        while (true) {
                            System.out.println("Введите новую задачу");
                            Scanner newTaskScanner = new Scanner(System.in);
                            String newTaskText = newTaskScanner.nextLine();
                            if (!newTaskText.equals("")) {
                                project.updateTask(num, newTaskText);
                                System.out.println("Задача изменена.");
                                break;
                            }
                        }
                        break;
                    } else
                        System.out.println("Попробуйте ввести число еще раз");
                }
            }
        }
    }

    private static void deleteTask() {
        System.out.println("Введите название проекта.");
        Project project = selectProject();
        if (project != null) {
            int taskNumber = project.getTasks().size();
            System.out.println("В проекте " + taskNumber + " задач. Введите номер задачи для удаления.");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String number = scanner.nextLine();
                if (number.equals(EXIT)) break;
                if (isNumber(number)) {
                    int num = Integer.parseInt(number);
                    if (num > 0 && num <= taskNumber) {
                        project.deleteTask(num);
                        System.out.println("Задача удалена.");
                        break;
                    }
                }
            }
        }
    }
}
