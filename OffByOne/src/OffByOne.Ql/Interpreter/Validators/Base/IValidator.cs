namespace OffByOne.Ql.Interpreter.Validators.Base
{
    public interface IValidator<T>
    {
         bool IsValid(T value);
    }
}
