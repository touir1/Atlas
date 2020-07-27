using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class CustomModel
    {
        [JsonProperty("semaine")]
        public int Semaine  { get; set; }
        [JsonProperty("mois")]
        public int Mois { get; set; }
        [JsonProperty("annee")]
        public int Annee { get; set; }
        [JsonProperty("rapports")]
        public IEnumerable<RapportModel> Rapports { get; set; }


    }
}
