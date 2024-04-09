[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/bl6zRdaa)
# Chess Template


This is the template for your chess project. You will use this template to create your own chess game.

This template comes with configurations for:

- Static code analysis 
- Coverage

It supports both Java code and Kotlin code

## Where should you include your code?

Your code should be included in the `engine/src/main/java` or `engine/src/main/kotlin` directory.

## How to build the project?

You can build the project using the following command:

```./gradlew build```

## Testing

### Requirements

This project depends on a package published in the GitHub Packages Registry. In order to download it a GitHub token must be used.
Instruction on how to create a GitHub personal token are [here](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token). 
Once created the following environment variables must be defined:
* GITHUB_USER
* GITHUB_TOKEN
Or in a file `gradle.properties` the following values must be defined: 
* github.user 
* github.token
