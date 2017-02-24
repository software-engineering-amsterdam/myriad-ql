using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DSL.SemanticAnalysis.SemenaticAnalysisEvents;

namespace DSL.AST
{
    public enum QLType
    {
        Number,
        Money,
        Bool,
        String,
        None        // TODO: remove
    };

    /* For now this is basically a useless interface that has no other purpose
     * than to allow us to use a single return type for all visit functions
     * All AST nodes inherit from this interface */
    public interface INode
    {
        QLType? CheckTypes(List<QLType> parameters, QLContext context, List<ISemenaticAnalysisEvent> events);
    }
}
