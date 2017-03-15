namespace OffByOne.Ql.Interpreter.Validators.Base
{
    public interface IValidator<in T>
    {
         bool IsValid(T value);
    }
}
