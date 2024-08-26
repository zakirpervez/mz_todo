# MZ_TODO

## App Description
The **MZ_TODO** application is a sleek and intuitive task management tool designed to help you stay organized and productive. Whether you’re managing daily tasks, planning a project, or keeping track of important deadlines, this app provides a simple and efficient way to keep everything in order.

## Tech Stack
- **Android SDK**
- **Kotlin**
- **Clean Architecture**
- **Coroutines**
- **Kotlin Flow**
- **Dependency Injection (Hilt Dagger)**
- **Compose UI**
- **Room Database**
- **JUnit**
- **AndroidX Unit Testing**
- **Espresso**

## Clean Architecture
The app follows the Clean Architecture principles to ensure a modular and scalable design:

1. **Presentation Layer**:
    - The Presentation module represents the user interface and contains multiple composable and ViewModels. It depends on the Data and Domain modules.

2. **Domain Layer**:
    - The Domain module contains the core business logic, including repositories, entities, and use cases.

3. **Data Layer**:
    - The Data module is responsible for data management, including database interactions, repository implementations, and mapping extensions.

## Version Control
The project uses a trunk-based Git branching strategy with the following branches:
- **Main**: The production-ready branch.
- **Dev**: The development branch where ongoing work is committed.

As a solo developer, I am currently committing directly to the Dev branch and have not created separate feature branches.

### Commit Guidelines
Follow the format for commit messages:
type(scope): [commit-details-message]-[ticket/issue_number]

### Unit Test Cases
Unit tests are implemented using MockK to mock objects and other testing libraries to ensure the reliability of the code.
Test cases cover various aspects of the application's functionality and behavior.

## Screens
The app includes the following screens:

1. **Splash Screen**:
   - Displays the app name for 3 seconds during startup and navigation to TodoList Screen.
![splash_screen.png](screenshots%2Fsplash_screen.png)
2. **TodoList Screen**:
   - Shows a list of todo items and includes a floating action button to create new todo items.
![todo_list_screen.png](screenshots%2Ftodo_list_screen.png)
   
3. **Create Todo Screen**:
   - Provides a simple form to add new todo items.
   - Once user add the items it will navigate back to TodoList Screen which shows the newly added items at bottom of the list.
![create_todo_screen.png](screenshots%2Fcreate_todo_screen.png)