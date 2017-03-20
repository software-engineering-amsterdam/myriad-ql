.PHONY: docs

# Initialises pipenv, setting a virtual environment and dowloading
# dependencies, including the developer dependencies.
init:
	pip install pipenv
	pipenv lock
	pipenv install --dev

# Generates the documentation using Sphinx.
docs:
	cd docs && pipenv shell -c make html
