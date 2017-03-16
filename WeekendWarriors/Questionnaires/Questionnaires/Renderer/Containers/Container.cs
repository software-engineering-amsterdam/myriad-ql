using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.Renderer.Containers
{
    public class Container
    {
        public string Name { get; set; }
        public List<Section> Sections { get; set; }
        public List<RunTime.Question> Questions { get; set; }

        public Container(string name)
        {
            Name = name;
            Sections = new List<Section>();
            Questions = new List<RunTime.Question>();
        }
    }
}
