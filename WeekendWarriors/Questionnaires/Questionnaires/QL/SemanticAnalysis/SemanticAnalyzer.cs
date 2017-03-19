﻿using Questionnaires.ErrorHandling;
using Questionnaires.QL.AST;

namespace Questionnaires.QL.SemanticAnalysis
{
    public class SemanticAnalyzer
    {
        private Result Result;

        public SemanticAnalyzer(Result result)
        {
            Result = result;
        }

        public void AnalyzeForm(Form form)
        {
            AnalyzeAstNode(form);
            if (!Result.ContainsErrors())
                form.CheckDependencies(Result);
        }

        protected void AnalyzeAstNode(INode node)
        {
            QLContext Context = new QLContext();

            // Get and check question declarations
            Result.Combine(new DeclarationValidator().Analyze(node, Context));
            if (!Result.ContainsErrors()) // Only apply type checking if the declaration validator passed
            {
                var TypeCheckerResult = new TypeChecker().Analyze(node, Context);
                Result.Combine(TypeCheckerResult);
            }
        }
    }
}
