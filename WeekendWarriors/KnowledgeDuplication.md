# How to prevent duplication of knowledge while maintaining code quality

We have this issue in a couple of locations, but to illustrate we'll use operations on a type.

Let's say that we have decided to put the operation into our type classes. In the example we have a language that has three types (int, float, bool) and two operators (plus, equals)
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

Now we need two things
1) We need to know which operations are supported on which types
2) We need to be able to evaluate the outcome for the supported operations

Let's start with the evaluation. We would need something like this:

```c#
public Class Type
{
	
}

public Class Integer : Type
{
	public Type Plus(Integer rhsOperand)
	{
		return Integer(this.value + rhsOperand.value);
	}
	
	public Type Plus(Float rhsOperand)
	{
		return Float(this.value + rhsOperand.value);
	}
	
	public Bool Equals(Integer rhsOperand)
	{
		return this.value == rhsOperand.value;
	}
}

public Class Float : Type
{
	public Type Plus(Integer rhsOperand)
	{
		return Float(this.value + rhsOperand.value);
	}
	
	public Type Plus(Float rhsOperand)
	{
		return Float(this.value + rhsOperand.value);
	}
	
	public Bool Equals(Float rhsOperand)
	{
		return this.value == rhsOperand.value;
	}
}

public Class Bool : Type
{
	public Bool Equals(Bool rhsOperand)
	{
		return this.value == rhsOperand.value;
	}
}
```

All supported operations are defined in the type classes. Because we use C# we don't need to apply the visitor pattern. If we want to evaluate the result of an expression
we would simple do something like this:

```
var lhs = new Integer(5);
var rhs = new Float(1.23);
var result = lhs.Plus((dynamic)rhs);
```

Which will of course fail if the operation is not defined, so we need to make sure that we know which operations are supported. We trust Beck and Martin, so we want to keep the code 
that code close to the evaluation code:

```c#
public Class Type
{
	public virtual bool PlusOperationSupported(Type rhsOperand)
	{
		return false;
	}
	
	public virtual bool EqualsOperationSupported(Type rhsOperand)
	{
		return false;
	}
}

public Class Integer : Type
{
	public override bool PlusOperationSupported(Integer rhsOperand)
	{
		return true;
	}
	
	public override bool PlusOperationSupported(Float rhsOperand)
	{
		return true;
	}
	
	public override bool EqualsOperationSupported(Integer rhsOperand)
	{
		return true;
	}

	public  Type Plus(Integer rhsOperand)
	{
		return Integer(this.value + rhsOperand.value);
	}

	public Type Plus(Integer rhsOperand)
	{
		return Integer(this.value + rhsOperand.value);
	}
	
	public Type Plus(Float rhsOperand)
	{
		return Float(this.value + rhsOperand.value);
	}
	
	public Bool Equals(Integer rhsOperand)
	{
		return this.value == rhsOperand.value;
	}
}

public Class Float : Type
{
	public override bool PlusOperationSupported(Integer rhsOperand)
	{
		return true;
	}
	
	public override bool PlusOperationSupported(Float rhsOperand)
	{
		return true;
	}
	
	public override bool EqualsOperationSupported(Integer rhsOperand)
	{
		return true;
	}
	
	public Type Plus(Integer rhsOperand)
	{
		return Float(this.value + rhsOperand.value);
	}
	
	public Type Plus(Float rhsOperand)
	{
		return Float(this.value + rhsOperand.value);
	}
	
	public Bool Equals(Float rhsOperand)
	{
		return this.value == rhsOperand.value;
	}
}

public Class Bool : Type
{
	public override bool EqualsOperationSupported(Integer rhsOperand)
	{
		return true;
	}
	
	public Bool Equals(Bool rhsOperand)
	{
		return this.value == rhsOperand.value;
	}
}
```

We use the base class for the premise that none of the operations is supported. The child classes override those operations that they support.
The method for type checking shown here is overly simplified and can be enhanced in a number of ways. The important propery of this method (and many like it) is that 
there are two locations that contain largely the same information:
* Which argument types are supported for an operation (and what the return type of the operation is)
* How to calculate the result for these operations.

Those two sources of information are incredicbly tightly coupled: Defining the evaluation of an operations that is not 'marked' as supported is useless because such an operation
in the code will be flagged as erroneous by the type checker. Failure to implement an evaluation for a type that is 'flagged' as supported may lead to run time failure.
It is clear that a change in one location should always lead to a change in the other location. We have somthing akin to a code clone. Maybe a knowledge clone. This is clearly a smell.

The following code is an attempt to put the knowledge at a single location instead

```c#
public Class Type
{
	public virtual Type Plus(Integer rhsOperand)
	{
		throw new NotSupportedException();
	}
	
	public virtual Type Plus(Float rhsOperand)
	{
		throw new NotSupportedException();
	}
	
	public virtual Type Plus(String rhsOperand)
	{
		throw new NotSupportedException();
	}
	
	public virtual Bool Equals(Integer rhsOperand)
	{
		throw new NotSupportedException();
	}
	
	public virtual Bool Equals(Float rhsOperand)
	{
		throw new NotSupportedException();
	}
	
	public virtual Bool Equals(String rhsOperand)
	{
		throw new NotSupportedException();
	}
}

public Class Integer : Type
{

	public override Type Plus(Integer rhsOperand)
	{
		return Integer(this.value + rhsOperand.value);
	}

	public override Type Plus(Integer rhsOperand)
	{
		return Integer(this.value + rhsOperand.value);
	}
	
	public override Type Plus(Float rhsOperand)
	{
		return Float(this.value + rhsOperand.value);
	}
	
	public override Bool Equals(Integer rhsOperand)
	{
		return this.value == rhsOperand.value;
	}
}

public Class Float : Type
{	
	public override Type Plus(Integer rhsOperand)
	{
		return Float(this.value + rhsOperand.value);
	}
	
	public override Type Plus(Float rhsOperand)
	{
		return Float(this.value + rhsOperand.value);
	}
	
	public override Bool Equals(Float rhsOperand)
	{
		return this.value == rhsOperand.value;
	}
}

public Class Bool : Type
{	
	public override Bool Equals(Bool rhsOperand)
	{
		return this.value == rhsOperand.value;
	}
}
```

The idea is that we defer type checking to the actual evaluation. If the function throws a NotSupportedException that means that an operation of this type is not supported.
Type checking would work something like this:
```
bool supported = false;
var lhs = new Integer(5);
var rhs = new Float(1.23);
try
{
	var result = lhs.Plus((dynamic)rhs);
	supported = true;
}
catch(NotSupportedException)
{
	supported = false;
}
```

A similar approach could be taken without the NotSupportedException exception being thrown from the base class as failure to 'bind' a dynamic call to the right method at runtime 
leads to a RuntimeBinderException. However, for both methods there are some draw backs:
* The type checker heavily relies on the type of exception that is thrown. If someone was to change that, the type checker would cease to work.
* Conceptually it is wrong to use exceptions for type checking as a failure in the type checker is not really an exception; it is normal.
* Althouh not a real reason, looking at the construction of setting a boolean based on whether or not an exception is throw is a bit cringeworthy.

A third method that does not require the use of exceptions uses function objects. In this case, instead of actually evaluating the operation, we return a function object that, when called,
performs the evaluation. Invalid operations will lead to a null function object:

```c#
public Class Type
{
	public virtual Func<Type> Plus(Integer rhsOperand)
	{
		return null;
	}
	
	public virtual Func<Type> Plus(Float rhsOperand)
	{
		return null;
	}
	
	public virtual Func<Type> Plus(String rhsOperand)
	{
		return null;
	}
	
	public virtual Func<Bool> Equals(Integer rhsOperand)
	{
		return null;
	}
	
	public virtual Func<Bool> Equals(Float rhsOperand)
	{
		return null;
	}
	
	public virtual Func<Bool> Equals(String rhsOperand)
	{
		return null;
	}
}

public Class Integer : Type
{

	public override Func<Type> Plus(Integer rhsOperand)
	{
		return () => { return Integer(this.value + rhsOperand.value); };
	}

	public override Func<Type> Plus(Integer rhsOperand)
	{
		return () => { return Integer(this.value + rhsOperand.value); };
	}
	
	public override Func<Type> Plus(Float rhsOperand)
	{
		return () => { return Float(this.value + rhsOperand.value); };
	}
	
	public override Func<Bool> Equals(Integer rhsOperand)
	{
		return () => { return this.value == rhsOperand.value; };
	}
}

public Class Float : Type
{	
	public override Func<Type> Plus(Integer rhsOperand)
	{
		return () => { return Float(this.value + rhsOperand.value); };
	}
	
	public override Func<Type> Plus(Float rhsOperand)
	{
		return () => { return Float(this.value + rhsOperand.value); };
	}
	
	public override Func<Bool> Equals(Float rhsOperand)
	{
		return () => { return this.value == rhsOperand.value; };
	}
}

public Class Bool : Type
{	
	public override Func<Bool> Equals(Bool rhsOperand)
	{
		return () => { return this.value == rhsOperand.value; };
	}
}
```

Type checking in this excample would look something like this:

```
var lhs = new Integer(5);
var rhs = new Float(1.23);

var evluationFunction = lhs.Plus((dynamic)rhs);
bool supported = !(evluationFunction == null);
```

The fact that we don't misuse exceptions makes this code a bit cleaner. The downside is that it may be a bit harder to understand. Although proper naming of the methods could be 
a huge help here.
