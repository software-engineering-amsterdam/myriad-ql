using Questionnaires.ErrorHandling;
using Questionnaires.QL.AST;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QL.SemanticAnalysis
{
    /* What we would really need here is some relational algebra. The dependecies are simply relations and the
     * transitive closure of those relations could tell us if there are cyclic dependencies. Unfortunately
     * we could not find a proper library for relational algebra */
    class DependencyChecker
    {
        Dictionary<string, HashSet<string>> Dependencies = new Dictionary<string, HashSet<string>>();

        public void AddDependencies(Question dependee, HashSet<Identifier> dependencies)
        {
            /* Convert from AST nodes to strings for convenience */
            if (!Dependencies.ContainsKey(dependee.Identifier))
                Dependencies[dependee.Identifier] = new HashSet<string>();

            foreach(var dependecy in dependencies)
            {
                Dependencies[dependee.Identifier].Add(dependecy.Name);
            }            
        }

        public void Check(Result result)
        {
            var dependees = Dependencies.Keys;
            foreach (var dependee in dependees)
            {
                List<string> dependencies = new List<string>();
                dependencies.Add(dependee);
                if (DependantOn(dependencies))
                {
                    string message = CreateErrorMessage(dependencies);

                    result.AddEvent(new Error(message));
                }
            }
        }

        private static string CreateErrorMessage(List<string> dependencies)
        {
            string message = "Cyclic dependency found between questions ";
            for (var index = 0; index < dependencies.Count; index++)
            {
                string connector = ",";
                if (index == dependencies.Count - 2)
                    connector = "and";
                if (index == dependencies.Count - 1)
                    connector = "";

                message = string.Format("{0} {1}{2} ", message, dependencies[index], connector);
            }

            return message;
        }

        public bool DependantOn(List<string> dependencyTrail)
        {
            Debug.Assert(dependencyTrail.Count > 0);

            var root = dependencyTrail.First();
            var leaf = dependencyTrail.Last();

            // If the last dependency in the trail has no dependencies, there is no cyclic dependency
            if (!Dependencies.ContainsKey(leaf))
                return false;

            // If the dependencies of the last dependency in the trail contains the root node, we have a cyclic dependency
            var leafDependencies = Dependencies[leaf];
            if (leafDependencies.Contains(root))
                return true;

            // If we get here, the last dependency of the trail has dependencies, but none of them are the root node. We need
            // to investigate further
            foreach(var dependency in leafDependencies)
            {
                List<string> copy = new List<string>(leafDependencies);
                copy.Add(dependency);
                if (DependantOn(copy))
                {
                    dependencyTrail.Clear();
                    dependencyTrail.AddRange(copy);
                    return true;
                }
            }
            return false;
        }
    }
}
