using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Value
{
    interface IValue
    {
        int? AsInt();
        decimal? AsDecimal();
        bool? AsBool();
    }
}
