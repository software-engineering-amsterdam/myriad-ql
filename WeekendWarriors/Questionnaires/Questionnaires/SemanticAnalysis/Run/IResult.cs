using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis.Run
{
    public interface IResult
    {
        bool IsError();
        void AddEvent(SemenaticAnalysisEvents.ISemenaticAnalysisEvent analysisEvent);
        List<SemenaticAnalysisEvents.ISemenaticAnalysisEvent> Events { get; }
        void Combine(IResult result);
    }
}
