using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Atlas_frontend.Models
{
    public class RubriqueModel
    {
        [JsonProperty("id")]
        public long Id { get; set; }
        [JsonProperty("titre")]
        public String Titre { get; set; }
        [JsonProperty("estimation")]
        public float Estimation { get; set; }
        [JsonProperty("projet")]
        public ProjetModel Projet { get; set; }
        [JsonProperty("rapports")]
        public List<RapportModel> Rapports { get; set; }
    }
}
