# How to prevent duplication of knowledge while maintaining code quality

We have this issue in a couple of locations, but to illustrate we'll use operations on a type.

Let's say that we have decided to put the operation into our type classes. In the example we have a language that has three types (int, float, bool) and three operators (plus, minus, equals)
We have something like this

```c#
public Class Type
{
}

public Class Integer : Type
{
}

public Class Float : Type
{
}

public Class Bool : Type
{
}
```