namespace OffByOne.Ql.Checker.Analyzers.CircularDependencies
{
    using MoreDotNet.Extensions.Common;

    public class Dependency
    {
        public Dependency(string startPointId, string endPointId)
        {
            this.StartPointId = startPointId;
            this.EndPointId = endPointId;
        }

        public string StartPointId { get; }

        public string EndPointId { get; }

        public override int GetHashCode()
        {
            var partialHas = this.StartPointId?.GetHashCode() ?? 0;
            return partialHas + this.EndPointId?.GetHashCode() ?? 0;
        }

        public override bool Equals(object obj)
        {
            if (obj.IsNot<Dependency>())
            {
                return false;
            }

            var dependency = obj.As<Dependency>();
            bool inEqualityCondition;

            if (this.StartPointId != null)
            {
                inEqualityCondition = this.StartPointId != dependency.StartPointId;
            }
            else
            {
                inEqualityCondition = dependency.StartPointId != null;
            }

            if (inEqualityCondition)
            {
                return false;
            }

            return this.EndPointId != null ? this.EndPointId == dependency.EndPointId : dependency.EndPointId == null;
        }

        public override string ToString()
        {
            return $"[{this.StartPointId},{this.EndPointId}]";
        }

        public bool IsTransitiveTo(Dependency supposedPair)
        {
            return this.EndPointId == supposedPair.StartPointId;
        }

        public bool IsReflexive()
        {
            return this.StartPointId == this.EndPointId;
        }
    }
}
