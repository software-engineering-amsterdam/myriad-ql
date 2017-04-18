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


AST
---
.. automodule:: qlworkbench.ql.ast.type
    :members:

.. automodule:: qlworkbench.ql.ast.node
    :members:

.. automodule:: qlworkbench.ql.ast.expression
    :members:

Checkers
========

.. automodule:: qlworkbench.typechecker.checker
    :members:

.. automodule:: qlworkbench.typechecker.lexer_checker
    :members:

.. automodule:: qlworkbench.typechecker.parser_checker
    :members:

.. automodule:: qlworkbench.typechecker.ql_checker
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

Event manager
-------------
.. automodule:: qlworkbench.ui.event_manager
    :members:
