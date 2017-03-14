namespace OffByOne.Ql.Checker.Analyzers.CircularDependencies
{
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    public class CircularDependencyChecker
    {
        private readonly ISet<Dependency> dependencySet;

        public CircularDependencyChecker()
        {
            this.dependencySet = new HashSet<Dependency>();
        }

        public IEnumerable<Dependency> CircularDependencies => this.GenerateTransitiveClosure().Where(x => x.IsReflexive());

        public void AddDependency(Dependency dependency)
        {
            this.dependencySet.Add(dependency);
        }

        public void AddDependencies(IEnumerable<Dependency> dependency)
        {
            this.dependencySet.AddRange(dependency);
        }

        public void AddDependencies(IEnumerable<string> startPoints, IEnumerable<string> endPoints)
        {
            foreach (var startPoint in startPoints)
            {
                foreach (var endPoint in endPoints)
                {
                    this.dependencySet.Add(new Dependency(startPoint, endPoint));
                }
            }
        }

        private IEnumerable<Dependency> GenerateTransitiveClosure()
        {
            ISet<Dependency> transitiveClosure = new HashSet<Dependency>();
            transitiveClosure.AddRange(this.dependencySet);

            while (true)
            {
                var newEdges = this.GenerateEdges(transitiveClosure);

                newEdges.AddRange(transitiveClosure);

                if (newEdges.SequenceEqual(transitiveClosure))
                {
                    break;
                }

                transitiveClosure = newEdges;
            }

            return transitiveClosure;
        }

        private ISet<Dependency> GenerateEdges(ISet<Dependency> dependencies)
        {
            var result = new HashSet<Dependency>();

            foreach (var dependency in dependencies)
            {
                foreach (var testDependency in dependencies)
                {
                    if (dependency.IsTransitiveTo(testDependency))
                    {
                        result.Add(new Dependency(
                             dependency.StartPointId,
                             testDependency.EndPointId));
                    }
                }
            }

            return result;
        }
    }
}
