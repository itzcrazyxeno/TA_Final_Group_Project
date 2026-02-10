#Testing Automation Engineering - Final Group Project

#Overview
This project implements an End-to-End Test Automation Framework for the "Automation Exercise", covering both UI and API testing.

The framework is built using:
- Java
- Selenium WebDriver
- TestNG
- RestAssured
- Maven
- Allure Reporting
- Page Object Model (POM)

Target Applications:
- UI: https://automationexercise.com
- API: https://automationexercise.com/api_list

## Group Members
- Sergo Menabdishvili
- Mariam Elashvili

## Tests Responsibility Mapping

## UI Tests

**Sergo Menabdishvili**
1. Verify Home Page is visible
2. Open Products page
3. Search product
4. View product details
5. Add product to cart
6. Open Cart page
7. Remove product from cart

**Mariam Elashvili**
8. Contact Us form submission
9. Verify subscription on Home page
10. Verify Scroll Up button functionality

---

### API Tests

**Sergo Menabdishvili**
1. GET all products list
2. GET all brands list
3. GET user account details
4. POST create user account
5. PUT update user account

**Mariam Elashvili**
6. DELETE user account
7. POST search product
8. POST verify login with valid credentials
9. POST verify login with invalid credentials
10. POST logout user

## How to Run the Tests

### Run all tests
Terminal:
mvn clean test

##Generate Allure Report
mvn allure:report
mvn allure:serve
