from imp import source_from_cache
import os
import shutil

source_file = "{{ cookiecutter.classname }}.java"

shutil.copy(source_file, "..")
os.remove("{{ cookiecutter.classname }}.java")
os.chdir("..")
os.rmdir("{{cookiecutter.classname}}")