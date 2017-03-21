export TOPDIR := $(shell pwd)

HOST_PYBIN=$(shell which python3)
PYVER_MAJOR=$(shell $(HOST_PYBIN) -V 2>&1 | cut -d ' ' -f 2 | cut -d . -f 1)
PYVER_MINOR=$(shell $(HOST_PYBIN) -V 2>&1 | cut -d ' ' -f 2 | cut -d . -f 2)

export OUTDIR = $(TOPDIR)/out
export HOST_OUT_INSTALL = $(TOPDIR)/host
export HOST_OUT_INSTALL_BIN = $(HOST_OUT_INSTALL)/usr/bin
export PYBIN=$(HOST_OUT_INSTALL_BIN)/python

HOST_OUT_PYTHON_INSTALL=$(HOST_OUT_INSTALL)/usr/lib/python$(PYVER_MAJOR).$(PYVER_MINOR)
HOST_VIRTUALENV_DIR=$(HOST_OUT_PYTHON_INSTALL)/site-packages

HOST_VIRTUALENV = $(HOST_VIRTUALENV_DIR)/.dirstamp

.PHONY: install_virtualenv

install_virtualenv:
	virtualenv --python=python$(PYVER_MAJOR).$(PYVER_MINOR) $(HOST_OUT_INSTALL)/usr
	touch $(HOST_VIRTUALENV)

	$(HOST_OUT_INSTALL)/usr/bin/pip install pyparsing
	$(HOST_OUT_INSTALL)/usr/bin/pip install argparse

run: 
	$(HOST_OUT_INSTALL)/usr/bin/python src/app.py

clean: FORCE
	rm -Rf $(HOST_OUT_INSTALL)

$(OUTDIR):
	mkdir -p $@

FORCE:
