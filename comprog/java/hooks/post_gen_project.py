from imp import source_from_cache
import os
import shutil

source_file = "{{ cookiecutter.repo_name }}.java"

shutil.copy(source_file, "..")
os.remove("{{ cookiecutter.repo_name }}.java")
os.chdir("..")
os.rmdir("{{cookiecutter.repo_name}}")