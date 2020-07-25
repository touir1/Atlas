using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class PassModel
    {

        public long Id { get; set; }
        public List<String> Choix { get; set; }
        public String Libelle { get; set; }
        public String Type { get; set; }
    }
}
