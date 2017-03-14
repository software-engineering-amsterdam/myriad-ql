namespace OffByOne.Ql.Checker.Analyzers.CircularDependency
{
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.Statements;

    public class DependencySet
    {
        private IDictionary<string, Dependency> dependencyMap;

        public DependencySet()
        {
            this.dependencyMap = new Dictionary<string, Dependency>();
        }

        public IEnumerable<Dependency> Dependencies => this.dependencyMap.Select(x => x.Value);

        public void Add(QuestionStatement statement)
        {
        }
    }
}
