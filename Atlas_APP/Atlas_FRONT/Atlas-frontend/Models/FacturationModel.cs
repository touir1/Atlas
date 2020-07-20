using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class FacturationModel
    {
        [JsonProperty("id")]
        public long? Id { get; set; }
        [JsonProperty("dateFacturation")]
        public DateTime? DateFacturation { get; set; }
        [JsonProperty("libelle")]
        public String Libelle { get; set; }
        
        [JsonProperty("montantTotale")]
        public float? MontantTotale { get; set; }
        [JsonProperty("document")]
        public String Document { get; set; }
        [JsonProperty("mission")]
        public MissionModel Mission { get; set; }
    }
}
