# Frontend: Vue 3 Application

### Overview
This project is a wildlife management system for a natural reserve, designed to allow the administrator to manage the fauna within the reserve. The frontend was built using Vue 3 with the Composition API, JavaScript, and Tailwind CSS.

### Features
- **Admin Login:** A login page that restricts access to the system, allowing only an administrator to manage the system. Incorrect login attempts provide feedback to the user.
- **Dashboard:** Displays the total number of species currently in the reserve after successful login.
- **CRUD Operations:**
  - **Add a New Animal:** Allows the administrator to add new animals to the system.
  - **Edit an Existing Animal:** Modify the details of an existing animal.
  - **Delete an Animal:** Remove an animal from the system.
- **Animal List View:** Displays a paginated list of all registered animals with options to filter by family or country of origin.

### Technologies Used
- **Vue 3 Composition API:** For building reactive and modular components.
- **JavaScript:** Core programming language for implementing application logic.
- **Tailwind CSS:** Utility-first CSS framework for styling the application.

### Project Setup
1. **Install Dependencies:**
   ```bash
   npm install
2. **Run the Development Server:**
   ```bash
   npm run serve
3. **Build for Production:**
   ```bash
   npm run build

### Directory Structure
- **'src/'**
  - **'components/'** Vue components such as the login form, dashboard, and animal list.
  - **'views/'** Main views including the admin login and dashboard.
  - **'store/'** Pinia store for state management.
  - **'assets/'** Static assets like images and icons.
  - **'App.vue'** Root component.
  - **'main.js'** Entry point for the application.in.
