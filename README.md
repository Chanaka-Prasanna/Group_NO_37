# Group_NO_37

## Before anything, prepare environments to conduct test cases

1. Clone the repository at [https://github.com/Chanaka-Prasanna/Group_NO_37](https://github.com/Chanaka-Prasanna/Group_NO_37)
2. Navigate to the `UI` or `API` directory.
3. Open the project using your preferred IDE. **IntelliJ is preferred.**
4. Download Maven dependencies and load them.
5. Install Scoop if your OS is Windows, Brew if your OS is macOS, and use `sudo` if your OS is Linux.
6. Then install **Allure** - A reporting tool. Refer to the installation guide [here](https://allurereport.org/docs/install/).
7. Make sure to add this in `UI/testing.xml`  (UI only)

```bash
<listeners>
    <listener class-name="io.qameta.allure.testng.AllureTestNg"/>
</listeners>
```
8. Make sure to add this in `API/pom.xml` (API only)
```bash
   <build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M8</version>
            <configuration>
                <systemPropertyVariables>
                    <allure.results.directory>target/allure-results</allure.results.directory>
                </systemPropertyVariables>
            </configuration>
        </plugin>
    </plugins>
</build>
```

9. Run test cases using runner class
10. Generate Allure report using this command

```bash
allure serve target/allure-results

```

## Environment Variables

In the `UI/.env` file, set the following:

```env
BASE_URL=https://cargillsonline.com/
```

## Allure properties

In the `UI/src/test/resources/allure.properties` file, set the following:

```env
allure.results.directory=target/allure-results
```

## Conducting UI Testing and API Testing

### UI Testing

Tools/Frameworks:

- Cucumber BDD Framework
- TestNG
- Selenium (WebDriver tool)

### API Testing

Tools/Frameworks:

- Cucumber BDD Framework
- TestNG
- Rest Assured

### Testing Site: [cargils online](https://cargillsonline.com/)

### Allure Report : [click here](https://chanaka-prasanna.github.io/Group_NO_37/report)
