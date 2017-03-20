.. QL Workbench documentation master file, created by
   sphinx-quickstart on Tue Feb 14 00:31:21 2017.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to QL Workbench's documentation!
========================================

.. toctree::
   :maxdepth: 2
   :caption: Contents:

Indices and tables
------------------

* :ref:`genindex`
* :ref:`modindex`
* :ref:`search`

::

    Usage: main.py [OPTIONS] <file>

    This program generates a formulary based on QL files. It parses the
    given <file> and generates a graphical interface based on the content.
    In case it contains errors, they will be displayed in the screen.

    Options:
        --help  Show this message and exit.


QL
==

QL Lexer
--------
.. automodule:: qlworkbench.ql.lexer
    :members:

QL Parser
---------
.. automodule:: qlworkbench.ql.parser
    :members:

Type checker
------------
.. automodule:: qlworkbench.ql.ast.typechecker
    :members:

AST
---
.. automodule:: qlworkbench.ql.ast.type
    :members:

.. automodule:: qlworkbench.ql.ast.root
    :members:

.. automodule:: qlworkbench.ql.ast.node
    :members:

.. automodule:: qlworkbench.ql.ast.expression
    :members:

GUI
===
Builder
-------
.. automodule:: qlworkbench.ui.builder
    :members:

Context
-------
.. automodule:: qlworkbench.ui.context
    :members:
