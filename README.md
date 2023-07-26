# Temple

Starter cookiecutter templates for quickly spinning up projects.

## Usage

Just use `cookiecutter` to grab the template. You can use the shortcut `gh:sbaldrich/temple` to refer to this repository instead of cloning it.
Check the [cookiecutter.io](https://cookiecutter.readthedocs.io/) project site for details on how to install or use cookiecutter.

```sh
# Grab the jersey-rest-grpc (wisdom) template
cookiecutter gh:sbaldrich/temple --directory=java/jersey-rest-grpc
```

### Templates

The structure of this section attempts to mimic closely the structure of the templates in the directories, so it should be simple to grab the template using the `--directory` argument for cookiecutter.

* java
  * jersey-rest-grpc: a simple service that grabs zen quotes from the github zen api. Has GRPC and REST endpoints.
  * spring-user-signup-api: **[alpha]** a super rough spring-boot + spring-security API that supports user signup and login.
  * spring-starter: A simple spring-boot starter service with basic support for gRPC.
* comprog
  * java: comprog template with a `ContestIO` class to help with input/output when solving problems using java. Can use both stdin/out and text files.