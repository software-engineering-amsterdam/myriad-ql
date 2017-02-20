using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

enum Visibility
{
    Visible,
    Hidden
}

interface IQuestion
{
    string Name { get; }
    string Body { get; }
    Visibility Visibility { get; }
};

namespace DSL.Renderer
{
    interface IRenderer
    {
        void AddQuestion(IQuestion question);
        void SetValue(string name, DSL.Value.IValue value);
        void SetVisibility(string name, Visibility visibility);
    }
}
